package edms.mapper;

import edms.model.PmProductInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author 李重辰
 * @date 2020/9/1 22:00
 */
@Mapper
public interface PmProductInfoSimpleMapper {

  @Delete("delete from pm_product_info where id=#{id}")
  int deletePmProductById(Integer id);

  //  @Select("select * from pm_product_info where id=#{id}")
  PmProductInfo getPmProductById(Integer id);

  @Insert("insert into pm_product_info(id,corp_no) values(#{id},#{corpNo})")
  int insertPmProduct(PmProductInfo productInfo);

  @Update("update pm_product_info set corp_no_=#{corpNo} where id=#{id}")
  int updatePmProduct(PmProductInfo productInfo);
}
