package edms.controller;

import edms.dao.EmployeeDao;
import edms.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

/**
 * @author 李重辰
 * @date 2020/7/9 22:43
 */
@Controller
public class EmployeeController {
  @Autowired
  EmployeeDao employeeDao;

  @GetMapping(value = "emps")
  public String list(Model model) {
    Collection<Employee> employees = employeeDao.getAll();
    model.addAttribute("emps", employees);
    // 放在请求域中
    return "emp/list";
  }
}
