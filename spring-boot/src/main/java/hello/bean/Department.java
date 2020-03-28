package hello.bean;

import org.springframework.stereotype.Component;

/**
 * @author 李重辰
 * @date 2020/3/28 10:01
 */
@Component
public class Department {
  private String deptName;

  public Department() {
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public Department(String deptName) {
    this.deptName = deptName;
  }
}
