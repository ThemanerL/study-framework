package mybatis.basic.dao;

import mybatis.basic.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 李重辰
 * @date 2019/2/19 16:17
 */
public interface EmployeeMapper {

  /**
   * 根据员工的姓进行模糊查询
   *
   * @param lastName /
   * @return 所有符合结果的员工集合
   */
  List<Employee> getEmpsByLastNameLike(String lastName);

  /**
   * 根据ID获取员工信息
   *
   * @param id 员工ID
   * @return emp对象
   */
  Employee getEmpByID(List id);

  /**
   * 根据ID获取员工信息
   *
   * @param id 员工ID
   * @return emp对象
   */
  Employee getEmpByID(Integer id);

  /**
   * 多条记录封装为一个Map<Integer, Employee>.Key为该条记录的主键，值为封装后的javabean对象
   * 该注解告诉myBatis封装这个Map时使用哪个属性作为Map的Key
   *
   * @param lastName /
   * @return /
   */
  @MapKey("lastName")
  Map<String, Employee> getEmpByLastNameReturnMap(String lastName);

  /**
   * 返回一条记录的Map：key就是列名，值为查出来的值
   *
   * @param id /
   * @return /
   */
  Map<String, Object> getEmpByIDReturnMap(Integer id);

  /**
   * 根据ID和名字 获取员工
   *
   * @param id       /
   * @param lastName /
   * @return /
   */
  Employee getEmpByIdXLastName(@Param("id") Integer id, @Param("lastName") String lastName);

  /**
   * 根据ID和名字 获取员工
   *
   * @param map /
   * @return /
   */
  Employee getEmpByMap(Map<String, Object> map);

  /**
   * 增加一个employee对象
   *
   * @param employee /
   * @return boolean 返回sql影响的数据条数
   */
  boolean addEmp(Employee employee);

  /**
   * 修改一个employee对象
   *
   * @param employee/
   * @return boolean 返回sql影响的数据条数
   */
  boolean updateEmp(Employee employee);

  /**
   * 根据ID删除一个employee对象
   *
   * @param id 要删除的对象的Employee的ID
   * @return boolean 返回sql影响的数据条数
   */
  boolean deleteEmpById(Integer id);

  /**
   * 根据email删除一批employee对象
   *
   * @param email 要删除的对象的email
   * @return int 影响的记录条数
   */
  int deleteEmpEmail(String email);
}
