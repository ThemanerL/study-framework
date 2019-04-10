package interview;

/**
 * 通过匿名内部类实现类
 * 因为内部静态类是要在有引用了以后才会装载到内存的。所以在你第一次调用
 * getInstance()之前，SingletonHolder是没有被装载进来的，只有在你第一次调用了getInstance()之后，里面涉及到了return SingletonHolder.instance; 产生了对SingletonHolder的引用，内部静态类的实例才会真正装载。实现了懒加载，而final的修饰确保不会被重新赋值
 * 1、由 new 关键字创建一个类的实例
 * 2、调用 Class.forName() 方法
 * 3、调用某个 ClassLoader 实例的 loadClass() 方法
 *
 * @author 李重辰
 * @date 2019/4/8 19:43
 */
public class Singleton {
  private Singleton() {
  }

  public static final Singleton getInstance() {
    return LazyHolder.INSTANCE;
  }

  private static class LazyHolder {
    private static final Singleton INSTANCE = new Singleton();
  }
}

/**
 * 双重效验锁
 */
class Singleton2 {
  private static volatile Singleton2 instance = null;

  private Singleton2() {
  }

  public static Singleton2 getInstance() {
    if (instance == null) {
      synchronized (Singleton2.class) {
        // 2
        if (instance == null) {
          instance = new Singleton2();
        }
      }
    }
    return instance;
  }
}
