package spider;

import lombok.Data;
import lombok.NoArgsConstructor;
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
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * 右键运行Main方法，将开始爬取国家统计局省市县相关资料
 * 如果遇到部分网页爬取失败将会输出到控制台，请手动处理，或者运行多次
 *
 * @author 74765
 */
@Slf4j
public class GetAddressFromGovByJsoup {
  AddressRecursion address = new AddressRecursion();

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    GetAddressFromGovByJsoup address = new GetAddressFromGovByJsoup();
    address.getAddressInfo("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/");
    log.info("省市县数据爬取完毕{}  共耗时{}分钟", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), (System.currentTimeMillis() - start) / 6_000);
  }

  private void getAddressInfo(String url) {
    //1.生成httpclient，相当于该打开一个浏览器
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    //2.创建get请求，相当于在浏览器地址栏输入 网址
    HttpGet request = new HttpGet(url);
    //设置请求头，将爬虫伪装成浏览器
    request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
    try {
      //3.执行get请求，相当于在输入地址栏后敲回车键
      response = httpClient.execute(request);
      //4.判断响应状态为200，进行处理
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        Document document = getDocumentAndReCharSet(response, StandardCharsets.UTF_8);
        if (!url.contains(".html")) {
          url = url + ".html";
        }
        List<String> urlList = Arrays.asList(url.split("/"));
        boolean lastStep = isLastStep(document);
        // 像js一样，通过标签获取title
        Elements addressList = getAddressList(document);
        for (Element a : addressList) {
          String areaName = a.text();
          String nextHref = a.attr("href");
          setAreaName(areaName);
          if (lastStep) {
            saveToDB(address);
            address.clearOnce();
            continue;
          }
          urlList.set(urlList.size() - 1, nextHref);
          getAddressInfo(String.join("/", urlList));
          address.clearOnce();
        }
      } else {
        //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
        System.out.println("返回状态不是200 URL：" + url + " //状态" + response.getStatusLine().getStatusCode());
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      //6.关闭
      HttpClientUtils.closeQuietly(response);
      HttpClientUtils.closeQuietly(httpClient);
    }
  }

  private Document getDocumentAndReCharSet(CloseableHttpResponse response, Charset targetCharset) throws IOException {
    byte[] bytes = EntityUtils.toByteArray(response.getEntity());
    //byte列表转为默认字符集
    String getCharsetString = new String(bytes);
    //解析
    Document charsetDocument = Jsoup.parse(getCharsetString);
    //字符集信息提取
    String charset = charsetDocument.select("meta[http-equiv=Content-Type]").attr("content").split("=")[1];
    //根据字符集重新编码成正确的
    String originEntity = new String(bytes, charset);
    //转换为统一的utf-8起源
    String entity = new String(originEntity.getBytes(), targetCharset);
    return Jsoup.parse(entity);
  }

  private boolean isLastStep(Document document) {
    Elements townList = document.select(".towntr td:nth-child(2) a");
    return !townList.isEmpty();
  }

  private Elements getAddressList(Document document) {
    Elements provinceList = document.select(".provincetr").select("a");
    Elements cityList = document.select(".citytr td:nth-child(2) a");
    Elements countryList = document.select(".countytr td:nth-child(2) a");
    Elements townList = document.select(".towntr td:nth-child(2) a");
    if (!provinceList.isEmpty()) {
      return provinceList;
    }
    if (!cityList.isEmpty()) {
      return cityList;
    }
    if (!countryList.isEmpty()) {
      return countryList;
    }
    if (!townList.isEmpty()) {
      return townList;
    }
    throw new RuntimeException("当前页面不含有目标元素");
  }

  private void setAreaName(String areaName) {
    if (StringUtils.isEmpty(address.province)) {
      address.setProvince(areaName);
    } else if (StringUtils.isEmpty(address.city)) {
      address.setCity(areaName);
    } else if (StringUtils.isEmpty(address.county)) {
      address.setCounty(areaName);
    } else if (StringUtils.isEmpty(address.town)) {
      address.setTown(areaName);
    }
  }

  private void saveToDB(AddressRecursion address) {
    // TODO: 2020/8/24 19:58 在这里进行数据持久化
  }
}

@Data
@NoArgsConstructor
class AddressRecursion {
  String province = "";
  String city = "";
  String county = "";
  String town = "";

  void clearOnce() {
    if (!StringUtils.isEmpty(town)) {
      town = "";
      return;
    }
    if (!StringUtils.isEmpty(county)) {
      county = "";
      return;
    }
    if (!StringUtils.isEmpty(city)) {
      city = "";
      return;
    }
    if (!StringUtils.isEmpty(province)) {
      province = "";
    }
  }
}
