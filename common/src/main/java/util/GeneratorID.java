package util;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * 生成分布式ID
 * 定制雪花算法，第一位表示正负号不适用，前四十一位使用时间戳，中间十位使用机器信息（包括五位的hostName和hostAddress），后十二位
 *
 * @author 李重辰
 * @date 2020/4/9 16:20
 */
public class GeneratorID {
  /**
   * 取2020-01-01 00:00:00作为起始时间
   */
  private static final long START_TIME = 1577808000000L;
  private static long LAST_STAMP = -1L;
  /**
   * 上一次的毫秒内序列值
   */
  private static long LAST_SEQ = 0L;
  /**
   * 毫秒内序列值的上限
   */
  final static long MAX_SQL = 4095L;
  /**
   * 时间戳部分的最大长度
   */
  final static long MAX_STAMP_LENGTH = 64L;

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

    System.out.println("最终生成效果：" + getId());

    String s = Long.toBinaryString(MAX_SQL);
    System.out.println(s);
  }

  private synchronized static long getId() {
    return toLong(time()) << 22 | getHostAddressId() << 17 | getHostNameId() << 12 | LAST_SEQ;
  }

  /**
   * 将long型的数字转化为二进制字符串
   */
  private static String toBinary(long num) {
    StringBuilder binary = new StringBuilder(Long.toBinaryString(num));
    while (binary.length() < 64) {
      binary.insert(0, "0");
    }
    return binary.toString();
  }

  /**
   * 将二进制的字符串转化为long型的数字
   */
  private static long toLong(String num) {
    return Long.valueOf(num, 2);
  }

  /**
   * 获取雪花算法生成的ID的前41位 （时间戳）
   *
   * @return 时间部分
   */
  private static String time() {
    long now = System.currentTimeMillis();
    // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过，抛出异常
    if (now < LAST_STAMP) {
      throw new RuntimeException(String.format("系统时间错误！%d 毫秒内拒绝生成雪花ID", START_TIME - now));
    }
    // 如果上一个ID和这一个ID都在同一个毫秒内生成
    if (now == LAST_STAMP) {
//      在下一行这种写法下，如果LAST_SQL + 1 = MAX_SQL，则LAST_SQL = 0;
//      LAST_SEQ = (LAST_SEQ+1) & MAX_SQL;
      LAST_SEQ++;
      if (LAST_SEQ > MAX_SQL) {
        now = nextMillis(LAST_STAMP);
        LAST_SEQ = 0;
      }
    } else {
      LAST_SEQ = 0;
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

  private static long nextMillis(long lastStamp) {
    long now = System.currentTimeMillis();
    while (lastStamp >= now) {
      lastStamp = System.currentTimeMillis();
    }
    return now;
  }

  private void getHostInfo() {
    try {
      System.out.print(Inet4Address.getLocalHost().getHostAddress());
      System.out.print(Inet4Address.getLocalHost().getHostName());
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }

  /**
   * @return 根据机器当前IP生产的ID
   */
  private static int getHostAddressId() {
    try {
      return getHostId(Inet4Address.getLocalHost().getHostAddress(), 31);
    } catch (UnknownHostException e) {
      return new Random().nextInt(31);
    }
  }

  /**
   * @return 当前机器名称生产的ID
   */
  private static int getHostNameId() {
    try {
      return getHostId(Inet4Address.getLocalHost().getHostAddress(), 31);
    } catch (UnknownHostException e) {
      return new Random().nextInt(31);
    }
  }

  /**
   * 获取字符串S的字符数组，将数组的元素逐个相加之后对（max + 1）取余
   *
   * @param s   源字符串
   * @param max 最大值
   * @return 机器ID
   */
  private static int getHostId(String s, int max) {
    byte[] bytes = s.getBytes();
    int sum = 0;
    for (int b : bytes) {
      sum += b;
    }
    return sum % (max + 1);
  }
}
