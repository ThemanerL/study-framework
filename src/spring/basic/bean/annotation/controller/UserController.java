package spring.basic.bean.annotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spring.basic.bean.annotation.repository.UserRepository;

import javax.annotation.Resource;

/**
 * @author 李重辰
 * @date 2019/3/19 21:55
 */
@Controller
public class UserController {
  private final UserRepository userRepository;

  public UserController(@Autowired UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void execute() {
    System.out.println("UserController.execute");
    userRepository.save();
  }

}
