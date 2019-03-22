package spring.basic.bean.annotation.repository;

/**
 * @author 李重辰
 * @date 2019/3/19 21:55
 */
public interface UserRepository {
  /**
   * 保存
   */
  default void save(){
    System.out.println("UserRepository.save");
  }
}
