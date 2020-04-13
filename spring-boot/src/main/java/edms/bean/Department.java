package edms.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 李重辰
 * @date 2020/3/28 10:01
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
  private String deptName;

  @Override
  public String toString() {
    return "{\"Department\":{" +
        "\"deptName\":\"" + deptName + '\"' +
        "}}";
  }
}
