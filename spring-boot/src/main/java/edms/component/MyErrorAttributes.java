package edms.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

/**
 * @author 李重辰
 * @date 2020/8/19 23:13
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
  @Override
  public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
    Map<String, Object> errorAttributesMap = super.getErrorAttributes(webRequest, includeStackTrace);
    errorAttributesMap.put("dad", "lzc");

    Map<String, Object> additionalInfo = (Map<String, Object>) webRequest.getAttribute("additionalInfo", SCOPE_REQUEST);
    errorAttributesMap.put("additionalInfo", additionalInfo);
    return errorAttributesMap;
  }
}
