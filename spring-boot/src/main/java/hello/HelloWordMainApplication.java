package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * SpringBootApplication 标注一个主程序类，说明这是一个SprintBoot应用
 *
 * @author 李重辰
 * @date 2020/3/24 0:08
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HelloWordMainApplication {
  public static void main(String[] args) {
    // Spring应用程序启动起来
    SpringApplication.run(HelloWordMainApplication.class, args);
  }
}
