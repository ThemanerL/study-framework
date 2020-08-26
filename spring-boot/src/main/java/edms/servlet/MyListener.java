package edms.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author 李重辰
 * @date 2020/8/26 23:56
 */
public class MyListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("contextInitialized 启动了");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("contextInitialized 关闭，当前项目停止了");
  }
}
