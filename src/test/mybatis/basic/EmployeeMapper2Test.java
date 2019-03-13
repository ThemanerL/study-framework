package test.mybatis.basic;

import mybatis.basic.bean.Employee;
import mybatis.basic.dao.EmployeeMapper2;
import mybatis.basic.dao.MyUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Random;

/**
 * @author 李重辰
 * @date 2019/3/2 17:52
 */
public class EmployeeMapper2Test {

  @Test
  public void getEmpById() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper2 employeeMapper2 = sqlSession.getMapper(EmployeeMapper2.class);
      Employee employee = employeeMapper2.getEmpById(87);
      System.out.println(employee);
    }
  }

  @Test
  public void getEmpsByDeptId() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper2 employeeMapper2 = sqlSession.getMapper(EmployeeMapper2.class);
      List<Employee> empsByDeptId = employeeMapper2.getEmpsByDeptId(1);
      System.out.println(empsByDeptId.size());
    }
  }

  @Test
  public void getEmpAndDept() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper2 employeeMapper2 = sqlSession.getMapper(EmployeeMapper2.class);
      Employee employee = employeeMapper2.getEmpAndDept(1);
      System.out.println(employee);
      System.out.println(employee.getDepartment());
    }
  }

  @Test
  public void getEmpByIdStep() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper2 employeeMapper2 = sqlSession.getMapper(EmployeeMapper2.class);
      Employee employee = employeeMapper2.getEmpByIdStep(6787);
      System.out.println(employee.getEmail());
//      System.out.println(employee.getDepartment());
    }
  }


  @Test
  public void addEmpDeptId() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper2 employeeMapper2 = sqlSession.getMapper(EmployeeMapper2.class);
      boolean flag = employeeMapper2.addEmpDeptId(0, new Random(System.currentTimeMillis()).nextInt(3) + 1);
      System.out.println(flag);
    }
  }
}
