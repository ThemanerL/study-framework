package ssm.java.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回类
 *
 * @author 李重辰
 * @date 2019/4/1 14:46
 */
public class Message {
  private int statusCode;
  private String msg;
  private Map<String, Object> map = new HashMap<>();

  public static Message success() {
    Message message = new Message();
    message.setStatusCode(200);
    message.setMsg("Success");
    return message;
  }

  public static Message fail() {
    Message message = new Message();
    message.setStatusCode(400);
    message.setMsg("fail");
    return message;
  }

  public static Message repeat() {
    Message message = new Message();
    message.setStatusCode(444);
    message.setMsg("用户名/邮箱已经存在！");
    return message;
  }

  public Message add(String key, Object value) {
    this.getMap().put(key, value);
    return this;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Map<String, Object> getMap() {
    return map;
  }

  public void setMap(Map<String, Object> map) {
    this.map = map;
  }
}
