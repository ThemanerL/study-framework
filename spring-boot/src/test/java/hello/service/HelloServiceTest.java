package hello.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 李重辰
 * @date 2020/3/29 16:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
  @Autowired
  ApplicationContext context;

  @Test
  public void testHelloService() {
    boolean helloService = context.containsBean("helloService");
    Assert.assertTrue(helloService);
  }
}