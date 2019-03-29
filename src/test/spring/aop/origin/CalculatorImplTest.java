package spring.aop.origin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * CalculatorImpl Tester.
 *
 * @author 李重辰
 * @version 1.0
 * @date 2019/3/20 11:53
 */
public class CalculatorImplTest {

  private Calculator calculator = null;
  @Before
  public void before() {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring/annotationScan.xml");
    calculator = (Calculator) context.getBean("calculatorImpl");
  }

  /**
   * Method: add(int a, int b)
   */
  @Test
  public void testAdd() {
    calculator.add(1,2);
  }

  /**
   * Method: subtract(int a, int b)
   */
  @Test
  public void testSubtract() {
    Assert.assertEquals(3,calculator.subtract(5,2));
  }

  /**
   * Method: divide(int a, int b)
   */
  @Test
  public void testDivide() {
    Assert.assertEquals(3,calculator.divide(6,2));
    Assert.assertEquals(0,calculator.divide(0,0));
  }

  /**
   * Method: multiply(int a, int b)
   */
  @Test
  public void testMultiply() {
    Assert.assertEquals(3,calculator.multiply(1,3));
  }

} 
