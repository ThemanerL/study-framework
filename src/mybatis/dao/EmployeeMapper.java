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
}
