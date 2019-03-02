package test.mybatis.dao;

import mybatis.bean.Employee;
import mybatis.dao.EmployeeMapper2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.Random;
/**
 * @author 李重辰
 * @date 2019/3/2 17:52
 */
public class EmployeeMapper2Test {
  private EmployeeMapperTest employeeMapper = new EmployeeMapperTest();

  @Test
  public void getEmpById() {
    SqlSessionFactory sqlSessionFactory = employeeMapper.getSqlSessionFactory();
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      EmployeeMapper2 employeeMapper2 = sqlSession.getMapper(EmployeeMapper2.class);
      Employee employee = employeeMapper2.getEmpById(87);
      System.out.println(employee);
    }
  }

  @Test
  public void addEmpDeptId() {
    SqlSessionFactory sqlSessionFactory = employeeMapper.getSqlSessionFactory();
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      EmployeeMapper2 employeeMapper2 = sqlSession.getMapper(EmployeeMapper2.class);
      boolean flag = employeeMapper2.addEmpDeptId(0, new Random(System.currentTimeMillis()).nextInt(3) + 1);
      System.out.println(flag);
    }
  }
}
