package test.spring.transaction.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.transaction.service.BookShop;

/**
 * BookShop Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 21, 2019</pre>
 */
public class BookShopTest {

  private ApplicationContext context = new ClassPathXmlApplicationContext("spring/annotationScan.xml");
  private BookShop shop = null;
  @Before
  public void before() throws Exception {
    shop = (BookShop) context.getBean("bookShop");
  }

  @After
  public void after() throws Exception {
    shop = null;
  }

  /**
   * Method: purchasBook(int userId, int bookNumber, int bookId)
   */
  @Test
  public void testPurchaseBook() throws Exception {
    shop.purchaseBook(1, 6,3);
  }


}
