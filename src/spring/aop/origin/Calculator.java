package spring.aop.origin;

/**
 * @author 李重辰
 * @date 2019/3/20 11:46
 */
public interface Calculator {
  /**
   * 加
   * @param a /
   * @param b /
   * @return /
   */
  int add(int a, int b);

  /**
   * 减
   * @param a /
   * @param b /
   * @return /
   */
  int subtract(int a, int b);

  /**
   * 乘
   * @param a 被除数
   * @param b 除数
   * @return /
   */
  int divide(int a, int b);

  /**
   * 除
   * @param a /
   * @param b /
   * @return /
   */
  int multiply(int a, int b);
}
