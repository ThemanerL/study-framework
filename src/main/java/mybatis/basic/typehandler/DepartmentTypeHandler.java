package mybatis.basic.typehandler;

import mybatis.basic.bean.Department;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * main.java.mybatis.basic.dao.EmployeeMapper.getEmpByID的返回值ResultMap使用
 *
 * @author 李重辰
 * @date 2019/3/14 12:03
 */
public class DepartmentTypeHandler implements TypeHandler<Department> {

  @Override
  public void setParameter(PreparedStatement ps, int i, Department parameter, JdbcType jdbcType) throws SQLException {
    ps.setInt(1, parameter.getId());
    ps.setString(2, parameter.getName());
  }

  @Override
  public Department getResult(ResultSet rs, String columnName) throws SQLException {
    return new Department(rs.getInt("id"), null);
  }

  @Override
  public Department getResult(ResultSet rs, int columnIndex) throws SQLException {
    return new Department(rs.getInt("id"), null);
  }

  @Override
  public Department getResult(CallableStatement cs, int columnIndex) throws SQLException {
    return new Department(cs.getInt("id"), null);
  }
}