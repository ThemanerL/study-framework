package interview;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author 李重辰
 * @date 2019/4/9 11:55
 */
public class CodeTest {

  /**
   * 锁！
   *
   * @param args
   */
  public static void main(String[] args) {
    final List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 3, 4));
    final List<Integer> list2 = new ArrayList<>(Arrays.asList(7, 8, 4, 5, 2, 12));
    new Thread(() -> {
      synchronized (list1) {
        for (int i = 0; i < 4; i++) {
          System.out.print(list1.get(i) + ",");
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (list2) {
          for (int i = 0; i < 4; i++) {
            System.out.print(list2.get(i) + ",");
          }
        }
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (list2) {
          for (int i = 0; i < 4; i++) {
            System.out.print(list1.get(i) + ",");
          }
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          synchronized (list1) {
            for (int i = 0; i < 4; i++) {
              System.out.print(list1.get(i) + ",");
            }
          }
        }
      }
    }).start();
  }

  @Test
  public void iteratorTest() {
    ArrayList<String> array = new ArrayList<String>();
    array.add(0, "hello world");
    System.out.println(array);
  }

  @Test
  public void binarySearchTest() {
    int[] its = {1, 2, 3, 4, 5, 11, 32, 41, 46, 48, 56};
    int i = 32;
    System.out.println(new CodeTest().binarySearch(i, its));

    ArrayList<Integer> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    System.out.print(list1.getClass() == list2.getClass());
  }

  private int binarySearch(int i, int[] its) {
    int left = 0, right = its.length - 1;
    while (left <= right) {
      int mid = (left + right) >> 1;
      if (its[mid] > i) {
        right = mid - 1;
      } else if (its[mid] < i) {
        left = mid + 1;
      } else {
        return its[mid];
      }
    }
    return -1;
  }

}

class Single {
  final String a = "sdfas", b = "212fas";

  public static void main(String[] args) {
    String a = "sdfas", b = "sdfas";

    System.out.println(a == b);
    Single single = new Single();
    Runnable th1 = single.new Thread1();
    Runnable th2 = single.new Thread2();
    ThreadFactory threadFactory = Executors.defaultThreadFactory();
    threadFactory.newThread(th1).start();
    threadFactory.newThread(th2).start();

  }

  class Thread1 implements Runnable {
    @Override
    public void run() {
      synchronized (a) {
        System.out.println(a);
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (b) {
          System.out.println(b);
        }
      }
    }
  }

  class Thread2 implements Runnable {
    @Override
    public void run() {
      synchronized (b) {
        System.out.println(b);
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (a) {
          System.out.println(a);
        }
      }
    }
  }
}

class Singleton1 {
  public static Singleton1 INSTANCE = new Singleton1();

  private Singleton1() {
  }
}

class Singleton1Test {
  public static void main(String[] args) {
    Singleton1 singleton1 = Singleton1.INSTANCE;
    Singleton1 singleton2 = Singleton1.INSTANCE;
    System.out.println(singleton1 == singleton2);
  }
}
