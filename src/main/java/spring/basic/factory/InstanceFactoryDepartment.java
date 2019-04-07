package spring.basic.factory;

import spring.basic.bean.Department;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂方法类
 *
 * @author 李重辰
 * @date 2019/3/19 1:06
 */
public class InstanceFactoryDepartment {
  private Map<String, Department> departmentMap;

  public InstanceFactoryDepartment() {
    departmentMap = new HashMap<>();
    departmentMap.put("Development", new Department(1, "Development Department"));
    departmentMap.put("Custom", new Department(1, "Custom Department"));
    departmentMap.put("Maintain", new Department(1, "Maintain Department"));
  }

  public Department getDeparment(String department) {
    return departmentMap.get(department);
  }
}
