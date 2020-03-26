package mybatis.basic.bean;

/**
 * @author 李重辰
 * @date 2019/3/14 11:07
 */
public enum EmpStatus {
  /**
   * 登入
   */
  LOGIN(200, "已登录"),

  /**
   * 登出
   */
  LOGOUT(400, "登录"),

  /**
   * 忙碌
   */
  BUSY(500, "忙碌"),

  /**
   * 不存在
   */
  ERROR(0, "不存在");

  private int code;
  private String msg;

  EmpStatus(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  /**
   * 按照不同的状态码返回相应的状态
   *
   * @param code 状态码
   * @return status
   */
  public static EmpStatus getEmpStatusByCode(int code) {
    switch (code) {
      case 200:
        return LOGIN;
      case 400:
        return LOGOUT;
      case 500:
        return BUSY;
      default:
        return ERROR;
    }
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
