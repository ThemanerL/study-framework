package spring.basic.bean;

import java.io.Serializable;

/**
 * @author 李重辰
 * @date 2019/3/2 17:56
 */
public class Department implements Serializable {

  private static final long serialVersionUID = -6812511105813154069L;
  private Integer id;
  private String name;
  private double baseSalary;

  public Department() {
  }

  public Department(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Department(Integer id, String name, double baseSalary) {
    this.id = id;
    this.name = name;
    this.baseSalary = baseSalary;
  }


  public double getBaseSalary() {
    return baseSalary;
  }

  public void setBaseSalary(double baseSalary) {
    this.baseSalary = baseSalary;
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

  @Override
  public String toString() {
    return "Department{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", baseSalary=" + baseSalary +
        '}';
  }

}
