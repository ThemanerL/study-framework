package mybatis.generator;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author 李重辰
 * @date 2019/3/13 17:42
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = Statement.class)})
public class MyPlugin implements Interceptor {
  /**
   * 拦截目标对象的目标方法
   *
   * @param invocation /
   * @return 返回执行后的返回值
   * @throws Throwable /
   */
  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    // 执行目标方法
    Object proceed = invocation.proceed();
    System.out.println("正在包装……" + invocation.getMethod().getName());
    return proceed;
  }

  /**
   * 包装目标对象,连接链通过调用interceptor,plugin来处理四大对象,为目标对象创建一个代理对象
   *
   * @param target 将被处理的目标
   * @return 为当前target创建的动态代理
   */
  @Override
  public Object plugin(Object target) {
    // 借助该方法使用当前Interceptor拦截包装目标对象
    Object wrap = Plugin.wrap(target, this);
    System.out.println("MyPlugin.plugin" + target.toString());
    return wrap;
  }

  /**
   * 设置插件注册时的Properties属性
   *
   * @param properties 插件配置的信息
   */
  @Override
  public void setProperties(Properties properties) {
    System.out.println(properties);
  }
}
