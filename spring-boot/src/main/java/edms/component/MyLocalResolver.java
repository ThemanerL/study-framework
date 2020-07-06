package edms.component;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 可以在链接上携带区域信息
 *
 * @author 李重辰
 * @date 2020/7/6 18:30
 */
public class MyLocalResolver implements LocaleResolver {

  @NotNull
  @Override
  public Locale resolveLocale(HttpServletRequest request) {
    String language = request.getParameter("l");
    if (!StringUtils.isEmpty(language)) {
      String[] split = language.split("_");
      return new Locale(split[0], split[1]);
    }
    return Locale.getDefault();
  }

  @Override
  public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

  }
}
