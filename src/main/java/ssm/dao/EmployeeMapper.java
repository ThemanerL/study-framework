package ssm.dao;

import org.apache.ibatis.annotations.Param;
import ssm.bean.Employee;
import ssm.bean.EmployeeExample;

import java.util.List;

public interface EmployeeMapper {
  long countByExample(EmployeeExample example);

  int deleteByExample(EmployeeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Employee record);

  int insertSelective(Employee record);

  List<Employee> selectByExample(EmployeeExample example);

  Employee selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

  int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

  int updateByPrimaryKeySelective(Employee record);

  int updateByPrimaryKey(Employee record);

  List<Employee> selectByExampleWithDept(Object o);

  Employee selectByPrimaryKeyWithDept(Integer empId);
}