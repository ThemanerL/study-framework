package edms.component;

import edms.controller.Handle;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取URL中的特定参数，并且将值带到Controller中
 *
 * @author 李重辰
 * @date 2020/8/20 22:02
 */
public class GetCorpNoHandlerInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String pathInfo = request.getRequestURI();
    String corpNo = pathInfo.split("/")[1];
    if (corpNo.length() == 6) {
      request.setAttribute("CorpNo_", corpNo);
      Handle.corpNo = new StringBuffer(corpNo);
      request.getRequestDispatcher(pathInfo.replace("/" + corpNo, "")).forward(request, response);
      return false;
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

  }
}
