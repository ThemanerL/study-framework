package spring.basic.factory;

import spring.basic.bean.Department;

import java.util.Hashtable;
import java.util.Map;

/**
 * 静态工厂方法类
 *
 * @author 李重辰
 * @date 2019/3/19 0:34
 */
public class StaticFactoryDepartment {

  private static Map<String, Department> departmentMap = new Hashtable<>();

  static {
    departmentMap.put("Development", new Department(1, "Development Department"));
    departmentMap.put("Custom", new Department(1, "Custom Department"));
    departmentMap.put("Maintain", new Department(1, "Maintain Department"));
  }

  /**
   * 静态工厂方法
   *
   * @param department 部门名字
   * @return 部门
   */
  public static Department getDepartment(String department) {
    return departmentMap.get(department);
  }
}
