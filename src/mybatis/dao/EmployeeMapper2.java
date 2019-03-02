package mybatis.dao;

import mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.Map;

/**
 * @author 李重辰
 * @date 2019/2/19 16:17
 */
public interface EmployeeMapper2 {
  /**
   * 根据ID返回一个员工
   * @param id 根据ID
   * @return 返回一个员工
   */
  Employee getEmpById(Integer id );

  /**
   * 建立员工与部门的关系
   * @param id 员工id
   * @param deptId 员工分属哪个部门
   * @return /
   */
  boolean addEmpDeptId(@Param("id")Integer id, @Param("deptId")Integer deptId);
}
