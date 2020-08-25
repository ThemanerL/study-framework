package edms.config;

import edms.component.GetCorpNoHandlerFilter;
import edms.component.LoginHandlerInterceptor;
import edms.component.MyLocalResolver;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李重辰
 * @date 2020/4/8 19:57
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Bean
  public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
    return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
      /**
       * 定制嵌入式的Servlet容器
       */
      @Override
      public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(8090);
      }
    };
  }

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
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
            excludePathPatterns("index", "/", "/user/login", "/index", "/static/**", "/webjars/**");
//        registry.addInterceptor(new GetCorpNoHandlerInterceptor()).addPathPatterns("/**").
//            excludePathPatterns("index", "/", "/user/login", "/index", "/static/**", "/webjars/**");
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

  @Bean
  public FilterRegistrationBean<GetCorpNoHandlerFilter> getCorpNoHandlerFilter() {
    FilterRegistrationBean<GetCorpNoHandlerFilter> registrationBean = new FilterRegistrationBean<>();
    GetCorpNoHandlerFilter timeFilter = new GetCorpNoHandlerFilter();
    registrationBean.setFilter(timeFilter);
    List<String> urls = new ArrayList<>();
    //所有请求都过滤，指定请求
    urls.add("/*");
    registrationBean.setUrlPatterns(urls);
    return registrationBean;
  }
}
