package edms.mapper;

import edms.model.OmOrder;
import edms.model.OmOrderExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OmOrderMapper {
  long countByExample(OmOrderExample example);

  int deleteByExample(OmOrderExample example);

  @Delete({
      "delete from om_order",
      "where id = #{id,jdbcType=BIGINT}"
  })
  int deleteByPrimaryKey(Long id);

  @Insert({
      "insert into om_order (id, type, ",
      "confirm_time, total_quantity, ",
      "total_amount, gmt_create, ",
      "gmt_update, is_deleted)",
      "values (#{id,jdbcType=BIGINT}, #{type,jdbcType=TINYINT}, ",
      "#{confirmTime,jdbcType=TIMESTAMP}, #{totalQuantity,jdbcType=INTEGER}, ",
      "#{totalAmount,jdbcType=DECIMAL}, #{gmtCreate,jdbcType=TIMESTAMP}, ",
      "#{gmtUpdate,jdbcType=TIMESTAMP}, #{isDeleted,jdbcType=TINYINT})"
  })
  int insert(OmOrder record);

  int insertSelective(OmOrder record);

  List<OmOrder> selectByExample(OmOrderExample example);

  @Select({
      "select",
      "id, type, confirm_time, total_quantity, total_amount, gmt_create, gmt_update, ",
      "is_deleted",
      "from om_order",
      "where id = #{id,jdbcType=BIGINT}"
  })
  @ResultMap("edms.mapper.OmOrderMapper.BaseResultMap")
  OmOrder selectByPrimaryKey(Long id);

  int updateByExampleSelective(@Param("record") OmOrder record, @Param("example") OmOrderExample example);

  int updateByExample(@Param("record") OmOrder record, @Param("example") OmOrderExample example);

  int updateByPrimaryKeySelective(OmOrder record);

  @Update({
      "update om_order",
      "set type = #{type,jdbcType=TINYINT},",
      "confirm_time = #{confirmTime,jdbcType=TIMESTAMP},",
      "total_quantity = #{totalQuantity,jdbcType=INTEGER},",
      "total_amount = #{totalAmount,jdbcType=DECIMAL},",
      "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
      "gmt_update = #{gmtUpdate,jdbcType=TIMESTAMP},",
      "is_deleted = #{isDeleted,jdbcType=TINYINT}",
      "where id = #{id,jdbcType=BIGINT}"
  })
  int updateByPrimaryKey(OmOrder record);
}