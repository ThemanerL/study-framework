package ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.bean.Department;
import ssm.dao.DepartmentMapper;

import java.util.Collection;

/**
 * @author 李重辰
 * @date 2019/4/13 15:07
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    final private DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public Collection<Department> getDepts() {
        return departmentMapper.selectByExample(null);
    }
}
