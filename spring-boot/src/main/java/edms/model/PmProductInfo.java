package edms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息表
 *
 * @author 李重辰
 * @date   2020/09/06 10:54
 */
public class PmProductInfo implements Serializable {
    /**
     * 使用雪花算法生成分布式唯一ID，不在数据库中自增
     */
    private Long id;

    /**
     * 帐套
     */
    private String corpNo;

    /**
     * 品牌
     */
    private Integer brandNo;

    /**
     * 品名
     */
    private String name;

    /**
     * 规格
     */
    private String spec;

    /**
     * 默认价格
     */
    private BigDecimal price;

    /**
     * 单位
     */
    private String unit;

    private Date gmtCreate;

    private Date gmtUpdate;

    /**
     * 商品是否被删除
     */
    private Byte isDeleted;

    /**
     * 商品的详情图片
     */
    private String images;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorpNo() {
        return corpNo;
    }

    public void setCorpNo(String corpNo) {
        this.corpNo = corpNo;
    }

    public Integer getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(Integer brandNo) {
        this.brandNo = brandNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", corpNo=").append(corpNo);
        sb.append(", brandNo=").append(brandNo);
        sb.append(", name=").append(name);
        sb.append(", spec=").append(spec);
        sb.append(", price=").append(price);
        sb.append(", unit=").append(unit);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtUpdate=").append(gmtUpdate);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", images=").append(images);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
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
        PmProductInfo other = (PmProductInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCorpNo() == null ? other.getCorpNo() == null : this.getCorpNo().equals(other.getCorpNo()))
            && (this.getBrandNo() == null ? other.getBrandNo() == null : this.getBrandNo().equals(other.getBrandNo()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSpec() == null ? other.getSpec() == null : this.getSpec().equals(other.getSpec()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtUpdate() == null ? other.getGmtUpdate() == null : this.getGmtUpdate().equals(other.getGmtUpdate()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getImages() == null ? other.getImages() == null : this.getImages().equals(other.getImages()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCorpNo() == null) ? 0 : getCorpNo().hashCode());
        result = prime * result + ((getBrandNo() == null) ? 0 : getBrandNo().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSpec() == null) ? 0 : getSpec().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtUpdate() == null) ? 0 : getGmtUpdate().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getImages() == null) ? 0 : getImages().hashCode());
        return result;
    }
}