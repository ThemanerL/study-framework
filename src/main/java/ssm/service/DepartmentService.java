package ssm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.bean.Department;
import ssm.dao.DepartmentMapper;

import java.util.Collection;

/**
 * @author 李重辰
 * @date 2019/4/3 13:49
 */
@Service
public class DepartmentService {

  final private DepartmentMapper departmentMapper;

  @Autowired
  public DepartmentService(DepartmentMapper departmentMapper) {
    this.departmentMapper = departmentMapper;
  }

  public Collection<Department> getDepts() {
    return departmentMapper.selectByExample(null);
  }
}
