package edms.config;

import edms.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration 这个注解告诉SpringBoot 指定当前类是一个配置类，替代以前的Spring的xml配置文件
 *
 * @author 李重辰
 * @date 2020/3/29 16:53
 */
@Configuration
public class AppConfig {
  /**
   * 将方法的返回值添加到容器中，容器中的这个组件默认的ID就是这个方法的名字
   */
  @Bean
  public HelloService helloService() {
    System.out.println("给容器中添加了helloService组件");
    return new HelloService();
  }
}
