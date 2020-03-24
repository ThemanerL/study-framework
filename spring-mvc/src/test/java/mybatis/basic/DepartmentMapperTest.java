package mybatis.basic;

import util.MyUtil;
import mybatis.basic.bean.Department;
import mybatis.basic.bean.Employee;
import mybatis.basic.dao.DepartmentMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author 李重辰
 * @date 2019/3/2 18:11
 */
public class DepartmentMapperTest {

  @Test
  public void addDept() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
      boolean result = departmentMapper.addDept(new Department(null, "售后服务部"));
      System.out.println(result);
    }
  }

  @Test
  public void getDeptById() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      Department department = sqlSession.getMapper(DepartmentMapper.class).getDeptById(1);
      System.out.println(department);
    }
  }

  @Test
  public void getDepts() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
      List<Department> depts = departmentMapper.getDepts();
      System.out.println(depts);
    }
  }

  @Test
  public void getDeptEmpsById() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
      Department department = departmentMapper.getDeptEmpsById("开发部");
      System.out.println(department.getName());
      List<Employee> employees = department.getEmployees();
      System.out.println(employees.size());
    }
  }

}
