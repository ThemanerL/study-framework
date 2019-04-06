package ssm.java.dao;

import org.apache.ibatis.annotations.Param;
import ssm.java.bean.Department;
import ssm.java.bean.DepartmentExample;

import java.util.List;

/**
 * @author 李重辰
 * @date 2019/03/31 07:30
 */
public interface DepartmentMapper {
  long countByExample(DepartmentExample example);

  int deleteByExample(DepartmentExample example);

  int deleteByPrimaryKey(Integer deptId);

  int insert(Department record);

  int insertSelective(Department record);

  List<Department> selectByExample(DepartmentExample example);

  Department selectByPrimaryKey(Integer deptId);

  int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

  int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

  int updateByPrimaryKeySelective(Department record);

  int updateByPrimaryKey(Department record);
}