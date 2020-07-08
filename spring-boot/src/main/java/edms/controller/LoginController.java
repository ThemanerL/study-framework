package edms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 李重辰
 * @date 2020/7/8 20:07
 */
@Controller
public class LoginController {

  private static String DEFAULT_PASSWORD = "123456";

  /**
   * @RequestMapping(value = "/user/login", method = RequestMethod.POST)
   */
  @PostMapping(value = "/user/login")
  public String login(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      Map<String, Object> map, HttpSession session) {
    if (!StringUtils.isEmpty(username) && DEFAULT_PASSWORD.equals(password)) {
      // 登录成功之后，防止表单提交，重定向到主页
      session.setAttribute("loginUser", username);
      return "redirect:/main.html";
    } else {
      map.put("msg", "登录名/密码错误");
      return "index";
    }
  }
}
