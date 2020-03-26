package mybatis.basic.dao;

import mybatis.basic.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李重辰
 * @date 2019/3/6 15:19
 */
public interface EmployeeDynamicSqlMapper {
  /**
   * eg1:查询员工 携带了哪个字段,查询条件就带上这个字段的值
   *
   * @param employee /
   * @return /
   */
  List<Employee> getEmpsByIfWhere(Employee employee);

  /**
   * eg1:查询员工 携带了哪个字段,查询条件就带上这个字段的值
   *
   * @param employee /
   * @return /
   */
  List<Employee> getEmpsByIfTrim(Employee employee);

  /**
   * eg1:如果带了id就用id查,如果带了empName就用empName查
   *
   * @param employee /
   * @return /
   */
  List<Employee> getEmpsByChoose(Employee employee);

  /**
   * 测试Mybatis内部参数
   *
   * @param employee /
   * @return /
   */
  List<Employee> getEmpsInnerParameter(Employee employee);

  /**
   * 查询id在给定集合中的员工
   *
   * @param ids 员工id集合
   * @return /
   */
  List<Employee> getEmpsByForeach(@Param("ids") List<Integer> ids);

  /**
   * Employee中有封装的对象有什么属性就更新什么属性
   *
   * @param employee /
   * @return /
   */
  boolean updateEmpBySet(Employee employee);

  /**
   * 使用foreach批量保存
   *
   * @param emps /
   * @return /
   */
  int addEmpsByForeach(@Param("emps") List<Employee> emps);
}
