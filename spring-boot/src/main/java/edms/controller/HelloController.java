package edms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author 李重辰
 * @date 2020/3/24 0:23
 */
@Controller
public class HelloController {

  @ResponseBody
  @RequestMapping("/hello")
  public String hello() {
    return "Hello World";
  }

  /**
   * classpath:/templates/success.html
   */
  @RequestMapping("/success")
  public String success(Map<String, Object> map) {
    map.put("hello", "thymeleaf demo");
    return "success";
  }
}
