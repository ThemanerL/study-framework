package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
