package edms.config;

import edms.component.LoginHandlerInterceptor;
import edms.component.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 李重辰
 * @date 2020/4/8 19:57
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

  /**
   * 可以使用这种方式将index映射到模板引擎解析的页面中
   */
  @Bean
  public WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
      }

      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("index", "/","/user/login");
      }
    };
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/viewController").setViewName("success");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
  }

  @Bean
  public LocaleResolver localeResolver() {
    return new MyLocalResolver();
  }
}
