package edms.component.mimrc;

import edms.controller.Handle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 【为华软测试】
 * @author 李重辰
 * @date 2020/8/20 23:33
 */
public class GetCorpNoHandlerFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestUrl = httpRequest.getRequestURI();
    String corpNo = requestUrl.split("/")[1];
    if (corpNo.length() == 6) {
      request.setAttribute("CorpNo_", corpNo);
      Handle.corpNo = new StringBuffer(corpNo);
      request.getRequestDispatcher(requestUrl.replace("/" + corpNo, "")).forward(request, response);
    }
    chain.doFilter(request, response);
  }

}
