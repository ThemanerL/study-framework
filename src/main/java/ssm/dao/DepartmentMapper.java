package ssm.dao;

import org.apache.ibatis.annotations.Param;
import ssm.bean.Department;
import ssm.bean.DepartmentExample;

import java.util.List;

public interface DepartmentMapper {
  long countByExample(DepartmentExample example);

  int deleteByExample(DepartmentExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Department record);

  int insertSelective(Department record);

  List<Department> selectByExample(DepartmentExample example);

  Department selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

  int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

  int updateByPrimaryKeySelective(Department record);

  int updateByPrimaryKey(Department record);
}