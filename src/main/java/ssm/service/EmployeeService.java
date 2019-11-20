package ssm.service;

import org.springframework.validation.BindingResult;
import ssm.bean.CheckRepeat;
import ssm.bean.Employee;
import ssm.bean.Message;

import java.util.List;

/**
 * @author 李重辰
 * @date 2019/4/13 15:10
 */
public interface EmployeeService {

  /**
   * 查询所有员工
   *
   * @return 、
   */
  List<Employee> listEmps();

  /**
   * 新增一个员工
   * 注意:mybatis的BatchExecutor执行的非Query方法的返回值总是-2147483638，所以这里不能使用int=1来判断是否执行成功
   *
   * @param employee 、
   */
  void saveEmp(Employee employee);

  Message dataValidate(Employee employee, BindingResult result);

  /**
   * 用户名或者邮箱是否被使用过，
   *
   * @param employee 、
   * @return 无则返回true
   */
  boolean empRepeat(Employee employee);


  /**
   * 根据前台传入的将被检查的类型，查看有无重复的用户名或者邮箱
   * 只用于新增
   *
   * @param checkRepeat 、
   * @return 无则返回true
   */
  boolean repeatByCheckRepeat(CheckRepeat checkRepeat);

  /**
   * 正则匹配格式
   *
   * @param checkRepeat 、
   * @return 、
   */
  @Deprecated
  boolean regexCheck(CheckRepeat checkRepeat);

  void updateEmp(Employee employee);

  Employee getEmpById(Integer empId);

  void deleteEmp(Integer empId);

  void deleteEmpList(List<Integer> empIds);
}
