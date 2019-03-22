package test.mybatis.basic;

import mybatis.basic.bean.Department;
import mybatis.basic.bean.Employee;
import mybatis.basic.dao.EmployeeDynamicSqlMapper;
import util.MyUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 李重辰
 * @date 2019/3/6 15:53
 */
public class EmployeeDynamicSqlMapperTest {
  private EmployeeMapperTest employeeMapperTest = new EmployeeMapperTest();
  private SqlSessionFactory sqlSessionFactory = employeeMapperTest.getSqlSessionFactory();

  @Test
  public void getEmpsByIfWhere() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
      Employee employee = new Employee(0, "李%", null, null);
      List<Employee> emps = mapper.getEmpsByIfWhere(employee);
      for (Employee e : emps) {
        System.out.println(e);
      }
    }
  }

  @Test
  public void getEmpsInnerParameter() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
      List<Employee> emps = mapper.getEmpsInnerParameter(new Employee(2342, null, null, null));
      for (Employee e : emps) {
        System.out.println(e);
      }
    }
  }

  @Test
  public void getEmpsByIfTrim() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
      Employee employee = new Employee(1434, null, null, null);
      List<Employee> emps = mapper.getEmpsByIfTrim(employee);
      for (Employee e : emps) {
        System.out.println(e);
      }
    }
  }

  @Test
  public void getEmpsByChoose() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
      Employee employee = new Employee((Integer) null, "李%", null, null);
      List<Employee> emps = mapper.getEmpsByChoose(employee);
      for (Employee e : emps) {
        System.out.println(e);
      }
    }
  }

  @Test
  public void getEmpsByForeach() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
      List<Integer> ids = new ArrayList<>(Arrays.asList(11, 2, 33, 4));
      List<Employee> emps = mapper.getEmpsByForeach(ids);
      for (Employee e : emps) {
        System.out.println(e);
      }
    }
  }

  @Test
  public void updateEmp() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
      Employee employee = new Employee(1434, "nameUpdate", null, null);
      boolean b = mapper.updateEmpBySet(employee);
      System.out.println(b);
    }
  }

  @Test
  public void addEmpsByForeach() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
      List<Employee> employees = new ArrayList<>();
      Employee employee1 = new Employee(null, "Foreach-1", "0", "one@kju.com", new Department(1));
      employees.add(employee1);
      Employee employee2 = new Employee(null, "Foreach-2", "1", "twow@kju.com", new Department(3));
      employees.add(employee2);
      Employee employee3 = new Employee(null, "Foreach-3", "1", "dfailw@kju.com", new Department(2));
      employees.add(employee3);

      int i = mapper.addEmpsByForeach(employees);
      System.out.println(i);
      System.out.println("--------------");
      for (Employee temp : employees) {
        System.out.println(temp.getId());
      }
    }
  }
}
