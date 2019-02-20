package mybatis.controller;

import mybatis.bean.Employee;
import mybatis.dao.EmployeeMapper;
import mybatis.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 此类被test.mybatis.dao.EmployeeMapperTest替代
 * @author 李重辰
 * @date 2019/2/19 17:55
 */
public class EmployeeTest {
  public static void main(String[] args) {
    EmployeeTest test = new EmployeeTest();
    System.out.println("______ByXml___");
    System.out.println(test.printData());
    System.out.println("______ByInterFaceXml___");
    String result =test.printEmpByInterfaceXml(1);
    System.out.println(result);
    result =test.printEmpByInterfaceXml(2);
    System.out.println(result);
    System.out.println("______ByInterFace___");
    System.out.println(test.getEmpByInterface());
  }

  /**
   * 1、根据XML文件配置文件（全局配置文件），创建一个SqlSessionFactory对象
   * 2、全局配置文件中链接到Sql映射文件，此处为EmployeeSQL.xml。该文件中配置了每一个sql以及sql的封装规则
   * 3、将Sql文件注册到全局配置文件
   * 4.1 根据全局配置文件得到一个SqlSessionFactory对象
   * 4.2 使用SqlSessionFactory，获取到sqlSession对象，使用该对象来执行sql语句
   * 4.3 一个sqlSession就是代表与数据库的一次会话、使用sql的唯一标示来告诉Mybatis执行哪个sql，sql都是保存在sql映射文件中
   * @return employee
   */
  private String printData() {
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
    Employee employee = null ;
    try(SqlSession session = sqlSessionFactory.openSession()) {
      employee = session.selectOne("mybatis.dao.EmployeeMapper.getEmpByID",1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return String.valueOf(employee);
  }

  String printEmpByInterfaceXml(Integer id){
    // 1、获取sqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
    // 2、获取sqlSession对象
    Employee employee = null;
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      // 3、获取接口的实现类对象
      // MyBatis会为接口自动的创建一个代理对象，代理对象去执行数据库操作
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      employee = employeeMapper.getEmpByID(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return String.valueOf(employee);
  }

  String getEmpByInterface(){
    SqlSessionFactory sessionFactory = getSqlSessionFactory();
    String str = null;
    try(SqlSession sqlSession = sessionFactory.openSession()){
      EmployeeMapperAnnotation empMapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
      Employee employee = empMapper.getEmpById(1);
      str = String.valueOf(employee);
    }catch (Exception e){
      e.printStackTrace();
    }
    return str;
  }

  /**
   * 根据配置文件获取一个SqlSessionFactory对象
   * @return /
   */
  SqlSessionFactory getSqlSessionFactory(){
    String resource = "mybatis/mybatis-config.xml";
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new SqlSessionFactoryBuilder().build(inputStream);
  }
}
