package ssm.bean;

import java.util.Date;

/**
 * @author 李重辰
 * @date 2020/03/23 11:29
 */
public class Department {
  /**
   *
   */
  private Integer id;

  /**
   * 部门名
   */
  private String deptName;

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
  private Boolean isDeleted;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName == null ? null : deptName.trim();
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

  public Boolean getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Boolean isDeleted) {
    this.isDeleted = isDeleted;
  }
}