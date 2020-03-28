package hello.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 李重辰
 * @date 2020/3/28 9:46
 */
@Component
@ConfigurationProperties(prefix = "employee")
public class Employee {
  private String empName;
  private int age;
  private char gender;
  private String email;
  private int isDeleted;
  private Department department;

  public Employee() {
  }

  public Employee(String empName, int age, char gender, String email, int isDeleted, Department department) {
    this.empName = empName;
    this.age = age;
    this.gender = gender;
    this.email = email;
    this.isDeleted = isDeleted;
    this.department = department;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "{\"Employee\":{" +
        "\"empName\":\"" + empName + '\"' +
        ", \"age\":" + age +
        ", \"gender\":" + gender +
        ", \"email\":\"" + email + '\"' +
        ", \"isDeleted\":" + isDeleted +
        ", \"department\":" + department +
        "'}}'";
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(int isDeleted) {
    this.isDeleted = isDeleted;
  }
}
