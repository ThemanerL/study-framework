package edms.config;

import edms.tomcat.filter.MyFilter;
import edms.tomcat.listener.MyListener;
import edms.tomcat.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李重辰
 * @date 2020/8/26 23:49
 */
@Configuration
public class MyServerConfig {

  /**
   * 注册三大组件
   */
  @Bean
  public ServletRegistrationBean<MyServlet> myServlet() {
    return new ServletRegistrationBean<>(new MyServlet(), "/MyServlet");
  }

  @Bean
  public FilterRegistrationBean<MyFilter> myFilter() {
    return new FilterRegistrationBean<>(new MyFilter());
  }

  @Bean
  public ServletListenerRegistrationBean<MyListener> myListener() {
    return new ServletListenerRegistrationBean<>(new MyListener());
  }

  /**
   * 配置嵌入式的Servlet容器
   */
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

}
