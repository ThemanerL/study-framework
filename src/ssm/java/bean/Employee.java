package ssm.java.bean;


import javax.validation.constraints.Pattern;

/**
 * @author 李重辰
 * @date 2019/03/31 07:30
 */
public class Employee {
  /**
   * 员工编号
   */
  private Integer empId;

  /**
   * 员工姓名
   */
  @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})", message = "姓名格式不正确！")
  private String empName;

  /**
   * 性别
   */
  private String gender;

  /**
   * 邮箱
   */
  @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱格式不正确！")
  private String email;

  /**
   * 部门ID
   */
  private Integer dId;
  private Department department;

  public Employee() {
  }

  public Employee(String empName, String gender, String email, Integer dId) {
    this.empName = empName;
    this.gender = gender;
    this.email = email;
    this.dId = dId;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Integer getEmpId() {
    return empId;
  }

  public void setEmpId(Integer empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName == null ? null : empName.trim();
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender == null ? null : gender.trim();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  public Integer getdId() {
    return dId;
  }

  public void setdId(Integer dId) {
    this.dId = dId;
  }
}