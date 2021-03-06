package mybatis.basic.dao;

import mybatis.basic.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * @author 李重辰
 * @date 2019/2/20 17:55
 */
public interface EmployeeMapperAnnotation {
  /**
   * 把sql语句写在注解中，省去了通过sql映射
   *
   * @param id /
   * @return /
   */
  @Select("select * from tbl_emp where id = #{id}")
  Employee getEmpById(Integer id);
}
