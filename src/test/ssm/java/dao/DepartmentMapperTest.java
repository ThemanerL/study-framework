package ssm.java.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ssm.java.bean.Department;
import ssm.java.bean.DepartmentExample;

import java.util.List;

/**
 * Spring的项目可以使用Spring的单元测试，可以自动注入需要的组件
 * 1. @ContextConfiguration指定Spring配置文件的位置，直接AutoWired需要使用的组件即可
 * 2. @RunWith(SpringJUnit4ClassRunner.class)Junit将使用指定的类来运行测试而不是Junit默认的类运行
 * <p>
 * 单元测试的数据应该不依赖于数据库;当前的测试是不完善的
 * 单元测试如果连接数据库应该是每次测试执行完强制回滚;
 *
 * @author 李重辰
 * @date 2019/3/31 20:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ssm/resource/applicationContext.xml"})
public class DepartmentMapperTest {

  @Autowired
  DepartmentMapper departmentMapper;

  @Autowired
  SqlSession sqlSession;

  @Test
  public void insert() {
    Department department = new Department();
    department.setDeptId(0);
    department.setDeptName("开发部");
    int insert = departmentMapper.insert(department);
    Assert.assertEquals(insert, 1);
  }

  @Test
  public void insertSelective() {
    Department department = new Department();
    department.setDeptName("客户服务");
    int insert = departmentMapper.insertSelective(department);
    Assert.assertEquals(insert, 1);
  }

  @Test
  public void selectByExample() {
    DepartmentExample department = new DepartmentExample();
    DepartmentExample.Criteria criteria = department.createCriteria();
    criteria.andDeptIdBetween(-9, 30);
    List<Department> departments = departmentMapper.selectByExample(department);
    Assert.assertNotNull(departments);
  }

  @Test
  public void selectByPrimaryKey() {
    Department department = departmentMapper.selectByPrimaryKey(3);
    Assert.assertNotNull(department);
  }
}