import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 李重辰
 * @date 2019/3/31 13:03
 */
public class TestDateFormat {
  public static void main(String[] args) {
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
    System.out.println(format.format(date));
  }
}
