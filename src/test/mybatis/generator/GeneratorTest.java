package mybatis.generator;

import com.github.pagehelper.PageHelper;
import mybatis.generator.bean.Employee;
import mybatis.generator.bean.EmployeeExample;
import mybatis.generator.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import util.MyUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 李重辰
 * @date 2019/3/11 23:48
 */
public class GeneratorTest {

  @Test
  public void testGenerator( ) throws Exception {
    List<String> warnings = new ArrayList<>();
    boolean overwrite = true;

    File configFile = new File("./conf/ssm/resource/mybatis-mbg.xml");
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(configFile);
    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
  }

  @Test
  public void selectAll(){
    try (SqlSession sqlSession = MyUtil.getSession()) {
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      EmployeeExample employeeExample = new EmployeeExample();
      EmployeeExample.Criteria criteria = employeeExample.createCriteria();
      criteria.andLastNameLike("李%");
      criteria.andGenderEqualTo("0");
      EmployeeExample.Criteria criteria1 = employeeExample.createCriteria();
      criteria1.andGenderEqualTo("1");
      criteria1.andLastNameLike("王%");
      employeeExample.or(criteria1);
      PageHelper.startPage(1,5);
      List<Employee> employees = employeeMapper.selectByExample(employeeExample);
      for (Employee employee : employees) {
        System.out.println(employee);
      }
    }
  }

  @Test
  public void deleteAll(){
    try(SqlSession sqlSession = MyUtil.getSession()){
      EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
      int i = employeeMapper.deleteByExample(null);
      System.out.println(i);
    }
  }

}
