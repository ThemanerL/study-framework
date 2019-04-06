package ssm.java.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ssm.java.bean.Employee;

import java.util.Random;

import static util.MyUtil.getName;
import static util.MyUtil.getRandEmail;

/**
 * @author 李重辰
 * @date 2019/3/31 20:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ssm/resource/applicationContext.xml")
public class EmployeeMapperTest {

  @Autowired
  SqlSession sqlSession;

  @Test
  public void insertSelective() {
    long start = System.currentTimeMillis();
    EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
    for (int i = 0; i < 785; i++) {
      int deptId = new Random(System.currentTimeMillis()).nextInt(3);
      Employee employee = new Employee(getName(), String.valueOf(Math.abs(deptId - 1)), getRandEmail(), deptId + 1);
      mapper.insertSelective(employee);
    }
    System.err.println(System.currentTimeMillis() - start);
  }

}