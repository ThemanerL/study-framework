package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spring.basic.bean.Department;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李重辰
 * @date 2019/3/23 12:02
 */
@RequestMapping("/springMVC")
@Controller
public class SpringMvc {

  static final String SUCCESS = "success";

  /**
   * value:限定请求URL
   * method:限定请求方式
   * params:限定请求必须包含的参数值
   * headers:限定请求头文件的某些值
   *
   * @return /
   */
  @RequestMapping(value = "/helloWord", method = RequestMethod.POST, params = {"username=Li", "password=lizhongchen"}, headers = {"Host=localhost:8080"})
  public String hello() {
    System.err.println("SpringMvc.helloWord");
    return SUCCESS;
  }

  /**
   * 使用@RequestParam 获得参数
   * name:请求参数绑定到的名称
   * value:name的别名
   * defaultValue:当请求参数没有提供或者是空值的时候,就使用defaultValue中的值;
   * 当提供一个默认值的时候,暗示着设置required()的值为false.
   *
   * @param method 获取到一个什么类型的请求
   * @return /
   */
  @RequestMapping(value = "/methodGet", method = RequestMethod.GET)
  public String methodGet(@RequestParam(name = "_method", defaultValue = "POST") String method) {
    System.out.println(method);
    return SUCCESS;
  }

  /**
   * @param method /
   * @return /
   */
  @RequestMapping(value = "/methodPut", method = RequestMethod.PUT)
  public String methodPut(@RequestParam(name = "_method", defaultValue = "POST") String method) {
    System.out.println(method);
    return SUCCESS;
  }

  /**
   * 可以使用@PathVariable来映射URL中的占位符到目标方法的参数中
   *
   * @param method 获取到一个什么类型的请求
   * @return /
   */
  @RequestMapping(value = "/methodDelete", method = RequestMethod.DELETE)
  public String methodDelete(@RequestParam(name = "_method", defaultValue = "POST") String method) {
    System.out.println(method);
    return SUCCESS;
  }

  /**
   * 可以使用@PathVariable来映射URL中的占位符到目标方法的参数中
   *
   * @param method 获取到一个什么类型的请求
   * @return /
   */
  @RequestMapping(value = "/methodPost", method = RequestMethod.POST)
  public String methodPost(@RequestParam(name = "_method", defaultValue = "POST") String method) {
    System.out.println(method);
    return SUCCESS;
  }

  /**
   * 可以使用@PathVariable来映射URL中的占位符到目标方法的参数中
   *
   * @param id 从URL中获得的参数
   * @return /
   */
  @RequestMapping("/pathVariable/{id}")
  public String hiThere(@PathVariable int id) {
    System.out.println(id);
    return SUCCESS;
  }

  /**
   * @param method HTTP头中的
   * @return /
   */
  @RequestMapping("/RequestHeader")
  public String requestHeader(@RequestHeader(name = "Cookie") String method) {
    System.out.println(method);
    return SUCCESS;
  }

  /**
   * 注解@CookieValue可以让处理方法入参绑定某个cookie的值
   *
   * @param sessionId 解sessionID
   * @return /
   */
  @RequestMapping("/CookieValue")
  public String cookieValue(@CookieValue(name = "JSESSIONID") String sessionId) {
    System.out.println(sessionId);
    return SUCCESS;
  }

  /**
   * SpringMVC会按请求参数名和POJO属性名进行自动匹配,自动为该参数填充属性值,支持级联属性
   *
   * @param department /
   * @return /
   */
  @RequestMapping("/putDepartment")
  public String addDepartment(Department department) {
    System.out.println(department);
    return SUCCESS;
  }

  @RequestMapping("/servletApi")
  public String servletApi(HttpServletRequest request, HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      String temp = cookie.getName() + ":" + cookie.getValue();
      System.out.println(temp);
    }
    System.out.println(response);
    return SUCCESS;
  }
}
