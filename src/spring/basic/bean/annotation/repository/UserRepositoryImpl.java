package spring.basic.bean.annotation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import spring.basic.bean.annotation.service.UserService;

/**
 * @author 李重辰
 * @date 2019/3/19 21:55
 */
@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

  private UserService userService;

  public UserRepositoryImpl( @Autowired UserService userService) {
    this.userService = userService;
  }

  @Override
  public void save() {
    System.out.println("UserRepositoryImpl.save");
    userService.add();
  }
}
