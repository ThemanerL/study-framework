package edms.exception;

/**
 * @author 李重辰
 * @date 2020/8/18 23:53
 */
public class UserNotExistException extends RuntimeException {
  public UserNotExistException() {
    super("用户不存在");
  }
}
