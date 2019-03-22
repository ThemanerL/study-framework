package spring.basic.bean;

import org.springframework.stereotype.Component;

/**
 * @author 李重辰
 * @date 2019/3/18 20:10
 */
@Component
public class Salary {
  private int employeeId;
  private double baseSalary;
  private double bonus;
  private Employee employee;

  public void init(){
    System.out.println("Salary.init");
  }

  public Salary() {
  }

  public Salary(int employeeId, double baseSalary, double bonus) {
    this.employeeId = employeeId;
    this.baseSalary = baseSalary;
    this.bonus = bonus;
  }

  public Salary(int employeeId, double baseSalary, double bonus, Employee employee) {
    System.out.println("构建原型Bean");
    this.employeeId = employeeId;
    this.baseSalary = baseSalary;
    this.bonus = bonus;
    this.employee = employee;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public double getBaseSalary() {
    return baseSalary;
  }

  public void setBaseSalary(double baseSalary) {
    this.baseSalary = baseSalary;
  }

  public double getBonus() {
    return bonus;
  }

  public void setBonus(double bonus) {
    this.bonus = bonus;
  }

  @Override
  public String toString() {
    return "Salary{" +
        "employeeId=" + employeeId +
        ", baseSalary=" + baseSalary +
        ", bonus=" + bonus +
        ", employee=" + employee +
        '}';
  }
}
