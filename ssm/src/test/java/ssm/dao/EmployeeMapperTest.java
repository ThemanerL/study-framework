package ssm.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ssm.bean.Employee;
import util.Util;

import java.util.List;
import java.util.Random;

/**
 * @author 李重辰
 * @date 2019/3/31 20:11
 */
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ssm/resource/applicationContext.xml")
public class EmployeeMapperTest {

  @Autowired
  SqlSession sqlSession;

  @Autowired
  EmployeeMapper employeeMapper;

  @Test
  public void selectByExampleWithDeptTest() {
    List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
    for (Employee employee : employees) {
      System.out.println(employee.getEmpName());
    }
  }

  /**
   * 批量插入时，使用BatchExecutor
   */
  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  @Test
  public void batchInsertSelective() {
    long start = System.currentTimeMillis();
    EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
    for (int i = 0; i < 785; i++) {
      int deptId = new Random(System.currentTimeMillis()).nextInt(3);
      Employee employee = new Employee(Util.getName(), Math.abs(deptId - 1), Util.getRandEmail());
      mapper.insertSelective(employee);
    }
    System.err.println(System.currentTimeMillis() - start);
  }

}