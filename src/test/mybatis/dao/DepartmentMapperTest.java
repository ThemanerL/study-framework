package test.mybatis.dao;

import mybatis.bean.Department;
import mybatis.dao.DepartmentMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

/**
 * @author 李重辰
 * @date 2019/3/2 18:11
 */
public class DepartmentMapperTest {
  private EmployeeMapperTest employeeMapperTest = new EmployeeMapperTest();

  @Test
  public void addDept(){
    SqlSessionFactory sqlSessionFactory = employeeMapperTest.getSqlSessionFactory();
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
      boolean result = departmentMapper.addDept(new Department(null, "售后服务部"));
      System.out.println(result);
    }
  }

  @Test
  public void getDepts(){
    SqlSessionFactory sqlSessionFactory = employeeMapperTest.getSqlSessionFactory();
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
      List<Department> depts = departmentMapper.getDepts();
      System.out.println(depts);
    }
  }

}
