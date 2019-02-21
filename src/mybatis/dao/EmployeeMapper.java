package mybatis.dao;

import mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李重辰
 * @date 2019/2/19 16:17
 */
public interface EmployeeMapper {
  /**
   * 根据ID获取员工信息
   * @param id 员工ID
   * @return emp对象
   */
  Employee getEmpByID(List id);

 /**
  * 根据ID获取员工信息
  * @param id 员工ID
  * @return emp对象
  */
 Employee getEmpByID(Integer id);

  /**
   * 根据ID和名字 获取员工
   * @param id /
   * @param lastName /
   * @return /
   */
  Employee getEmpByIdXLastName(@Param("id") Integer id, @Param("lastName") String lastName);

  /**
  * 根据ID和名字 获取员工
  * @param map /
  * @return /
  */
  Employee getEmpByMap(Map<String, Object> map);

  /**
   * 增加一个employee对象
   * @param employee /
   * @return boolean 返回sql影响的数据条数
   */
  boolean addEmp(Employee employee);

  /**
   * 修改一个employee对象
   * @param employee/
   * @return boolean 返回sql影响的数据条数
   */
  boolean updateEmp(Employee employee);

  /**
   * 根据ID删除一个employee对象
   * @param id 要删除的对象的Employee的ID
   * @return boolean 返回sql影响的数据条数
   */
  boolean deleteEmpById(Integer id);

  /**
   * 根据email删除一批employee对象
   * @param email 要删除的对象的email
   * @return int 影响的记录条数
   */
  int deleteEmpEmail(String email);
}
