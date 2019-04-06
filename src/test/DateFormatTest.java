import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 李重辰
 * @date 2019/3/31 13:03
 */
public class DateFormatTest {
  @Test
  public void testFormat() {
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
    System.out.println(format.format(date));

    List<List<String>> fatherList = new ArrayList<>();
    List<String> childList = new ArrayList<>();
    childList.add("Before");
    fatherList.add(childList);
    childList.add("after");
    System.out.println(fatherList);
  }

  @Test
  public void stringTest() {
    String s1 = "Hello";
    String s2 = "World";
    System.out.println(s1 + "---" + s2);
    change(s1, s2);
    System.out.println(s1 + "---" + s2);

    StringBuffer sb1 = new StringBuffer("Hello");
    StringBuffer sb2 = new StringBuffer("World");
    System.out.println(sb1 + "---" + sb2);
    change(sb1, sb2);
    System.out.println(sb1 + "---" + sb2);
  }

  private void change(StringBuffer sb1, StringBuffer sb2) {
    sb1 = sb2;
    sb2.append(sb1);
  }

  private void change(String s1, String s2) {
    s1 = s2;
    s2 = s1 + s2;
  }
}
