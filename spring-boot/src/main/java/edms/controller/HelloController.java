package edms.controller;

import edms.exception.UserNotExistException;
import edms.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 李重辰
 * @date 2020/3/24 0:23
 */
@Controller
public class HelloController {

  @Resource
  HelloService helloService;

  @ResponseBody
  @RequestMapping("/hello")
  public String hello(@RequestParam("user") String user) {
    String aaa = "aaa";
    if (aaa.equals(user)) {
      throw new UserNotExistException();
    }
    return "Hello World";
  }

  @RequestMapping({"/", "/index"})
  public String index() {
    return "index";
  }

  /**
   * classpath:/templates/success.html
   */
  @RequestMapping("/success")
  public String success(Map<String, Object> map) {
    map.put("hello", "thymeleaf demo");
    return "success";
  }

  @RequestMapping(value = "/listProduct", method = RequestMethod.GET)
  public String listPmProductInfo() {
    helloService.testSelectByExample();
    return "success";
  }
}
