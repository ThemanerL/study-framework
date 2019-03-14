package mybatis.basic.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author 李重辰
 * @date 2019/3/8 19:19
 */
public class MyUtil {

  private MyUtil() {

  }

  private static SqlSessionFactory getSqlSessionFactory(){
    String resource;
    InputStream inputStream = null;
    try {
      resource = "mybatis/mybatis-config.xml";
      inputStream = Resources.getResourceAsStream(resource);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new  SqlSessionFactoryBuilder().build(inputStream);
  }

  public static SqlSession getSession() {
    return getSqlSessionFactory().openSession();
  }

  public static SqlSession getSession(ExecutorType execType) {
    return getSqlSessionFactory().openSession(execType);
  }
}
