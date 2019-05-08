package ssm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import ssm.bean.Department;
import ssm.bean.DepartmentExample;

import java.util.List;
/**
 * @author 李重辰
 * @date 2019/4/3 13:46
 */
@Transactional(rollbackFor = Exception.class)
public interface DepartmentMapper {
  long countByExample(DepartmentExample example);

  int deleteByExample(DepartmentExample example);

  int deleteByPrimaryKey(Integer deptId);

  int insert(Department record);

  int insertSelective(Department record);

    @Transactional(readOnly = true)
    List<Department> selectByExample(DepartmentExample example);

    @Transactional(readOnly = true)
    Department selectByPrimaryKey(Integer deptId);

  int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

  int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

  int updateByPrimaryKeySelective(Department record);

  int updateByPrimaryKey(Department record);
}