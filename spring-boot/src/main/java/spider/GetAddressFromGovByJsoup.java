package spider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 右键运行Main方法，将开始爬取国家统计局省市县相关资料
 * 如果遇到部分网页爬取失败将会输出到控制台，请手动处理
 *
 * @author 74765
 */
@Slf4j
public class GetAddressFromGovByJsoup {

  static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  static String rootUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/";

  public static void main(String[] args) {
    getProvinceInfo(rootUrl);
  }

  /**
   * 省
   */
  private static void getProvinceInfo(String url) {
    long start = System.currentTimeMillis();
    log.info("开始爬取省市县数据{}", LocalDateTime.now());
    //1.生成httpclient，相当于该打开一个浏览器
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    //2.创建get请求，相当于在浏览器地址栏输入 网址
    HttpGet request = new HttpGet(url);
    //设置请求头，将爬虫伪装成浏览器
//    request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
    try {
      //3.执行get请求，相当于在输入地址栏后敲回车键
      response = httpClient.execute(request);
      //4.判断响应状态为200，进行处理
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        Document document = getDocument(response);

        // 像js一样，通过标签获取title
        Elements aList = document.select(".provincetr").select("a");
        for (Element a : aList) {
          String province = a.text();
          String nextHref = a.attr("href");
          long provinceStart = System.currentTimeMillis();
          System.out.println(String.format("%s 开始处理：%s ", LocalDateTime.now().format(dateTimeFormatter), province));
          getCityInfo(rootUrl + nextHref, province);
          System.out.println(String.format("%s 处理完成：%s  耗时 %d 秒", LocalDateTime.now().format(dateTimeFormatter), province, (System.currentTimeMillis() - provinceStart) / 1000));
        }
      } else {
        //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
        log.error("省 返回状态不是200 URL：" + url + " //状态" + response.getStatusLine().getStatusCode());
      }
      log.info("省市县数据爬取完毕{}  共耗时{}分钟", LocalDateTime.now().format(dateTimeFormatter), (System.currentTimeMillis() - start) / 6_000);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      //6.关闭
      HttpClientUtils.closeQuietly(response);
      HttpClientUtils.closeQuietly(httpClient);
    }
  }

  /**
   * 市
   */
  private static void getCityInfo(String url, String province) {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    HttpGet request = new HttpGet(url);
    try {
      response = httpClient.execute(request);
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        Document document = getDocument(response);
        Elements aList = document.select(".citytr td:nth-child(2) a");
        for (Element a : aList) {
          String city = a.text();
          String suffix = getSuffer(a);
          getCountyInfo(rootUrl + suffix, province, city);
        }
      } else {
        log.error("市 返回状态不是200 URL：" + url + " //状态" + response.getStatusLine().getStatusCode());
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      HttpClientUtils.closeQuietly(response);
      HttpClientUtils.closeQuietly(httpClient);
    }
  }

  /**
   * 区县
   */
  private static void getCountyInfo(String url, String province, String city) {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    HttpGet request = new HttpGet(url);
    List<String> cityUrlPartList = Arrays.asList(url.split("/"));
    try {
      response = httpClient.execute(request);
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        Document document = getDocument(response);

        Elements aList = document.select(".countytr td:nth-child(2) a");
        for (Element a : aList) {
          String county = a.text();
          String suffix = getSuffer(a);
          cityUrlPartList.set(cityUrlPartList.size() - 1, suffix);
          StringBuilder target = new StringBuilder();
          for (String s : cityUrlPartList) {
            target.append(s).append("/");
          }
          target.deleteCharAt(target.length() - 1);
          getTownInfo(target.toString(), province, city, county);
        }
      } else {
        log.error("区县 返回状态不是200 URL：" + url + " //状态" + response.getStatusLine().getStatusCode());
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      //6.关闭
      HttpClientUtils.closeQuietly(response);
      HttpClientUtils.closeQuietly(httpClient);
    }
  }

  /**
   * 镇/街道
   */
  private static void getTownInfo(String url, String province, String city, String county) {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    HttpGet request = new HttpGet(url);
    try {
      response = httpClient.execute(request);
      Thread.sleep(new Random(19).nextInt(100));
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        Document document = getDocument(response);
        Elements aList = document.select(".towntr td:nth-child(2) a");
        for (Element a : aList) {
          String town = a.text();
          System.out.println("省：" + province + "\t市:" + city + "\t区:" + county + "\t街道:" + town);
        }
      } else {
        log.error("街道 返回状态不是200 URL：" + url + " //状态" + response.getStatusLine().getStatusCode());
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    } finally {
      HttpClientUtils.closeQuietly(response);
      HttpClientUtils.closeQuietly(httpClient);
    }
  }

  private static Document getDocument(CloseableHttpResponse response) throws IOException {
    byte[] bytes = EntityUtils.toByteArray(response.getEntity());
    //byte列表转为默认字符集
    String getCharsetEntity2String = new String(bytes);
    //解析
    Document getCharsetDocument = Jsoup.parse(getCharsetEntity2String);
    //字符集信息提取
    String charset = getCharsetDocument.select("meta[http-equiv=Content-Type]").attr("content").split("=")[1];
    //根据字符集重新编码成正确的
    String oriEntity = new String(bytes, charset);
    //转换为统一的utf-8
    String entity = new String(oriEntity.getBytes(), StandardCharsets.UTF_8);
    return Jsoup.parse(entity);
  }

  /**
   * 拼接链接的后缀
   */
  private static String getSuffer(Element a) {
    String nextHref = a.attr("href");
    List<String> strings = Arrays.asList(nextHref.split("/"));
    return strings.get(strings.size() - 2) + "/" + strings.get(strings.size() - 1);
  }

}

@Data
@AllArgsConstructor
class Address {
  String area1;
  String area2;
  String area3;
  String area4;
}