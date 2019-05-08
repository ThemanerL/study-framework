package ssm.handle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@ControllerAdvice
public class HandleException {
    /**
     * The name of this Logger will be "ssm.handle.HandleException"
     */
    private static final Logger logger = LogManager.getLogger();
    private final DateFormat dateFormat;

    @Autowired
    public HandleException(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @ExceptionHandler
    public ModelAndView handleException(Exception e) {
        logger.error(dateFormat.format(new Date()), e);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("msg", "出现了错误哦:" + e.getMessage());
        return modelAndView;
    }
}
