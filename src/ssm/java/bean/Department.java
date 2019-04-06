package ssm.java.bean;

/**
 * @author 李重辰
 * @date 2019/03/31 07:30
 */
public class Department {
  /**
   * 部门编号
   */
  private Integer deptId;

  /**
   * 部门名
   */
  private String deptName;

  public Integer getDeptId() {
    return deptId;
  }

  public void setDeptId(Integer deptId) {
    this.deptId = deptId;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName == null ? null : deptName.trim();
  }
}