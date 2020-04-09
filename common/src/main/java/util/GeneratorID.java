package util;

/**
 * 生成分布式ID
 *
 * @author 李重辰
 * @date 2020/4/9 16:20
 */
public class GeneratorID {
  private static final long START_TIME = 1577808000000L;
  private static long LAST_STAMP = -1L;

  public static void main(String[] args) {
    long a = 5L << 20;
    System.out.println("a = " + toBinary(a));
    long b = 8L << 10;
    System.out.println("b = " + toBinary(b));
    long c = 10L;
    System.out.println("c = " + toBinary(c));
    long d = a | b | c;
    System.out.println("d = " + toBinary(d) + d);
    System.out.println("前41位" + time());
    long l = toLong("11111111111111111111111111111111111111111");
    System.out.println("41位最大long值 " + l);
    System.out.println("222 转化为long " + toLong("101"));
    System.out.println(l + START_TIME);
  }

  private static String toBinary(long num) {
    StringBuilder binary = new StringBuilder(Long.toBinaryString(num));
    while (binary.length() < 64) {
      binary.insert(0, "0");
    }
    return binary.toString();
  }

  private static long toLong(String num) {
    return Long.valueOf(num, 2);
  }

  private static String time() {
    long now = System.currentTimeMillis();
    // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过，抛出异常
    if (now < LAST_STAMP) {
      throw new RuntimeException(String.format("系统时间错误！%d 毫秒内拒绝生成雪花ID", START_TIME - now));
    }
    // 上次生成ID的时间戳
    LAST_STAMP = now;
    long l = now - START_TIME;
    StringBuilder sb = new StringBuilder(Long.toBinaryString(l));
    while (sb.length() < 64) {
      sb.insert(0, 0);
    }
    return sb.substring(23, 64);
  }
}
