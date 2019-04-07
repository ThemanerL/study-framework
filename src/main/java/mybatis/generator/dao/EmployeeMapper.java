package mybatis.generator.dao;

import mybatis.generator.bean.Employee;
import mybatis.generator.bean.EmployeeExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmployeeMapper {
  long countByExample(EmployeeExample example);

  int deleteByExample(EmployeeExample example);

  @Delete({
      "delete from tbl_employee",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteByPrimaryKey(Integer id);

  @Insert({
      "insert into tbl_employee (id, last_name, ",
      "gender, email, dept_id)",
      "values (#{id,jdbcType=INTEGER}, #{lastName,jdbcType=VARCHAR}, ",
      "#{gender,jdbcType=CHAR}, #{email,jdbcType=VARCHAR}, #{deptId,jdbcType=INTEGER})"
  })
  int insert(Employee record);

  int insertSelective(Employee record);

  List<Employee> selectByExample(EmployeeExample example);

  @Select({
      "select",
      "id, last_name, gender, email, dept_id",
      "from tbl_employee",
      "where id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("main.java.mybatis.generator.dao.EmployeeMapper.BaseResultMap")
  Employee selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

  int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

  int updateByPrimaryKeySelective(Employee record);

  @Update({
      "update tbl_employee",
      "set last_name = #{lastName,jdbcType=VARCHAR},",
      "gender = #{gender,jdbcType=CHAR},",
      "email = #{email,jdbcType=VARCHAR},",
      "dept_id = #{deptId,jdbcType=INTEGER}",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int updateByPrimaryKey(Employee record);
}