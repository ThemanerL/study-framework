package ssm.dao;

import org.apache.ibatis.annotations.Param;
import ssm.bean.Employee;
import ssm.bean.EmployeeExample;

import java.util.List;
/**
 * @author 李重辰
 * @date 2019/4/3 13:46
 */
public interface EmployeeMapper {
  long countByExample(EmployeeExample example);

  int deleteByExample(EmployeeExample example);

  int deleteByPrimaryKey(Integer empId);

  int insert(Employee record);

  int insertSelective(Employee record);

  List<Employee> selectByExample(EmployeeExample example);

  Employee selectByPrimaryKey(Integer empId);

  List<Employee> selectByExampleWithDept(EmployeeExample example);

  Employee selectByPrimaryKeyWithDept(Integer empId);

  int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

  int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

  int updateByPrimaryKeySelective(Employee record);

  int updateByPrimaryKey(Employee record);
}