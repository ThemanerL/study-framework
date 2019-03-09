package mybatis.bean;

import java.util.List;

/**
 * @author 李重辰
 * @date 2019/3/2 17:56
 */
public class Department {
  private Integer id;
  private String name;
  private List<Employee> employees;

  public Department() {
  }


  public Department(Integer id) {
    this.id = id;
  }

  public Department(String name) {
    this.name = name;
  }

  public Department(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  @Override
  public String toString() {
    return "Department{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
