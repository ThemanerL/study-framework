package test.mybatis.basic;

import mybatis.basic.bean.Department;
import mybatis.basic.bean.EmpStatus;
import mybatis.basic.bean.Employee;
import mybatis.basic.dao.EmployeeMapper;
import mybatis.basic.dao.EmployeeMapperAnnotation;
import util.MyUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 1、接口式编程
 * 原生：   Dao     ==》 DaoImpl
 * MyBatis：Mapper  ==》 xxMapper.xml
 * 2、SqlSession代表与数据库的一次会话：用完必须释放资源
 * 3、SqlSessionFactory.openSession()获取到的session不自动提交
 * 4、SqlSession与Connection一样，都是非线程安全的。每次使用都应该获取新的对象。
 * 5、mapper接口没有实现类。但是myBatis会为这个接口生成一个代理对象。
 * (将接口与XML进行绑定)
 * EmployeeMapper employeeMapper = SqlSession.getMapper(EmployeeMapper.class)
 * 6、myBatis的全局配置文件：包含数据库连接池信息，事务管理器信息，系统运行环境信息等等。
 * sql映射文件：保存了每一个sql语句的映射信息。通过该文件抽取sql语句。
 *
 * @author 李重辰
 * @date 2019/2/19 11:01
 */
public class EmployeeMapperTest {
  private boolean result;

  SqlSessionFactory getSqlSessionFactory() {
    String resource = "mybatis/mybatis-config.xml";
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new SqlSessionFactoryBuilder().build(inputStream);
  }

  private String getRandEmail() {
    long currentTime = System.currentTimeMillis();
    Random random = new Random(currentTime);
    String emailSuffix;
    switch (random.nextInt(5)) {
      case 0:
        emailSuffix = "@foxmail.com";
        break;
      case 1:
        emailSuffix = "@qq.com";
        break;
      case 2:
        emailSuffix = "@163.com";
        break;
      case 3:
        emailSuffix = "@gmail.com";
        break;
      case 4:
        emailSuffix = "@sina.com.cn";
        break;
      default:
        emailSuffix = "@169.com";
    }
    return String.valueOf(currentTime + emailSuffix);
  }

  /**
   * @return 获得一个姓+名
   */
  private String getName() {
    Random random = new Random(System.currentTimeMillis());
    // 598 百家姓
    String[] surName = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
        "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
        "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
        "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
        "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
        "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟",
        "徐", "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应",
        "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀",
        "羊", "于", "惠", "甄", "曲", "家", "封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山",
        "谷", "车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖", "武", "符", "刘", "景",
        "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "溥", "印", "宿", "白", "怀", "蒲", "邰", "从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠",
        "蒙", "池", "乔", "阴", "郁", "胥", "能", "苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳", "逄", "姬", "申", "扶", "堵", "冉", "宰", "郦", "雍", "却",
        "璩", "桑", "桂", "濮", "牛", "寿", "通", "边", "扈", "燕", "冀", "浦", "尚", "农", "温", "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹", "习",
        "宦", "艾", "鱼", "容", "向", "古", "易", "慎", "戈", "廖", "庾", "终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡", "国", "文", "寇", "广", "禄",
        "阙", "东", "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂", "晁", "勾", "敖", "融", "冷", "訾", "辛", "阚", "那", "简", "饶", "空",
        "曾", "毋", "沙", "乜", "养", "鞠", "须", "丰", "巢", "关", "蒯", "相", "查", "后", "荆", "红", "游", "郏", "竺", "权", "逯", "盖", "益", "桓", "公", "仉",
        "督", "岳", "帅", "缑", "亢", "况", "郈", "有", "琴", "归", "海", "晋", "楚", "闫", "法", "汝", "鄢", "涂", "钦", "商", "牟", "佘", "佴", "伯", "赏", "墨",
        "哈", "谯", "篁", "年", "爱", "阳", "佟", "言", "福", "南", "火", "铁", "迟", "漆", "官", "冼", "真", "展", "繁", "檀", "祭", "密", "敬", "揭", "舜", "楼",
        "疏", "冒", "浑", "挚", "胶", "随", "高", "皋", "原", "种", "练", "弥", "仓", "眭", "蹇", "覃", "阿", "门", "恽", "来", "綦", "召", "仪", "风", "介", "巨",
        "木", "京", "狐", "郇", "虎", "枚", "抗", "达", "杞", "苌", "折", "麦", "庆", "过", "竹", "端", "鲜", "皇", "亓", "老", "是", "秘", "畅", "邝", "还", "宾",
        "闾", "辜", "纵", "侴", "万俟", "司马", "上官", "欧阳", "夏侯", "诸葛", "闻人", "东方", "赫连", "皇甫", "羊舌", "尉迟", "公羊", "澹台", "公冶", "宗正",
        "濮阳", "淳于", "单于", "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐", "钟离", "宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空", "兀官", "司寇",
        "南门", "呼延", "子车", "颛孙", "端木", "巫马", "公西", "漆雕", "车正", "壤驷", "公良", "拓跋", "夹谷", "宰父", "谷梁", "段干", "百里", "东郭", "微生",
        "梁丘", "左丘", "东门", "西门", "南宫", "第五", "公仪", "公乘", "太史", "仲长", "叔孙", "屈突", "尔朱", "东乡", "相里", "胡母", "司城", "张廖", "雍门",
        "毋丘", "贺兰", "綦毋", "屋庐", "独孤", "南郭", "北宫", "王孙"};

    int index = random.nextInt(surName.length - 1);
    // 获得一个随机的姓氏
    String name = surName[index];

