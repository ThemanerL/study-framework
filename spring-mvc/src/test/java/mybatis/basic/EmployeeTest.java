package mybatis.basic;

import util.MyUtil;
import mybatis.basic.bean.Employee;
import mybatis.basic.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 此类被test.mybatis.dao.EmployeeMapperTest替代
 *
 * @author 李重辰 * @date 2019/2/19 17:55
 */
class EmployeeTest {

  String printEmpByInterfaceXml(Integer id) {
    String resource = "mybatis/mybatis-config.xml";
    Employee employee = null;
    try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      try (SqlSession sqlSession = MyUtil.getSession()) {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        employee = employeeMapper.getEmpByID(id);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return String.valueOf(employee);
  }

}
