package spring.basic.bean;

import mybatis.basic.bean.EmpStatus;

import java.io.Serializable;

/**
 * @author 李重辰
 * @date 2019/2/18 15:32
 */
public class Employee implements Serializable {

  private static final long serialVersionUID = -2101711165167738656L;
  private Integer id;
  private String empName;
  private String gender;
  private String email;
  private int deptId;
  private EmpStatus status;

  public Employee() {
  }

  public Employee(Integer id, String empName, String gender, String email) {
    this.id = id;
    this.empName = empName;
    this.gender = gender;
    this.email = email;
  }

  public Employee(Integer id, String empName, String gender, String email, EmpStatus status) {
    this.id = id;
    this.empName = empName;
    this.gender = gender;
    this.email = email;
    this.status = status;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
    System.out.println(empName);
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

  public EmpStatus getStatus() {
    return status;
  }

  public void setStatus(EmpStatus status) {
    this.status = status;
  }

  public int getDeptId() {
    return deptId;
  }

  public void setDeptId(int deptId) {
    this.deptId = deptId;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", empName='" + empName + '\'' +
        ", gender='" + gender + '\'' +
        ", email='" + email + '\'' +
        ", deptId=" + deptId +
        ", status=" + status +
        '}';
  }
}
