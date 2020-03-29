package hello.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

/**
 * ConfigurationProperties 默认是从全局配置文件中读取配置
 * PropertySource 可以加载指定的配置文件
 * @author 李重辰
 * @date 2020/3/28 9:46
 */
@Component
@ConfigurationProperties(prefix = "employee")
@Data
@AllArgsConstructor
@Validated
@NoArgsConstructor
@PropertySource(value = {"classpath:employee.properties"})
public class Employee {
  @Value("${employee.empName}")
  private String empName;
  private int age;
  private char gender;
  @Email
  private String email;
  private int isDeleted;
  private Department department;

  @Override
  public String toString() {
    return "{\"Employee\":{" +
        "\"empName\":\"" + empName + '\"' +
        ", \"age\":" + age +
        ", \"gender\":" + gender +
        ", \"email\":\"" + email + '\"' +
        ", \"isDeleted\":" + isDeleted +
        ", \"department\":" + department +
        "}}";
  }
}
