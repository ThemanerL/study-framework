package spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestAttributeListener;

/**
 * 日志 切面,将该类声明为一个切面
 * @author 李重辰
 * @date 2019/3/20 13:45
 */
@Component
@Aspect
public class LoggingAspect {
  /**
   * 抽取切点以公用
   */
  @Pointcut("execution(* spring.transaction.service.BookShop.*(..))")
  void declareJoinPointExpression(){}

  /**
   * 通过Before指定该方法在目标方法执行前执行
   */
  @Before("declareJoinPointExpression()")
  public void beforeMethod(JoinPoint point){
    System.out.println("LoggingAspect.before " + point.getSignature().getName() + " Method");
  }
  /**
   * 通过After指定该方法在目标方法执行后执行
   */
  @After("declareJoinPointExpression()")
  public void afterMethod(JoinPoint point){
    Object[] args = point.getArgs();
    System.out.println("用户 " + args[0] + " 购买 " + args[1] + "本" +args[2]+"书成功!");
  }

  @AfterReturning(value = "declareJoinPointExpression()",returning = "o")
  public void afterReturning(JoinPoint point, Object o){
    Object[] args = point.getArgs();
    System.out.println("用户 " + args[0] + " 购买 " + args[1] + "本" +args[2]+"书成功!");
  }
}
