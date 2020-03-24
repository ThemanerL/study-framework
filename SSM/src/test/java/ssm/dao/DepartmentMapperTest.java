package ssm.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ssm.bean.Department;
import ssm.bean.DepartmentExample;

import java.util.List;

/**
 * Spring的项目可以使用Spring的单元测试，可以自动注入需要的组件
 * 1. @ContextConfiguration指定Spring配置文件的位置，直接AutoWired需要使用的组件即可
 * 2. @RunWith(SpringJUnit4ClassRunner.class)Junit将使用指定的类来运行测试而不是Junit默认的类运行
 *
 * @author 李重辰
 * @date 2019/3/31 20:10
 */
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ssm/resource/applicationContext.xml"})
public class DepartmentMapperTest {

  @Autowired
  DepartmentMapper departmentMapper;

  @Autowired
  SqlSession sqlSession;

  @Test
  public void insertTest() {
    Department department = new Department();
    department.setId(1);
    department.setDeptName("测试部门");
    departmentMapper.insert(department);
    Assert.assertEquals(department.toString(), departmentMapper.selectByPrimaryKey(department.getId()).toString());
  }

  @Test
  public void insertSelectiveTest() {
    Department department = new Department();
    department.setDeptName("客户服务");
    int i = departmentMapper.insertSelective(department);
    Assert.assertEquals(department.toString(), departmentMapper.selectByPrimaryKey(department.getId()).toString());
  }

  @Test
  public void selectByExampleTest() {
    DepartmentExample department = new DepartmentExample();
    DepartmentExample.Criteria criteria = department.createCriteria();
    criteria.andIdBetween(-9, 30);
    List<Department> departments = departmentMapper.selectByExample(department);
    Assert.assertNotNull(departments);
  }

}