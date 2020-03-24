package ssm.bean;

import java.util.Date;

/**
 * @author 李重辰
 * @date 2020/03/23 11:29
 */
public class Employee {
  /**
   *
   */
  private Integer id;

  /**
   * 员工姓名
   */
  private String empName;

  /**
   * 性别
   */
  private String gender;

  /**
   * 邮箱
   */
  private String email;

  /**
   *
   */
  private Integer dId;

  /**
   *
   */
  private Date gmtCreate;

  /**
   *
   */
  private Date gmtModified;

  /**
   *
   */
  private Byte isDeleted;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmpName() {
    return empName;
  }

  public Employee(String empName, Integer dId, String email) {
    this.empName = empName;
    this.email = email;
    this.dId = dId;
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

  public Date getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(Date gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public Date getGmtModified() {
    return gmtModified;
  }

  public void setGmtModified(Date gmtModified) {
    this.gmtModified = gmtModified;
  }

  public Byte getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Byte isDeleted) {
    this.isDeleted = isDeleted;
  }
}