package ssm.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author 李重辰
 * @date 2019/4/11 17:40
 */
@Slf4j
@ControllerAdvice
public class HandleException {
  /**
   * The name of this Logger will be "ssm.handle.HandleException"
   */
  private final DateFormat dateFormat;

  @Autowired
  public HandleException(DateFormat dateFormat) {
    this.dateFormat = dateFormat;
  }

  @ExceptionHandler
  public ModelAndView handleException(Exception e) {
    log.error(dateFormat.format(new Date()), e);
    ModelAndView modelAndView = new ModelAndView("error");
    modelAndView.addObject("msg", "出现了错误哦:" + e.getMessage());
    return modelAndView;
  }
}
