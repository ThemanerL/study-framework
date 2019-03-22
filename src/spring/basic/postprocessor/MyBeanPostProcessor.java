package spring.basic.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 李重辰
 * @date 2019/3/18 23:29
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    String judge = "emp1Salary";
    if (judge.equals(beanName)){
      System.out.print("MyBeanPostProcessor.postProcessBeforeInitialization");
      System.out.println("bean = [" + bean + "], beanName = [" + beanName + "]");
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    String judge = "emp1Salary";
    if (judge.equals(beanName)) {
      System.out.print("MyBeanPostProcessor.postProcessAfterInitialization");
      System.out.println("bean = [" + bean + "], beanName = [" + beanName + "]");
    }
    return bean;
  }
}