    // 从常用字中选取一个或两个字作为名
    if (random.nextBoolean()) {
      name += getChinese() + getChinese();
    } else {
      name += getChinese();
    }
    return name;
  }

  /**
   * @return 获得姓名中的"名字"
   */
  private String getChinese() {
    String str = null;
    int highPos, lowPos;
    Random random = new Random();
    // 区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
    highPos = (176 + Math.abs(random.nextInt(71)));
    random = new Random();
    // 位码，0xA0打头，范围第1~94列
    lowPos = 161 + Math.abs(random.nextInt(94));

    byte[] bArr = new byte[2];
    bArr[0] = (new Integer(highPos)).byteValue();
    bArr[1] = (new Integer(lowPos)).byteValue();
    try {
      // 区位码组合成汉字
      str = new String(bArr, "GB2312");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }


    return str;
  }


  /**
   * SQL文件置于XML中，XML注册到mybatis-config.xml中
   * 通过SqlSession.selectOne指定sql的唯一标识(写全路径),和参数来操作数据库
   * <p>
   * 不推荐
   */
  @Test
  public void getEmpById() {
    try (SqlSession session = MyUtil.getSession()) {
      session.clearCache();
      Employee employee = session.getMapper(EmployeeMapper.class).getEmpByID(1);
      System.out.println(employee.getStatus());
      System.out.println(employee);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * sql写在映射文件中，但是映射文件的命名空间与某个接口名称相同。
   * 通过SqlSession.getMapper获取接口的实现类对象
   * 通过这个实现类对象调用接口的操作数据库的方法
   * (该方法的名字与将被执行的sql语句的唯一标识名称完全一致)来操作数据库
   * <p>
   * 最推荐
   */
  @Test
  public void printEmpByInterfaceXml() {
    // 1、获取sqlSessionFactory对象
    // 2、获取sqlSession对象
    Employee employee = null;
    try (SqlSession sqlSession = MyUtil.getSession()) {
      // 3、获取接口的实现类对象
      // MyBatis会为接口自动的创建一个代理对象，代理对象去执行数据库操作
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      employee = employeeMapper.getEmpByID(1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(employee);
  }

  /**
   * sql通过注解的形式写在接口中
   * 通过SqlSession.getMapper获取对象实例，再调用相应的操作数据库的方法。
   * <p>
   * 只有简单的sql或者不重要的sql语句出于快速开发的缘故可以这样写。
   */
  @Test
  public void getEmpByInterface() {
    SqlSessionFactory sessionFactory = getSqlSessionFactory();
    String str = null;
    try (SqlSession sqlSession = sessionFactory.openSession()) {
      EmployeeMapperAnnotation empMapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
      Employee employee = empMapper.getEmpById(2);
      str = String.valueOf(employee);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(str);
  }

  @Test
  public void getEmpByIdXLastName() {
    String str = null;
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class);
      Employee employee = empMapper.getEmpByIdXLastName(1, "UpdateT");
      str = String.valueOf(employee);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(str);
  }

  @Test
  public void getEmpsByLastNameLike() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      List<Employee> emps = employeeMapper.getEmpsByLastNameLike("码");
      for (Employee emp : emps) {
        System.out.println(emp);
      }
    }
  }

  @Test
  public void getEmpByIDReturnMap() {
    SqlSessionFactory sessionFactory = getSqlSessionFactory();
    try (SqlSession sqlSession = sessionFactory.openSession()) {
      Map<String, Object> map = sqlSession.getMapper(EmployeeMapper.class).getEmpByIDReturnMap(1);
      for (Map.Entry<String, Object> set : map.entrySet()) {
        System.out.println("Key: " + set.getKey() + "\tValue: " + set.getValue());
      }
    }
  }

  @Test
  public void getEmpByLastNameReturnMap() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      Map<String, Employee> map = sqlSession.getMapper(EmployeeMapper.class).getEmpByLastNameReturnMap("%码");
      for (Map.Entry<String, Employee> set : map.entrySet()) {
        System.out.println("Key: " + set.getKey() + "\tValue: " + set.getValue());
      }
    }
  }

  @Test
  public void getEmpByMap() {
    SqlSessionFactory sessionFactory = getSqlSessionFactory();
    try (SqlSession sqlSession = sessionFactory.openSession(true)) {
      EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
      Map<String, Object> map = new HashMap<>(16);
      map.put("id", 1);
      map.put("lastName", "'updateT'");
      map.put("tableName", "tbl_employee");
      System.out.println(mapper.getEmpByMap(map));
    }
  }

  @Test
  public void addEmp() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      Employee employee = new Employee(null, getName(), String.valueOf(new Random(System.currentTimeMillis()).nextInt(2)), getRandEmail(), new Department(new Random(System.currentTimeMillis()).nextInt(3)+1,null));
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      result = employeeMapper.addEmp(employee);
      System.out.print(employee);
      sqlSession.commit();
    }
  }

  @Test
  public void addEmpBatch(){
    long startTime = System.currentTimeMillis();
    try(SqlSession sqlSession = MyUtil.getSession(ExecutorType.BATCH)){
      int records = 10000;
      for (int i = 0; i < records; i++) {
        int deptId = new Random(System.currentTimeMillis()).nextInt(3);
        Employee employee = new Employee(getName(), String.valueOf(Math.abs(deptId-1)), getRandEmail(), new Department(deptId+1,null), EmpStatus.values()[deptId]);
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        result = employeeMapper.addEmp(employee);
      }
      sqlSession.commit();
    }
    System.out.println(System.currentTimeMillis()-startTime);
  }

  @Test
  public void updateEmp() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      result = employeeMapper.updateEmp(new Employee(1, "updateT", "0", "updater@qw"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void deleteEmpById() {
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      result = employeeMapper.deleteEmpById(5);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(result);
  }

  @Test
  public void deleteEmpEmail() {
    int result = 0;
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      result = employeeMapper.deleteEmpEmail("add3");
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(result);
  }
}
