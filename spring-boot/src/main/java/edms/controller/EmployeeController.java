package edms.controller;

import edms.dao.DepartmentDao;
import edms.dao.EmployeeDao;
import edms.entities.Department;
import edms.entities.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collection;

/**
 * @author 李重辰
 * @date 2020/7/9 22:43
 */
@Controller
public class EmployeeController {
  final EmployeeDao employeeDao;
  final DepartmentDao departmentDao;

  public EmployeeController(EmployeeDao employeeDao, DepartmentDao departmentDao) {
    this.employeeDao = employeeDao;
    this.departmentDao = departmentDao;
  }

  @GetMapping(value = "emps")
  public String list(Model model) {
    Collection<Employee> employees = employeeDao.getAll();
    model.addAttribute("emps", employees);
    // 放在请求域中
    return "emp/list";
  }

  @GetMapping(value = "emp")
  public String addPage(Model model) {
    Collection<Department> departments = departmentDao.getDepartments();
    model.addAttribute("departments", departments);
    return "emp/add";
  }

  /**
   * SpringMVC 自动将请求取参数和参数对象的属性进行一一绑定
   * 要求请求参数的名字和javaBean入参的对象的属性名是一样的
   */
  @PostMapping(value = "emp")
  public String addEmp(Employee employee) {
    System.out.println("success");
    // 斜杠代表是当前项目下
    return "redirect:/emps";
  }

  /**
   * 修改界面，显示当前员工
   *
   * @param id 将要修改的员工的编号
   * @return 目标页面的名字
   */
  @GetMapping("/emp/{id}")
  public String editEmp(@PathVariable("id") Integer id, Model model) {
    Employee employee = employeeDao.get(id);
    model.addAttribute("emp", employee);
    Collection<Department> departments = departmentDao.getDepartments();
    model.addAttribute("departments", departments);
    return "emp/edit";
  }

  /**
   * SpringMVC 自动将请求取参数和参数对象的属性进行一一绑定
   * 要求请求参数的名字和javaBean入参的对象的属性名是一样的
   */
  @PutMapping(value = "emp")
  public String editEmp(Employee employee) {
    System.out.println("修改后的员工数据" + employee);
    employeeDao.save(employee);
    // 斜杠代表是当前项目下
    return "redirect:/emps";
  }

  @DeleteMapping(value = "emp/{id}")
  public String deleteEmp(@PathVariable("id") Integer id) {
    employeeDao.delete(id);
    return "redirect:/emps";
  }
}
