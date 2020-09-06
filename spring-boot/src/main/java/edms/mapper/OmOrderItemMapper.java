package edms.mapper;

import edms.model.OmOrderItem;
import edms.model.OmOrderItemExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OmOrderItemMapper {
  long countByExample(OmOrderItemExample example);

  int deleteByExample(OmOrderItemExample example);

  @Delete({
      "delete from om_order_item",
      "where id = #{id,jdbcType=BIGINT}"
  })
  int deleteByPrimaryKey(Long id);

  @Insert({
      "insert into om_order_item (id, it, product_id, ",
      "product_price, product_quantity, ",
      "product_unit, product_name, ",
      "product_spec, gmt_create, ",
      "gmt_update, is_deleted)",
      "values (#{id,jdbcType=BIGINT}, #{it,jdbcType=VARCHAR}, #{productId,jdbcType=BIGINT}, ",
      "#{productPrice,jdbcType=DECIMAL}, #{productQuantity,jdbcType=INTEGER}, ",
      "#{productUnit,jdbcType=VARCHAR}, #{productName,jdbcType=VARCHAR}, ",
      "#{productSpec,jdbcType=VARCHAR}, #{gmtCreate,jdbcType=TIMESTAMP}, ",
      "#{gmtUpdate,jdbcType=TIMESTAMP}, #{isDeleted,jdbcType=INTEGER})"
  })
  int insert(OmOrderItem record);

  int insertSelective(OmOrderItem record);

  List<OmOrderItem> selectByExample(OmOrderItemExample example);

  @Select({
      "select",
      "id, it, product_id, product_price, product_quantity, product_unit, product_name, ",
      "product_spec, gmt_create, gmt_update, is_deleted",
      "from om_order_item",
      "where id = #{id,jdbcType=BIGINT}"
  })
  @ResultMap("edms.mapper.OmOrderItemMapper.BaseResultMap")
  OmOrderItem selectByPrimaryKey(Long id);

  int updateByExampleSelective(@Param("record") OmOrderItem record, @Param("example") OmOrderItemExample example);

  int updateByExample(@Param("record") OmOrderItem record, @Param("example") OmOrderItemExample example);

  int updateByPrimaryKeySelective(OmOrderItem record);

  @Update({
      "update om_order_item",
      "set it = #{it,jdbcType=VARCHAR},",
      "product_id = #{productId,jdbcType=BIGINT},",
      "product_price = #{productPrice,jdbcType=DECIMAL},",
      "product_quantity = #{productQuantity,jdbcType=INTEGER},",
      "product_unit = #{productUnit,jdbcType=VARCHAR},",
      "product_name = #{productName,jdbcType=VARCHAR},",
      "product_spec = #{productSpec,jdbcType=VARCHAR},",
      "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
      "gmt_update = #{gmtUpdate,jdbcType=TIMESTAMP},",
      "is_deleted = #{isDeleted,jdbcType=INTEGER}",
      "where id = #{id,jdbcType=BIGINT}"
  })
  int updateByPrimaryKey(OmOrderItem record);
}