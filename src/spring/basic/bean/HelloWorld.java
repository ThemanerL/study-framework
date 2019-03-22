package spring.basic.bean;

/**
 * @author 李重辰
 * @date 2019/3/16 17:27
 */
public class HelloWorld {

  private String name;

  public HelloWorld() {
  }

  public HelloWorld(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void hello( ){
    System.out.println("Hello" + name);
  }
}
