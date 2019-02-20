package test.mybatis.dao;

import mybatis.bean.Employee;
import mybatis.dao.EmployeeMapper;
import mybatis.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * 1、接口式编程
 *  原生：   Dao     ==》 DaoImpl
 *  MyBatis：Mapper  ==》 xxMapper.xml
 * 2、SqlSession代表与数据库的一次会话：用完必须释放资源
 * 3、SqlSession与Connection一样，都是非线程安全的。每次使用都应该获取新的对象。
 * 4、mapper接口没有实现类。但是myBatis会为这个接口生成一个代理对象。
 *    (将接口与XML进行绑定)
 *    EmployeeMapper employeeMapper = SqlSession.getMapper(EmployeeMapper.class)
 * 5、myBatis的全局配置文件：包含数据库连接池信息，事务管理器信息，系统运行环境信息等等。
 *    sql映射文件：保存了每一个sql语句的映射信息。通过该文件抽取sql语句。
 * @author 李重辰
 * @date 2019/2/19 11:01
 */
public class EmployeeMapperTest {

  private SqlSessionFactory getSqlSessionFactory(){
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
   *
   * 不推荐
   */
  @Test
  public void getEmpByID() {
    // 1、根据XML文件配置文件（全局配置文件），创建一个SqlSessionFactory对象
    String resource = "mybatis/mybatis-config.xml";
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    // 2.获取一个SqlSession实例，能够执行一个已经映射的Sql语句
    try(SqlSession session = sqlSessionFactory.openSession()) {
      Employee employee = session.selectOne("mybatis.dao.EmployeeMapper.getEmpByID",1);
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
   *
   * 最推荐
   */
  @Test
  public void printEmpByInterfaceXml(){
    // 1、获取sqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
    // 2、获取sqlSession对象
    Employee employee = null;
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
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
   *
   * 只有简单的sql或者不重要的sql语句出于快速开发的缘故可以这样写。
   */
  @Test
  public void getEmpByInterface(){
    SqlSessionFactory sessionFactory = getSqlSessionFactory();
    String str = null;
    try(SqlSession sqlSession = sessionFactory.openSession()){
      EmployeeMapperAnnotation empMapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
      Employee employee = empMapper.getEmpById(2);
      str = String.valueOf(employee);
    }catch (Exception e){
      e.printStackTrace();
    }
    System.out.println(str);
  }

  @Test
  public void addEmp() {
    SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
    Employee employee = new Employee("小头3", "1", "add3");
    int result = 0;
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      result = employeeMapper.addEmp(employee);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(result);
  }

  @Test
  public void updateEmp() {
    SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
    int result = 0;
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      result = employeeMapper.updateEmp(new Employee(1,"updateT","0", "updater@qw"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(result);
  }

  @Test
  public void deleteEmpById() {
    SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
    int result = 0;
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      result = employeeMapper.deleteEmpById(5);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(result);
  }
}
