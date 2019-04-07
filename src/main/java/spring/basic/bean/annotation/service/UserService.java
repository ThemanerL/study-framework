package spring.basic.bean.annotation.service;

import org.springframework.stereotype.Service;

/**
 * @author 李重辰
 * @date 2019/3/19 21:56
 */
@Service
public class UserService {
  public void add() {
    System.out.println("UserService.add");
  }
}
