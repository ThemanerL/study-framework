package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.bean.Department;
import ssm.bean.Message;
import ssm.service.DepartmentService;

import java.util.Collection;

/**
 * @author 李重辰
 * @date 2019/4/3 13:46
 */
@Controller
public class DepartmentController {

  final private DepartmentService departmentService;

  @Autowired
  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @ResponseBody
  @RequestMapping("/depts")
  public Message getDepts() {
    Collection<Department> depts = departmentService.getDepts();
    return Message.success().add("depts", depts);
  }
}
