package mybatis.basic.bean;

import java.io.Serializable;

/**
 * @author 李重辰
 * @date 2019/2/18 15:32
 */
public class Employee implements Serializable {

  private static final long serialVersionUID = -2101711165167738656L;
  private Integer id;
  private String lastName;
  private String gender;
  private String email;
  private Department department;
  private EmpStatus status;

  public Employee() {
  }

  public Employee(Integer id, String lastName, String gender, String email) {
    this.id = id;
    this.lastName = lastName;
    this.gender = gender;
    this.email = email;
  }

  public Employee(Integer id, String lastName, String gender, String email, EmpStatus status) {
    this.id = id;
    this.lastName = lastName;
    this.gender = gender;
    this.email = email;
    this.status = status;
  }

  public Employee(Integer id, String lastName, String gender, String email, Department department) {
    this.id = id;
    this.lastName = lastName;
    this.gender = gender;
    this.email = email;
    this.department = department;
  }

  public Employee(String lastName, String gender, String email, Department department, EmpStatus status) {
    this.lastName = lastName;
    this.gender = gender;
    this.email = email;
    this.department = department;
    this.status = status;
  }

  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public EmpStatus getStatus() {
    return status;
  }

  public void setStatus(EmpStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", lastName='" + lastName + '\'' +
        ", gender='" + gender + '\'' +
        ", email='" + email + '\'' +
        ", department=" + department +
        ", status=" + status +
        '}';
  }
}
