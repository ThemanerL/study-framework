package ssm.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ssm.java.bean.CheckRepeat;
import ssm.java.bean.Employee;
import ssm.java.bean.EmployeeExample;
import ssm.java.bean.Message;
import ssm.java.dao.EmployeeMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李重辰
 * @date 2019/4/1 9:26
 */
@Service
public class EmployeeService {

  final private EmployeeMapper employeeMapper;

  @Autowired
  public EmployeeService(EmployeeMapper employeeMapper) {
    this.employeeMapper = employeeMapper;
  }

  /**
   * 查询所有员工
   *
   * @return 、
   */
  public List<Employee> getAll() {
    return employeeMapper.selectByExampleWithDept(null);
  }

  /**
   * 新增一个员工
   * 注意:mybatis的BatchExecutor执行的非Query方法的返回值总是-2147483638，所以这里不能使用int=1来判断是否执行成功
   *
   * @param employee 、
   */
  public void saveEmp(Employee employee) {
    employeeMapper.insertSelective(employee);
  }

  public Message dataValidate(Employee employee, BindingResult result) {
    // 正则效验
    if (result.hasErrors()) {
      Map<String, String> errorMsg = new HashMap<>(18);
      List<FieldError> fieldError = result.getFieldErrors();
      for (FieldError error : fieldError) {
        errorMsg.put(error.getField(), error.getDefaultMessage());
      }
      return Message.fail().add("error", errorMsg);
    }
    // repeat效验
    if (!empRepeat(employee)) {
      return Message.repeat();
    }
    return Message.success();
  }

  /**
   * 用户名或者邮箱是否被使用过，
   *
   * @param employee 、
   * @return 无则返回true
   */
  private boolean empRepeat(Employee employee) {
    EmployeeExample employeeExample = new EmployeeExample();
    EmployeeExample.Criteria criteria = employeeExample.createCriteria();
    criteria.andEmailEqualTo(employee.getEmail());
    criteria.andDIdNotEqualTo(employee.getdId());
    // 不为空就是新增数据的验证
    if (null != employee.getEmpName()) {
      EmployeeExample.Criteria criteria1 = employeeExample.createCriteria();
      criteria1.andEmpNameEqualTo(employee.getEmpName());
      employeeExample.or(criteria1);
    }
    List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
    if (employeeList.size() == 0) {
      return true;
    } else {
      // 如果数据库中有相同的email，取出所有的id
      // 这些id中是否与将要查验的员工id相同，如果相同，则说明更新未更新email而已，
      // 如果不同，则说明当前修改后员工的邮箱与别的员工的邮箱重复了
      List<Integer> idList = new ArrayList<>();
      for (Employee temp : employeeList) {
        idList.add(temp.getdId());
      }
      return !idList.contains(employee.getdId());
    }
  }


  /**
   * 根据前台传入的将被检查的类型，查看有无重复的用户名或者邮箱
   * 只用于新增
   *
   * @param checkRepeat 、
   * @return 无则返回true
   */
  public boolean repeatByCheckRepeat(CheckRepeat checkRepeat) {
    String dataType = checkRepeat.getDataType();
    String value = checkRepeat.getValue();
    String empName = "empName", email = "email";
    EmployeeExample employeeExample = new EmployeeExample();
    EmployeeExample.Criteria criteria = employeeExample.createCriteria();
    if (empName.equals(dataType)) {
      criteria.andEmpNameEqualTo(value);
    } else if (email.equals(dataType)) {
      criteria.andEmailEqualTo(value);
    }
    List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
    return employeeList.size() == 0;
  }

  /**
   * 正则匹配格式
   *
   * @param checkRepeat 、
   * @return 、
   */
  @Deprecated
  public boolean regexCheck(CheckRepeat checkRepeat) {
    String dataType = checkRepeat.getDataType();
    String value = checkRepeat.getValue();
    String empName = "empName", email = "email", regex = "";
    if (empName.equals(dataType)) {
      regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
    } else if (email.equals(dataType)) {
      regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    }
    return value.matches(regex);
  }

  public void updateEmp(Employee employee) {
    employeeMapper.updateByPrimaryKeySelective(employee);
  }

  public Employee getEmpById(Integer empId) {
    return employeeMapper.selectByPrimaryKeyWithDept(empId);
  }

  public void deleteEmp(Integer empId) {
    employeeMapper.deleteByPrimaryKey(empId);
  }

  public void deleteEmp(List<Integer> empIds) {
    EmployeeExample example = new EmployeeExample();
    EmployeeExample.Criteria criteria = example.createCriteria();
    criteria.andEmpIdIn(empIds);
    employeeMapper.deleteByExample(example);
  }
}
