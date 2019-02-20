package mybatis.bean;

/**
 * @author 李重辰
 * @date 2019/2/18 15:32
 */
public class Employee {
  private int id;
  private String lastName;
  private String gender;
  private String email;

  public int getId() {
    return id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Employee( String lastName, String gender, String email) {
    this.lastName = lastName;
    this.gender = gender;
    this.email = email;
  }

  public Employee(Integer id, String lastName, String gender, String email) {
    this.id = id;
    this.lastName = lastName;
    this.gender = gender;
    this.email = email;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", lastName='" + lastName + '\'' +
        ", gender='" + gender + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
