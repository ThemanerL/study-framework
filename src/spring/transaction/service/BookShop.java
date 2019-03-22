package spring.transaction.service;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.transaction.bean.Account;
import spring.transaction.bean.Book;
import spring.transaction.dao.AccountMapper;
import spring.transaction.dao.BookMapper;
import util.MyUtil;

/**
 * @author 李重辰
 * @date 2019/3/21 14:42
 */
@Service
public class BookShop {

  /**
   * 执行买书操作
   * @param userId  购买者
   * @param bookNumber 购买的数量
   * @param bookId 购买的什么书
   */
  public void purchaseBook(int userId, int bookNumber, int bookId){
    SqlSession sqlSession = MyUtil.getSession(false);
    try{
      AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
      BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
      Account account = accountMapper.selectByPrimaryKey(userId);
      Book book = bookMapper.selectByPrimaryKey(bookId);

      int currentStore = book.getNumber();
      int number = currentStore-bookNumber;
      if (number<0){
        throw new Exception("库存不足!无法购买");
      }
      book.setNumber(number);
      bookMapper.updateByPrimaryKey(book);

      long sumPrice = bookNumber*book.getPrice();
      float currentBalance = account.getBalance();
      float balance =  currentBalance - sumPrice;
      if(balance<0){
        throw new Exception("用户"+account.getName() +"账户余额不足");
      }
      account.setBalance(balance);
      accountMapper.updateByPrimaryKey(account);
      sqlSession.commit();
    }catch (Exception e){
      e.printStackTrace();
      sqlSession.rollback();
    }finally {
      sqlSession.close();
    }
  }

}
