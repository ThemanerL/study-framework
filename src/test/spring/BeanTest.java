package spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.basic.bean.Department;
import spring.basic.bean.Employee;
import spring.basic.bean.Salary;
import spring.basic.bean.annotation.controller.UserController;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author 李重辰
 * @date 2019/3/16 17:29
 */
public class BeanTest {

  @Test
  public void mybatisEmployee() {
    // 1、创建Spring的ICO容器对象
    ApplicationContext context = new ClassPathXmlApplicationContext("spring/mybatisBasicBean.xml");
    // 2、从ICO容器中获取Bean实例
    Employee employee = (Employee) context.getBean("employee");
    System.out.println(employee + "\n __________________________");

    Employee employee1 = (Employee) context.getBean("employee1");
    System.out.println(employee1 + "\n __________________________");

    Department department = (Department) context.getBean("developmentDepartment");
    System.out.println(department + "\n __________________________");

    DataSource dataSource = (DataSource) context.getBean("DataSource");
    try {
      System.out.println(dataSource.getConnection());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void springEmployee() {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring/springBeanContext.xml");
    Salary emp1Salary = (Salary) context.getBean("emp1Salary");
    System.out.println(emp1Salary);
  }

  @Test
  public void departmentFactory(){
    ApplicationContext context = new ClassPathXmlApplicationContext("spring/springBeanContext.xml");
    Department department = (Department) context.getBean("department2");
    System.out.println(department);
  }

  @Test
  public void annotationSalary(){
    ApplicationContext context = new ClassPathXmlApplicationContext("spring/annotationScan.xml");

    UserController userController = (UserController)context.getBean("userController");
    userController.execute();
  }
}
