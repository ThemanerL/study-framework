package edms.model;

import javax.annotation.Generated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 *
 * @author 李重辰
 * @date 2020/04/15 09:35
 */
public class OmOrder implements Serializable {
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.879+08:00", comments = "Source Table: om_order")
  private static final long serialVersionUID = 1L;
  /**
   * 订单编号，保证分布式唯一
   */
  private Long id;
  private Byte type;
  private Date confirmTime;
  /**
   * 商品数量合计
   */
  private Integer totalQuantity;
  /**
   * 订单金额合计
   */
  private BigDecimal totalAmount;
  private Date gmtCreate;
  private Date gmtUpdate;
  /**
   * 是否被删除
   */
  private Byte isDeleted;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.867+08:00", comments = "Source field: om_order.id")
  public Long getId() {
    return id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.87+08:00", comments = "Source field: om_order.id")
  public void setId(Long id) {
    this.id = id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.87+08:00", comments = "Source field: om_order.type")
  public Byte getType() {
    return type;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.87+08:00", comments = "Source field: om_order.type")
  public void setType(Byte type) {
    this.type = type;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.87+08:00", comments = "Source field: om_order.confirm_time")
  public Date getConfirmTime() {
    return confirmTime;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.87+08:00", comments = "Source field: om_order.confirm_time")
  public void setConfirmTime(Date confirmTime) {
    this.confirmTime = confirmTime;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.87+08:00", comments = "Source field: om_order.total_quantity")
  public Integer getTotalQuantity() {
    return totalQuantity;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.87+08:00", comments = "Source field: om_order.total_quantity")
  public void setTotalQuantity(Integer totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.87+08:00", comments = "Source field: om_order.total_amount")
  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.87+08:00", comments = "Source field: om_order.total_amount")
  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.87+08:00", comments = "Source field: om_order.gmt_create")
  public Date getGmtCreate() {
    return gmtCreate;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.87+08:00", comments = "Source field: om_order.gmt_create")
  public void setGmtCreate(Date gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.87+08:00", comments = "Source field: om_order.gmt_update")
  public Date getGmtUpdate() {
    return gmtUpdate;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.878+08:00", comments = "Source field: om_order.gmt_update")
  public void setGmtUpdate(Date gmtUpdate) {
    this.gmtUpdate = gmtUpdate;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.879+08:00", comments = "Source field: om_order.is_deleted")
  public Byte getIsDeleted() {
    return isDeleted;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.879+08:00", comments = "Source field: om_order.is_deleted")
  public void setIsDeleted(Byte isDeleted) {
    this.isDeleted = isDeleted;
  }

  @Override
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.879+08:00", comments = "Source Table: om_order")
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", type=").append(type);
    sb.append(", confirmTime=").append(confirmTime);
    sb.append(", totalQuantity=").append(totalQuantity);
    sb.append(", totalAmount=").append(totalAmount);
    sb.append(", gmtCreate=").append(gmtCreate);
    sb.append(", gmtUpdate=").append(gmtUpdate);
    sb.append(", isDeleted=").append(isDeleted);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }

  @Override
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.879+08:00", comments = "Source Table: om_order")
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    OmOrder other = (OmOrder) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
        && (this.getConfirmTime() == null ? other.getConfirmTime() == null : this.getConfirmTime().equals(other.getConfirmTime()))
        && (this.getTotalQuantity() == null ? other.getTotalQuantity() == null : this.getTotalQuantity().equals(other.getTotalQuantity()))
        && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
        && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
        && (this.getGmtUpdate() == null ? other.getGmtUpdate() == null : this.getGmtUpdate().equals(other.getGmtUpdate()))
        && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
  }

  @Override
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.88+08:00", comments = "Source Table: om_order")
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
    result = prime * result + ((getConfirmTime() == null) ? 0 : getConfirmTime().hashCode());
    result = prime * result + ((getTotalQuantity() == null) ? 0 : getTotalQuantity().hashCode());
    result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
    result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
    result = prime * result + ((getGmtUpdate() == null) ? 0 : getGmtUpdate().hashCode());
    result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
    return result;
  }
}