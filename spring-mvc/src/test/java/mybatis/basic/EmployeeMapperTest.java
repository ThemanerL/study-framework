package mybatis.basic;

import mybatis.basic.bean.Department;
import mybatis.basic.bean.EmpStatus;
import mybatis.basic.bean.Employee;
import mybatis.basic.dao.EmployeeMapper;
import mybatis.basic.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import util.MyUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 1、接口式编程
 * 原生：   Dao     ==》 DaoImpl
 * MyBatis：Mapper  ==》 xxMapper.xml
 * 2、SqlSession代表与数据库的一次会话：用完必须释放资源
 * 3、SqlSessionFactory.openSession()获取到的session不自动提交
 * 4、SqlSession与Connection一样，都是非线程安全的。每次使用都应该获取新的对象。
 * 5、mapper接口没有实现类。但是myBatis会为这个接口生成一个代理对象。
 * (将接口与XML进行绑定)
 * EmployeeMapper employeeMapper = SqlSession.getMapper(EmployeeMapper.class)
 * 6、myBatis的全局配置文件：包含数据库连接池信息，事务管理器信息，系统运行环境信息等等。
 * sql映射文件：保存了每一个sql语句的映射信息。通过该文件抽取sql语句。
 *
 * @author 李重辰
 * @date 2019/2/19 11:01
 */
public class EmployeeMapperTest {
  private boolean result;

  SqlSessionFactory getSqlSessionFactory() {
    String resource = "mybatis/mybatis-config.xml";
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new SqlSessionFactoryBuilder().build(inputStream);
  }

  /**
   * SQL文件置于XML中，XML注册到mybatis-config.xml中
   * 通过SqlSession.selectOne指定sql的唯一标识(写全路径),和参数来操作数据库
   * <p>
   * 不推荐
   */
  @Test
  public void getEmpById() {
    try (SqlSession session = MyUtil.getSession()) {
      session.clearCache();
      Employee employee = session.getMapper(EmployeeMapper.class).getEmpByID(1);
      System.out.println(employee.getStatus());
      System.out.println(employee);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * sql写在映射文件中，但是映射文件的命名空间与某个接口名称相同。
   * 通过SqlSession.getMapper获取接口的实现类对象
   * 通过这个实现类对象调用接口的操作数据库的方法
   * (该方法的名字与将被执行的sql语句的唯一标识名称完全一致)来操作数据库
   * <p>
   * 最推荐
   */
  @Test
  public void printEmpByInterfaceXml() {
    // 1、获取sqlSessionFactory对象
    // 2、获取sqlSession对象
    Employee employee = null;
    try (SqlSession sqlSession = MyUtil.getSession()) {
      // 3、获取接口的实现类对象
      // MyBatis会为接口自动的创建一个代理对象，代理对象去执行数据库操作
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      employee = employeeMapper.getEmpByID(1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(employee);
  }

  /**
   * sql通过注解的形式写在接口中
   * 通过SqlSession.getMapper获取对象实例，再调用相应的操作数据库的方法。
   * <p>
   * 只有简单的sql或者不重要的sql语句出于快速开发的缘故可以这样写。
   */
  @Test
  public void getEmpByInterface() {
    SqlSessionFactory sessionFactory = getSqlSessionFactory();
    String str = null;
    try (SqlSession sqlSession = sessionFactory.openSession()) {
      EmployeeMapperAnnotation empMapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
      Employee employee = empMapper.getEmpById(2);
      str = String.valueOf(employee);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(str);
  }

  @Test
  public void getEmpByIdXLastName() {
    String str = null;
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class);
      Employee employee = empMapper.getEmpByIdXLastName(1, "UpdateT");
      str = String.valueOf(employee);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(str);
  }

  @Test
  public void getEmpsByLastNameLike() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      List<Employee> emps = employeeMapper.getEmpsByLastNameLike("码");
      for (Employee emp : emps) {
        System.out.println(emp);
      }
    }
  }

  @Test
  public void getEmpByIDReturnMap() {
    SqlSessionFactory sessionFactory = getSqlSessionFactory();
    try (SqlSession sqlSession = sessionFactory.openSession()) {
      Map<String, Object> map = sqlSession.getMapper(EmployeeMapper.class).getEmpByIDReturnMap(1);
      for (Map.Entry<String, Object> set : map.entrySet()) {
        System.out.println("Key: " + set.getKey() + "\tValue: " + set.getValue());
      }
    }
  }

  @Test
  public void getEmpByLastNameReturnMap() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      Map<String, Employee> map = sqlSession.getMapper(EmployeeMapper.class).getEmpByLastNameReturnMap("%码");
      for (Map.Entry<String, Employee> set : map.entrySet()) {
        System.out.println("Key: " + set.getKey() + "\tValue: " + set.getValue());
      }
    }
  }

  @Test
  public void getEmpByMap() {
    SqlSessionFactory sessionFactory = getSqlSessionFactory();
    try (SqlSession sqlSession = sessionFactory.openSession(true)) {
      EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
      Map<String, Object> map = new HashMap<>(16);
      map.put("id", 1);
      map.put("lastName", "'updateT'");
      map.put("tableName", "tbl_employee");
      System.out.println(mapper.getEmpByMap(map));
    }
  }

  @Test
  public void addEmp() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      Employee employee = new Employee(null, MyUtil.getName(), String.valueOf(new Random(System.currentTimeMillis()).nextInt(2)), MyUtil.getRandEmail(), new Department(new Random(System.currentTimeMillis()).nextInt(3) + 1, null));
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      result = employeeMapper.addEmp(employee);
      System.out.print(employee);
      sqlSession.commit();
    }
  }

  @Test
  public void addEmpBatch() {
    long startTime = System.currentTimeMillis();
    try (SqlSession sqlSession = MyUtil.getSession(ExecutorType.BATCH)) {
      int records = 10000;
      for (int i = 0; i < records; i++) {
        int deptId = new Random(System.currentTimeMillis()).nextInt(3);
        Employee employee = new Employee(MyUtil.getName(), String.valueOf(Math.abs(deptId - 1)), MyUtil.getRandEmail(), new Department(deptId + 1, null), EmpStatus.values()[deptId]);
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        result = employeeMapper.addEmp(employee);
      }
      sqlSession.commit();
    }
    System.out.println(System.currentTimeMillis() - startTime);
  }

  @Test
  public void updateEmp() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      result = employeeMapper.updateEmp(new Employee(1, "updateT", "0", "updater@qw"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void deleteEmpById() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      result = employeeMapper.deleteEmpById(5);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(result);
  }

  @Test
  public void deleteEmpEmail() {
    int result = 0;
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      result = employeeMapper.deleteEmpEmail("add3");
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(result);
  }
}
