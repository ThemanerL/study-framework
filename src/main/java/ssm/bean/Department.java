package ssm.bean;

/**
 * @author 李重辰
 * @date 2019/4/3 13:46
 */
public class Department {

  private Integer deptId;

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

    @Override
    public String toString() {
        return "Department{" +
            "deptId=" + deptId +
            ", deptName='" + deptName + '\'' +
            '}';
    }
}