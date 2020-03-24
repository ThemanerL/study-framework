package mybatis.basic.dao;

import mybatis.basic.bean.Department;

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
   * 根据部门id 查出所有的部门员工
   *
   * @param deptName /
   * @return /
   */
  Department getDeptEmpsById(String deptName);

  /**
   * 根据id查部门
   *
   * @param id /
   * @return /
   */
  Department getDeptById(Integer id);

  /**
   * 查获所有部门
   *
   * @return /
   */
  List<Department> getDepts();
}
