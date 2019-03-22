package spring.aop.origin;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 一个传统的动态代理类.与Spring无关,是AOP编程的基础
 * @author 李重辰
 * @date 2019/3/20 12:28
 */
@Component
public class CalculatorProxy {
  private Calculator target;

  public CalculatorProxy(Calculator calculator) {
    this.target = calculator;
  }

  public Calculator  getCalculatorProxy(){
  /*
   * public static Object newProxyInstance(ClassLoader loader,
   *                                       Class<?>[] interfaces,
   *                                       InvocationHandler h)
   * @loader 使用哪个类加载器来加载代理类
   * @interfaces 这个类实现了哪些接口
   * @h 在该方法调度器中对将与执行的方法做调用前后的一系列的处理
   */
    return (Calculator) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Calculator.class}, new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("在真实方法调用之前");
        Object o = method.invoke(target, args);
        System.out.println("在真实方法调用之后");
        return o;
      }
    });
  }
}
