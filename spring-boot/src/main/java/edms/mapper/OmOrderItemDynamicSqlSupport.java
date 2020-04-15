package edms.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OmOrderItemDynamicSqlSupport {
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  public static final OmOrderItem omOrderItem = new OmOrderItem();

  /**
   * 订单编号
   */
  public static final SqlColumn<Long> id = omOrderItem.id;

  /**
   * 单身序号
   */
  public static final SqlColumn<String> it = omOrderItem.it;

  /**
   * 商品编号
   */
  public static final SqlColumn<Long> productId = omOrderItem.productId;

  /**
   * 商品价格
   */
  public static final SqlColumn<BigDecimal> productPrice = omOrderItem.productPrice;

  /**
   * 商品数量
   */
  public static final SqlColumn<Integer> productQuantity = omOrderItem.productQuantity;

  /**
   * 商品单位
   */
  public static final SqlColumn<String> productUnit = omOrderItem.productUnit;

  /**
   * 商品名称
   */
  public static final SqlColumn<String> productName = omOrderItem.productName;

  /**
   * 商品规格
   */
  public static final SqlColumn<String> productSpec = omOrderItem.productSpec;

  public static final SqlColumn<Date> gmtCreate = omOrderItem.gmtCreate;

  public static final SqlColumn<Date> gmtUpdate = omOrderItem.gmtUpdate;

  /**
   * 是否被删除
   */
  public static final SqlColumn<Integer> isDeleted = omOrderItem.isDeleted;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  public static final class OmOrderItem extends SqlTable {
    public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

    public final SqlColumn<String> it = column("it", JDBCType.VARCHAR);

    public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

    public final SqlColumn<BigDecimal> productPrice = column("product_price", JDBCType.DECIMAL);

    public final SqlColumn<Integer> productQuantity = column("product_quantity", JDBCType.INTEGER);

    public final SqlColumn<String> productUnit = column("product_unit", JDBCType.VARCHAR);

    public final SqlColumn<String> productName = column("product_name", JDBCType.VARCHAR);

    public final SqlColumn<String> productSpec = column("product_spec", JDBCType.VARCHAR);

    public final SqlColumn<Date> gmtCreate = column("gmt_create", JDBCType.TIMESTAMP);

    public final SqlColumn<Date> gmtUpdate = column("gmt_update", JDBCType.TIMESTAMP);

    public final SqlColumn<Integer> isDeleted = column("is_deleted", JDBCType.INTEGER);

    public OmOrderItem() {
      super("om_order_item");
    }
  }
}