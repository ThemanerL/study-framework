package mybatis.dao;

import mybatis.bean.Employee;

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
  Employee getEmpByID(Integer id);

  /**
   * 增加一个employee对象
   * @param employee /
   * @return int 返回sql影响的数据条数
   */
  int addEmp(Employee employee);

  /**
   * 修改一个employee对象
   * @param employee/
   * @return int 返回sql影响的数据条数
   */
  int updateEmp(Employee employee);

  /**
   * 根据ID删除一个employee对象
   * @param id 要删除的对象的Employee的ID
   * @return int 返回sql影响的数据条数
   */
  int deleteEmpById(Integer id);
}
