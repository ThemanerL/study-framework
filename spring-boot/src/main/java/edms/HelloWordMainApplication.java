package edms;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * SpringBootApplication 标注一个主程序类，说明这是一个SprintBoot应用
 * <p>
 * ImportResource 导入Spring的配置文件，让配置文件里面的内容生效:
 * eg:\@ImportResource(locations = {"classpath:beans.xml"})
 * SpringBoot推荐的添加组件的方式；推荐使用全注解的方式
 * 1. 配置类
 *
 * @author 李重辰
 * @date 2020/3/24 0:08
 */
@SpringBootApplication
public class HelloWordMainApplication {
  public static void main(String[] args) {
    // Spring应用程序启动起来
    SpringApplication.run(HelloWordMainApplication.class, args);
  }

  @Bean
  public ViewResolver myViewResolver() {
    return new MyViewResolver();
  }

  private static class MyViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(@NotNull String viewName, @NotNull Locale locale) {
      return null;
    }
  }
}
