package ssm.controller;

import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 使用Spring测试模块提供的测试请求功能
 *
 * @author 李重辰
 * @date 2019/4/1 10:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:ssm/resource/applicationContext.xml"})
public class EmployeeControllerTest {
  // 传入SpringMVC的IOC
  @Autowired
  WebApplicationContext webApplicationContext;
  // 虚拟MVC，获取到处理结果
  private MockMvc mockMvc;

  @Before
  public void initMockMvc() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void getEmps() throws Exception {
    // 模拟请求拿到返回值
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
    // 请求成功以后，请求域中会有pageInfo
    MockHttpServletRequest request = mvcResult.getRequest();
    PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
    assertNotNull(pageInfo);
    assertEquals(pageInfo.getPageNum(), 1);
  }
}