package ssm.bean;

import java.util.Date;
import java.util.Objects;

/**
 * @author 李重辰
 * @date 2019/11/20 01:40
 */
public class Department {
  /**
   * 部门编号
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

  public Department() {
  }

  public Department(Integer id, String deptName) {
    this.id = id;
    this.deptName = deptName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Department that = (Department) o;
    return id.equals(that.id) &&
        Objects.equals(deptName, that.deptName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, deptName);
  }

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