package mybatis.basic;

import main.java.util.MyUtil;
import mybatis.basic.bean.Employee;
import mybatis.basic.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * Mybatis中设定了两级缓存
 * mybatis默认缓存设置相关:
 * 1),cacheEnabled(T/F)  全局二级缓存开关
 * 2).useCache(T/F)      单独控制某一个sql是否使用缓存
 * 3).flushCache         每个增删改默认为true,执行后刷新所有缓存(包括一级和二级).对于查询操作,
 * 查询时不使用缓存且查询后刷新所有缓存
 * 4).SqlSession.clearCache() 只针对一级缓存
 * 5).localCacheScope    本地缓存作用域(一级缓存Session和Statement 值STATEMENT:相当于禁用一级缓存)
 * 第三方缓存整合:
 * 1).导入第三方jar包
 * 2).导入与第三方缓存整合的适配包,(Mybatis官方已经提供)
 * 3).mapper.xml中引用何时的缓存类型(通过cache的type属性)
 * 注意!!! 在二级缓存开启的情况下,即使是在同一个SqlSession中进行查询,还是会先去二级缓存中查询,再去一级缓存,再去数据库
 *
 * @author 李重辰
 * @date 2019/3/9 13:46
 */
public class CacheTest {
  /**
   * 一级缓存 SqlSession级别的缓存,一个Session级别的Map,下次查询先查询Map中有没有,没有的话再操作数据库
   * 一级缓存失效情况:
   * 1. 没有使用到当前以及缓存的情况下.还需要向数据库发出查询
   * 2. SqlSession不同,缓存失效
   * 3. SqlSession相同,sql语句不同(当前一级缓存还未被缓存)
   * 4. SqlSession相同,两次查询之间进行了增删改操作
   * 5. SqlSession相同,手动清空缓存
   */
  @Test
  public void level1Cache() {
    try (SqlSession sqlsession = MyUtil.getSession()) {
      Employee emp = sqlsession.getMapper(EmployeeMapper.class).getEmpByID(1);
      System.out.println(emp);
      Employee emp1 = sqlsession.getMapper(EmployeeMapper.class).getEmpByID(1);
      System.out.println(emp1);
      System.out.println(emp.equals(emp1));
      // 只执行了一次sql操作
    }
  }

  /**
   * 二级缓存(全局缓存) 基于namespace级别的缓存,一个namespace对应一个二级缓存,有自己的一个map
   * 工作机制:
   * 一个会话,查询一个数据,这个数据就会被放在当前会话的一次缓存中,
   * 只有会话关闭或者提交之后,一级缓存中的数据会被保存在二级缓存中,
   * 新的会话查询信息,就可以参照二级缓存中的内容
   * SqlSession==>EmployeeMapper  ==>Employee
   * ==>DepartmentMapper==>Department
   * EmployeeMapper与DepartmentMapper是不同的namespace,不同的Mapper文件中分别查出两种对象
   * 不同的namespace查出的数据会放在自己对应的缓存中
   * 使用:
   * 1. 开启二级缓存配置(显式配置,防止版本更替)
   * 2. 去mapper.xml中配置启用二级缓存
   * 3. 我们的POJO需要实现序列化接口
   */
  @Test
  public void level2Cache() {
    EmployeeMapper mapper;
    try (SqlSession sqlSession1 = MyUtil.getSession()) {
      mapper = sqlSession1.getMapper(EmployeeMapper.class);
      System.out.println(mapper.getEmpByID(1));
      System.out.println(mapper.getEmpByID(1));
      System.out.println(mapper.getEmpByID(1));
    }
    try (SqlSession sqlSession1 = MyUtil.getSession()) {
      mapper = sqlSession1.getMapper(EmployeeMapper.class);
      System.out.println(mapper.getEmpByID(1));
    }
  }

  @Test
  public void get() {
    final Employee employee = new Employee();
    System.out.println(employee.hashCode());
    employee.setId(1);
    System.out.println(employee.hashCode());
  }
}
