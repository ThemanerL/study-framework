package hello.service;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 李重辰
 * @date 2020/3/29 16:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {

  @Autowired
  DataSource dataSource;

  @Autowired
  ApplicationContext context;

  @Test
  public void testHelloService() {
    boolean helloService = context.containsBean("helloService");
    Assert.assertTrue(helloService);
  }

  @Test
  public void jdbcConnectTest() {
    System.out.println(dataSource.getClass());
    try (Connection connection = dataSource.getConnection()) {
      System.out.print(connection.getAutoCommit());
      System.out.print(connection.getSchema());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}