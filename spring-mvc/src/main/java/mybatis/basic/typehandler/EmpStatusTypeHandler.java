package mybatis.basic.typehandler;

import mybatis.basic.bean.EmpStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 1、实现TypeHandler接口或者继承BaseTypeHandler
 *
 * @author 李重辰
 * @date 2019/3/14 12:03
 */
public class EmpStatusTypeHandler implements TypeHandler<EmpStatus> {

  /**
   * 定义当前数据如何保存在数据库中
   *
   * @param ps        /
   * @param i         /
   * @param parameter /
   * @param jdbcType  /
   * @throws SQLException /
   */
  @Override
  public void setParameter(PreparedStatement ps, int i, EmpStatus parameter, JdbcType jdbcType) throws SQLException {
    ps.setInt(i, parameter.getCode());
  }

  @Override
  public EmpStatus getResult(ResultSet rs, String columnName) throws SQLException {
    int rsInt = rs.getInt(columnName);
    return EmpStatus.getEmpStatusByCode(rsInt);
  }

  @Override
  public EmpStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
    int rsInt = rs.getInt(columnIndex);
    return EmpStatus.getEmpStatusByCode(rsInt);
  }

  @Override
  public EmpStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
    int rsInt = cs.getInt(columnIndex);
    return EmpStatus.getEmpStatusByCode(rsInt);
  }
}
