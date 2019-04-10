package ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ssm.bean.CheckRepeat;
import ssm.bean.Employee;
import ssm.bean.Message;
import ssm.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

/**
 * 1、处理员工增加改查
 *
 * @author 李重辰
 * @date 2019/4/1 9:21
 */
@Controller
public class EmployeeController {

  final private EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  /**
   * 查询员工数据（分页）
   *
   * @return /
   */
  @ResponseBody
  @RequestMapping(value = "/emps", method = RequestMethod.GET)
  public Message getEmpsByJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
    PageHelper.startPage(pn, 10);
    List<Employee> employeeList = employeeService.listEmps();
    PageInfo<Employee> pageInfo = new PageInfo<>(employeeList, 5);
    return Message.success().add("pageInfo", pageInfo);
  }

  /**
   * 查询单个员工数据
   *
   * @return /
   */
  @ResponseBody
  @RequestMapping(value = "/emp/{empId}", method = RequestMethod.GET)
  public Message getEmpById(@PathVariable("empId") Integer empId) {
    Employee employee = employeeService.getEmpById(empId);
    return Message.success().add("emp", employee);
  }


  /**
   * 新增员工
   *
   * @param employee 、
   * @return /
   */
  @ResponseBody
  @RequestMapping(value = "/emp", method = RequestMethod.POST)
  public Message addEmp(@Valid Employee employee, BindingResult result) {
    Message message = employeeService.dataValidate(employee, result);
    int successStatusCode = 200;
    if (successStatusCode != message.getStatusCode()) {
      return message;
    } else {
      employeeService.saveEmp(employee);
      return Message.success();
    }
  }


  /**
   * 更新员工，要求前台请求为PUT
   *
   * @param employee 、
   * @return /
   */
  @ResponseBody
  @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
  public Message updateEmp(@Valid Employee employee, BindingResult result) {
    Message message = employeeService.dataValidate(employee, result);
    int successStatusCode = 200;
    if (successStatusCode != message.getStatusCode()) {
      return message;
    } else {
      employeeService.updateEmp(employee);
      return Message.success();
    }
  }

  @ResponseBody
  @RequestMapping(value = "/emp/{empId}", method = RequestMethod.DELETE)
  public Message deleteEmp(@PathVariable Integer empId) {
    employeeService.deleteEmp(empId);
    return Message.success();
  }

  @ResponseBody
  @RequestMapping(value = "/emps", method = RequestMethod.DELETE)
  public Message deleteEmps(@RequestBody List<Integer> empIds) {
    employeeService.deleteEmpList(empIds);
    return Message.success();
  }


  /**
   * 前台输入框失去焦点触发数据库查重验证，
   * 注意:不能被提交后后端验证替代，这里的入参包括了输入框的数据类型，提交后验证直接验证的Employee对象
   *
   * @return /
   */
  @ResponseBody
  @RequestMapping("/validate")
  public Message validate(@RequestBody CheckRepeat checkRepeat) {
    if (!employeeService.repeatByCheckRepeat(checkRepeat)) {
      return Message.repeat();
    }
    return Message.success();
  }

  /**
   * 查询员工数据（分页）
   *
   * @return /
   */
  @Deprecated
  public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
    // 在查询前调用，传入页码和每页的大小
    PageHelper.startPage(pn, 10);
    // 在startPage后面紧跟的这个查询就是分页查询
    List<Employee> employeeList = employeeService.listEmps();
    // navigatePages设定连续显示5页，使用PageInfo包装查询后的结果，将PageInfo传给页码，封装了详细的分页信息，包括有我们查询出来的数据
    PageInfo<Employee> pageInfo = PageInfo.of(employeeList, 5);
    model.addAttribute("pageInfo", pageInfo);
    return "list";
  }
}
