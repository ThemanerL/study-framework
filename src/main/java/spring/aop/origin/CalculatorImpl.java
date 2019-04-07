package spring.aop.origin;

import org.springframework.stereotype.Component;

/**
 * @author 李重辰
 * @date 2019/3/20 11:53
 */
@Component
public class CalculatorImpl implements Calculator {
  @Override
  public int add(int a, int b) {
    return a + b;
  }

  @Override
  public int subtract(int a, int b) {
    return a - b;
  }

  @Override
  public int divide(int a, int b) {
    int c = 0;
    try {
      if (0 == b) {
        throw new MyException("b不能为0!!");
      }
      c = a / b;
    } catch (MyException e) {
      e.getMessage();
    }
    return c;
  }

  @Override
  public int multiply(int a, int b) {
    return a * b;
  }
}

class MyException extends Exception {

  private static final long serialVersionUID = -3468180610043280464L;

  MyException(String message) {
    super(message);
  }
}
