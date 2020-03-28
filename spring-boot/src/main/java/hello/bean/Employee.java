package hello.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 李重辰
 * @date 2020/3/28 9:46
 */
@Component
@ConfigurationProperties(prefix = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
  private String empName;
  private int age;
  private char gender;
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
