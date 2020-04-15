package edms.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmProductInfoDynamicSqlSupport {
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.909+08:00", comments = "Source Table: pm_product_info")
  public static final PmProductInfo pmProductInfo = new PmProductInfo();

  /**
   * 使用雪花算法生成分布式唯一ID，不在数据库中自增
   */
  public static final SqlColumn<Long> id = pmProductInfo.id;

  /**
   * 帐套
   */
  public static final SqlColumn<String> corpNo = pmProductInfo.corpNo;

  /**
   * 品牌
   */
  public static final SqlColumn<Integer> brandNo = pmProductInfo.brandNo;

  /**
   * 品名
   */
  public static final SqlColumn<String> name = pmProductInfo.name;

  /**
   * 规格
   */
  public static final SqlColumn<String> spec = pmProductInfo.spec;

  /**
   * 默认价格
   */
  public static final SqlColumn<BigDecimal> price = pmProductInfo.price;

  /**
   * 单位
   */
  public static final SqlColumn<String> unit = pmProductInfo.unit;

  public static final SqlColumn<Date> gmtCreate = pmProductInfo.gmtCreate;

  public static final SqlColumn<Date> gmtUpdate = pmProductInfo.gmtUpdate;

  /**
   * 商品是否被删除
   */
  public static final SqlColumn<Byte> isDeleted = pmProductInfo.isDeleted;

  /**
   * 商品的详情图片
   */
  public static final SqlColumn<String> imgs = pmProductInfo.imgs;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.909+08:00", comments = "Source Table: pm_product_info")
  public static final class PmProductInfo extends SqlTable {
    public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

    public final SqlColumn<String> corpNo = column("corp_no", JDBCType.VARCHAR);

    public final SqlColumn<Integer> brandNo = column("brand_no", JDBCType.INTEGER);

    public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

    public final SqlColumn<String> spec = column("spec", JDBCType.VARCHAR);

    public final SqlColumn<BigDecimal> price = column("price", JDBCType.DECIMAL);

    public final SqlColumn<String> unit = column("unit", JDBCType.VARCHAR);

    public final SqlColumn<Date> gmtCreate = column("gmt_create", JDBCType.TIMESTAMP);

    public final SqlColumn<Date> gmtUpdate = column("gmt_update", JDBCType.TIMESTAMP);

    public final SqlColumn<Byte> isDeleted = column("is_deleted", JDBCType.TINYINT);

    public final SqlColumn<String> imgs = column("imgs", JDBCType.LONGVARCHAR);

    public PmProductInfo() {
      super("pm_product_info");
    }
  }
}