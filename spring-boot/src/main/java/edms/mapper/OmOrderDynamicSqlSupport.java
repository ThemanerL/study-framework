package edms.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;

public final class OmOrderDynamicSqlSupport {
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  public static final OmOrder omOrder = new OmOrder();

  /**
   * 订单编号，保证分布式唯一
   */
  public static final SqlColumn<Long> id = omOrder.id;

  public static final SqlColumn<Byte> type = omOrder.type;

  public static final SqlColumn<Date> confirmTime = omOrder.confirmTime;

  /**
   * 商品数量合计
   */
  public static final SqlColumn<Integer> totalQuantity = omOrder.totalQuantity;

  /**
   * 订单金额合计
   */
  public static final SqlColumn<BigDecimal> totalAmount = omOrder.totalAmount;

  public static final SqlColumn<Date> gmtCreate = omOrder.gmtCreate;

  public static final SqlColumn<Date> gmtUpdate = omOrder.gmtUpdate;

  /**
   * 是否被删除
   */
  public static final SqlColumn<Byte> isDeleted = omOrder.isDeleted;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  public static final class OmOrder extends SqlTable {
    public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

    public final SqlColumn<Byte> type = column("type", JDBCType.TINYINT);

    public final SqlColumn<Date> confirmTime = column("confirm_time", JDBCType.TIMESTAMP);

    public final SqlColumn<Integer> totalQuantity = column("total_quantity", JDBCType.INTEGER);

    public final SqlColumn<BigDecimal> totalAmount = column("total_amount", JDBCType.DECIMAL);

    public final SqlColumn<Date> gmtCreate = column("gmt_create", JDBCType.TIMESTAMP);

    public final SqlColumn<Date> gmtUpdate = column("gmt_update", JDBCType.TIMESTAMP);

    public final SqlColumn<Byte> isDeleted = column("is_deleted", JDBCType.TINYINT);

    public OmOrder() {
      super("om_order");
    }
  }
}