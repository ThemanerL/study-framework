package edms.controller;

import edms.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李重辰
 * @date 2020/8/19 22:44
 */
@ControllerAdvice
public class MyExceptionHandler {

//  /**
//   * 第一种写法： 浏览器和客户端返回的都是 JSON 数据
//   */
//  @ResponseBody
//  @ExceptionHandler(UserNotExistException.class)
//  public Map<String, String> handleException(Exception e) {
//    Map<String, String> map = new HashMap<>(16);
//    map.put("code", "user.notEexist");
//    map.put("message", e.getMessage());
//    return map;
//  }

//  /**
//   * 第二种写法：根据浏览器请求头信息，自适应的返回HTML界面或者JSON数据。
//   * 利用BasicErrorController进行处理，BasicErrorController是根据页面的错误码进行转发的，所以必须在请求头中设置按照
//   * BasicErrorController的规则设置错误码，这样才知道转发到哪个页面
//   */
//  @ExceptionHandler(UserNotExistException.class)
//  public String handleException(Exception e, HttpServletRequest request) {
//    request.setAttribute("javax.servlet.error.status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
//    Map<String, String> map = new HashMap<>(16);
//    map.put("code", "user.notEexist");
//    map.put("message", e.getMessage());
//    return "forward:/error";
//  }

  /**
   * 第三种写法：通过给容器中添加我们自己定义的ErrorAttributes将我们的定制数据也携带出去。
   * <p>
   * 原理：出现错误以后，会来到/error请求，会被BasicErrorController处理。响应出去可以获取的数据是由getErrorAttributes得到的，
   * （AbstractErrorController（ErrorController）规定了这个方法）
   * 实现方法分析：
   * 1. 完全重新写一个ErrorController的实现类，或者写一个AbstractErrorController的子类来替换掉BasicErrorController这个类
   * 2. 页面上能用的数据或者是json返回能用的数据都是通过errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
   * 这个方法得到的。
   * &nbsp;&nbsp;&nbsp;&nbsp;容器中DefaultErrorAttributes.getErrorAttributes是默认进行数据处理的。
   *
   * @see edms.component.MyErrorAttributes
   */
  @ExceptionHandler(UserNotExistException.class)
  public String handleException(Exception e, HttpServletRequest request) {
    Map<String, String> map = new HashMap<>(16);
    map.put("code", "user.notEexist");
    map.put("message", e.getMessage());
    request.setAttribute("javax.servlet.error.status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
    request.setAttribute("additionalInfo", map);
    return "forward:/error";
  }
}
