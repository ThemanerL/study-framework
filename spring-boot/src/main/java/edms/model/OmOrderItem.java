package edms.model;

import javax.annotation.Generated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单明细表
 *
 * @author 李重辰
 * @date 2020/04/15 09:35
 */
public class OmOrderItem implements Serializable {
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source Table: om_order_item")
  private static final long serialVersionUID = 1L;
  /**
   * 订单编号
   */
  private Long id;
  /**
   * 单身序号
   */
  private String it;
  /**
   * 商品编号
   */
  private Long productId;
  /**
   * 商品价格
   */
  private BigDecimal productPrice;
  /**
   * 商品数量
   */
  private Integer productQuantity;
  /**
   * 商品单位
   */
  private String productUnit;
  /**
   * 商品名称
   */
  private String productName;
  /**
   * 商品规格
   */
  private String productSpec;
  private Date gmtCreate;
  private Date gmtUpdate;
  /**
   * 是否被删除
   */
  private Integer isDeleted;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.id")
  public Long getId() {
    return id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.id")
  public void setId(Long id) {
    this.id = id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.it")
  public String getIt() {
    return it;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.it")
  public void setIt(String it) {
    this.it = it;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.product_id")
  public Long getProductId() {
    return productId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.product_id")
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.product_price")
  public BigDecimal getProductPrice() {
    return productPrice;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.product_price")
  public void setProductPrice(BigDecimal productPrice) {
    this.productPrice = productPrice;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.product_quantity")
  public Integer getProductQuantity() {
    return productQuantity;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.product_quantity")
  public void setProductQuantity(Integer productQuantity) {
    this.productQuantity = productQuantity;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.product_unit")
  public String getProductUnit() {
    return productUnit;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.product_unit")
  public void setProductUnit(String productUnit) {
    this.productUnit = productUnit;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.904+08:00", comments = "Source field: om_order_item.product_name")
  public String getProductName() {
    return productName;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source field: om_order_item.product_name")
  public void setProductName(String productName) {
    this.productName = productName;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source field: om_order_item.product_spec")
  public String getProductSpec() {
    return productSpec;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source field: om_order_item.product_spec")
  public void setProductSpec(String productSpec) {
    this.productSpec = productSpec;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source field: om_order_item.gmt_create")
  public Date getGmtCreate() {
    return gmtCreate;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source field: om_order_item.gmt_create")
  public void setGmtCreate(Date gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source field: om_order_item.gmt_update")
  public Date getGmtUpdate() {
    return gmtUpdate;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source field: om_order_item.gmt_update")
  public void setGmtUpdate(Date gmtUpdate) {
    this.gmtUpdate = gmtUpdate;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source field: om_order_item.is_deleted")
  public Integer getIsDeleted() {
    return isDeleted;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source field: om_order_item.is_deleted")
  public void setIsDeleted(Integer isDeleted) {
    this.isDeleted = isDeleted;
  }

  @Override
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source Table: om_order_item")
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", it=").append(it);
    sb.append(", productId=").append(productId);
    sb.append(", productPrice=").append(productPrice);
    sb.append(", productQuantity=").append(productQuantity);
    sb.append(", productUnit=").append(productUnit);
    sb.append(", productName=").append(productName);
    sb.append(", productSpec=").append(productSpec);
    sb.append(", gmtCreate=").append(gmtCreate);
    sb.append(", gmtUpdate=").append(gmtUpdate);
    sb.append(", isDeleted=").append(isDeleted);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }

  @Override
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source Table: om_order_item")
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
    OmOrderItem other = (OmOrderItem) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getIt() == null ? other.getIt() == null : this.getIt().equals(other.getIt()))
        && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
        && (this.getProductPrice() == null ? other.getProductPrice() == null : this.getProductPrice().equals(other.getProductPrice()))
        && (this.getProductQuantity() == null ? other.getProductQuantity() == null : this.getProductQuantity().equals(other.getProductQuantity()))
        && (this.getProductUnit() == null ? other.getProductUnit() == null : this.getProductUnit().equals(other.getProductUnit()))
        && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
        && (this.getProductSpec() == null ? other.getProductSpec() == null : this.getProductSpec().equals(other.getProductSpec()))
        && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
        && (this.getGmtUpdate() == null ? other.getGmtUpdate() == null : this.getGmtUpdate().equals(other.getGmtUpdate()))
        && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
  }

  @Override
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.905+08:00", comments = "Source Table: om_order_item")
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getIt() == null) ? 0 : getIt().hashCode());
    result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
    result = prime * result + ((getProductPrice() == null) ? 0 : getProductPrice().hashCode());
    result = prime * result + ((getProductQuantity() == null) ? 0 : getProductQuantity().hashCode());
    result = prime * result + ((getProductUnit() == null) ? 0 : getProductUnit().hashCode());
    result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
    result = prime * result + ((getProductSpec() == null) ? 0 : getProductSpec().hashCode());
    result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
    result = prime * result + ((getGmtUpdate() == null) ? 0 : getGmtUpdate().hashCode());
    result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
    return result;
  }
}