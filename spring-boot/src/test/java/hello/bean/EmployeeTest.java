package hello.bean;

import edms.bean.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 李重辰
 * @date 2020/3/28 11:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {
  @Autowired
  private Employee employee;

  @Test
  public void testApplicationImport() {
    System.out.println(employee);
  }
}