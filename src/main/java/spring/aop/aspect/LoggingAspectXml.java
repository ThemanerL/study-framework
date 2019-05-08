package spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

/**
 * 使用XML方式来配置切面
 *
 * @author 李重辰
 * @date 2019/3/20 13:45
 */
@Component
public class LoggingAspectXml {

    /**S
   * 通过Before指定该方法在目标方法执行前执行
   */
  public void beforeMethod(JoinPoint point) {
    System.out.println("LoggingAspect.before " + point.getSignature().getName() + " Method");
  }

  /**
   * 通过After指定该方法在目标方法执行后执行
   */
  public void afterMethod(JoinPoint point) {
    Object[] args = point.getArgs();
    System.out.println("用户 " + args[0] + " 购买 " + args[1] + "本" + args[2] + "书成功!");
  }

  public void afterReturning(JoinPoint point, Object o) {
    System.out.println("LoggingAspect.afterReturning " + point.getSignature().getName() + " Method result with " + o);
  }
}
