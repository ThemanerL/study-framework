package edms.mapper;

import edms.model.PmProductInfo;
import edms.model.PmProductInfoExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PmProductInfoMapper {
  long countByExample(PmProductInfoExample example);

  int deleteByExample(PmProductInfoExample example);

  @Delete({
      "delete from pm_product_info",
      "where id = #{id,jdbcType=BIGINT}"
  })
  int deleteByPrimaryKey(Long id);

  @Insert({
      "insert into pm_product_info (id, corp_no, ",
      "brand_no, name, spec, ",
      "price, unit, gmt_create, ",
      "gmt_update, is_deleted, ",
      "images)",
      "values (#{id,jdbcType=BIGINT}, #{corpNo,jdbcType=VARCHAR}, ",
      "#{brandNo,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{spec,jdbcType=VARCHAR}, ",
      "#{price,jdbcType=DECIMAL}, #{unit,jdbcType=VARCHAR}, #{gmtCreate,jdbcType=TIMESTAMP}, ",
      "#{gmtUpdate,jdbcType=TIMESTAMP}, #{isDeleted,jdbcType=TINYINT}, ",
      "#{images,jdbcType=LONGVARCHAR})"
  })
  int insert(PmProductInfo record);

  int insertSelective(PmProductInfo record);

  List<PmProductInfo> selectByExampleWithBLOBs(PmProductInfoExample example);

  List<PmProductInfo> selectByExample(PmProductInfoExample example);

  @Select({
      "select",
      "id, corp_no, brand_no, name, spec, price, unit, gmt_create, gmt_update, is_deleted, ",
      "images",
      "from pm_product_info",
      "where id = #{id,jdbcType=BIGINT}"
  })
  @ResultMap("edms.mapper.PmProductInfoMapper.ResultMapWithBLOBs")
  PmProductInfo selectByPrimaryKey(Long id);

  int updateByExampleSelective(@Param("record") PmProductInfo record, @Param("example") PmProductInfoExample example);

  int updateByExampleWithBLOBs(@Param("record") PmProductInfo record, @Param("example") PmProductInfoExample example);

  int updateByExample(@Param("record") PmProductInfo record, @Param("example") PmProductInfoExample example);

  int updateByPrimaryKeySelective(PmProductInfo record);

  @Update({
      "update pm_product_info",
      "set corp_no = #{corpNo,jdbcType=VARCHAR},",
      "brand_no = #{brandNo,jdbcType=INTEGER},",
      "name = #{name,jdbcType=VARCHAR},",
      "spec = #{spec,jdbcType=VARCHAR},",
      "price = #{price,jdbcType=DECIMAL},",
      "unit = #{unit,jdbcType=VARCHAR},",
      "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
      "gmt_update = #{gmtUpdate,jdbcType=TIMESTAMP},",
      "is_deleted = #{isDeleted,jdbcType=TINYINT},",
      "images = #{images,jdbcType=LONGVARCHAR}",
      "where id = #{id,jdbcType=BIGINT}"
  })
  int updateByPrimaryKeyWithBLOBs(PmProductInfo record);

  @Update({
      "update pm_product_info",
      "set corp_no = #{corpNo,jdbcType=VARCHAR},",
      "brand_no = #{brandNo,jdbcType=INTEGER},",
      "name = #{name,jdbcType=VARCHAR},",
      "spec = #{spec,jdbcType=VARCHAR},",
      "price = #{price,jdbcType=DECIMAL},",
      "unit = #{unit,jdbcType=VARCHAR},",
      "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
      "gmt_update = #{gmtUpdate,jdbcType=TIMESTAMP},",
      "is_deleted = #{isDeleted,jdbcType=TINYINT}",
      "where id = #{id,jdbcType=BIGINT}"
  })
  int updateByPrimaryKey(PmProductInfo record);
}