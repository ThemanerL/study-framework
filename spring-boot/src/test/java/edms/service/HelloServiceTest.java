package edms.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 李重辰
 * @date 2020/8/30 22:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {

  @Autowired(required = false)
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