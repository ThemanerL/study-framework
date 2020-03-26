package mybatis.generator.dao;

import mybatis.generator.bean.Employee;
import mybatis.generator.bean.EmployeeExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EmployeeMapper {
  long countByExample(EmployeeExample example);

  int deleteByExample(EmployeeExample example);

  @Delete({
      "delete from tbl_emp",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteByPrimaryKey(Integer id);

  @Insert({
      "insert into tbl_emp (id, emp_name, ",
      "gender, email, d_id)",
      "values (#{id,jdbcType=INTEGER}, #{empName,jdbcType=VARCHAR}, ",
      "#{gender,jdbcType=CHAR}, #{email,jdbcType=VARCHAR}, #{deptId,jdbcType=INTEGER})"
  })
  int insert(Employee record);

  int insertSelective(Employee record);

  List<Employee> selectByExample(EmployeeExample example);

  @Select({
      "select",
      "id, emp_name, gender, email, d_id",
      "from tbl_emp",
      "where id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("mybatis.generator.dao.EmployeeMapper.BaseResultMap")
  Employee selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

  int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

  int updateByPrimaryKeySelective(Employee record);

  @Update({
      "update tbl_emp",
      "set emp_name = #{empName,jdbcType=VARCHAR},",
      "gender = #{gender,jdbcType=CHAR},",
      "email = #{email,jdbcType=VARCHAR},",
      "d_id = #{deptId,jdbcType=INTEGER}",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int updateByPrimaryKey(Employee record);
}