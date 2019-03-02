package mybatis.dao;

import mybatis.bean.Department;

import java.util.List;
/**
 * @author 李重辰
 * @date 2019/3/2 18:02
 */
public interface DepartmentMapper {
  /**
   * 添加部门
   *
   * @param department /
   * @return /
   */
  boolean addDept(Department department);

  /**
   * 查获所有部门
   *
   * @return /
   */
  List<Department> getDepts();
}
