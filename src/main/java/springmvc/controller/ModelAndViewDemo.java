package springmvc.controller;

import mybatis.basic.bean.EmpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.basic.bean.Employee;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 李重辰
 * @date 2019/3/25 16:57
 */
@SessionAttributes("employee")
@RequestMapping("/mav")
@Controller
public class ModelAndViewDemo {

  /**
   * 目标方法的返回值设为ModelAndView,可以在其中包含了视图和模型信息
   * SpringMVC会把Model数据放入request域对象中
   *
   * @return /
   */
  @RequestMapping("testMAV")
  public ModelAndView testMAV() {
    String viewName = springmvc.controller.SpringMvc.SUCCESS;

    ModelAndView view = new ModelAndView(viewName);
    // 添加模型数据到ModeAndView中
    view.addObject("time", new Date());
    return view;
  }

  @RequestMapping("testMAVMap")
  public String testMAVMap(Map<String, Object> map) {
    map.put("param1", Arrays.asList("一号", "23号", "4号"));
    return SpringMvc.SUCCESS;
  }

  @RequestMapping("session")
  public String testSession(Map<String, Object> map) {
    Employee employee = new Employee(2, "王大锤", "0", "awdawdk@asd.cn");
    map.put("employee", employee);
    return SpringMvc.SUCCESS;
  }

  /**
   * 注解ModelAttribute的方法会在每个目标方法执行之前被SpringMVC调用
   * 使用该注解,可以将数据库中的某个对象取出通过MVC给到前台,前台进行操作后再把原对象返回
   * 传统的方法是取出数据库中的对象,然后前台传回一个对象,将部分前台不能给的值从数据库对象中的值取出赋对前台传回的对象再
   * 存入数据库
   *
   * @param map /
   * @return /
   */
  @ModelAttribute
  public Employee getEmp(Map<String, Object> map) {
    Employee employee = new Employee(2, "王大锤", "0", "awdawdk@asd.cn", EmpStatus.BUSY);
    map.put("employee", employee);
    return employee;
  }

  /**
   * 运行流程
   * 1. 执行@ModelAttribute注解修饰的方法:从数据库中取出对象,将对象放入到了Map中,键为employee
   * 2. SpringMVC从Map中取出了employee对象,并将表单中请求参数赋给了该对象的对应属性
   * 3. SpringMVC把上述对象传入目标方法的参数.
   * <p>
   * 注意:在modelAttribute修饰的方法中,放入到Map中的键需要和目标方法入参类型的第一个字母小写的字符串一致
   *
   * @param employee /
   * @return /
   */
  @RequestMapping("testModelAttribute")
  public String testModelAttribute(Employee employee) {
    System.out.println(employee);
    return SpringMvc.SUCCESS;
  }

  /**
   * 有@InitBinder标识的方法,可以对WebDataBinder对象进行初始化
   * 该方法不能有返回值,必须为void 该方法参数通常为WebDataBinder
   *
   * @param webDataBinder DataBinder对象的子类,用于完成表单字段到JavaBean字段的绑定
   */
  @InitBinder
  public void initBinder(WebDataBinder webDataBinder) {
    webDataBinder.setDisallowedFields("id");
  }

  @ResponseBody
  @RequestMapping("json")
  public List<Employee> getEmps() {
    return Arrays.asList(new Employee(1, "lisd", "0", "1@163.com"),
        new Employee(2, "Jsoy", "1", "gson@gmail.com"),
        new Employee(3, "Gson", "1", "sdfae@qq.com"));
  }

  @ExceptionHandler({ArithmeticException.class})
  public ModelAndView testExceptionHandler(Exception e) {
    ModelAndView mav = new ModelAndView("error");
    mav.addObject("exception", e);
    return mav;
  }

  @RequestMapping("zeroException")
  public String zeroException(@RequestParam(name = "i") int i) {
    System.out.println(3 / i);
    return SpringMvc.SUCCESS;
  }
}
