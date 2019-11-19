package mybatis.generator.dao;

import mybatis.generator.bean.Department;
import mybatis.generator.bean.DepartmentExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DepartmentMapper {
  long countByExample(DepartmentExample example);

  int deleteByExample(DepartmentExample example);

  @Delete({
      "delete from tbl_dept",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteByPrimaryKey(Integer id);

  @Insert({
      "insert into tbl_dept (id, dept_name)",
      "values (#{id,jdbcType=INTEGER}, #{deptName,jdbcType=VARCHAR})"
  })
  int insert(Department record);

  int insertSelective(Department record);

  List<Department> selectByExample(DepartmentExample example);

  @Select({
      "select",
      "id, dept_name",
      "from tbl_dept",
      "where id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("mybatis.generator.dao.DepartmentMapper.BaseResultMap")
  Department selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

  int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

  int updateByPrimaryKeySelective(Department record);

  @Update({
      "update tbl_dept",
      "set dept_name = #{deptName,jdbcType=VARCHAR}",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int updateByPrimaryKey(Department record);
}