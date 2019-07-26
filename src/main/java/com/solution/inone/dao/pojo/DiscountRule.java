package com.solution.inone.dao.pojo;

import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName DiscountRule
 * @Author AlexTong
 * @Date 2019/07/26
 */
@Entity
@Table(appliesTo = "discount_rule", comment = "discount rule")
public class DiscountRule implements Serializable {
    private static final long serialVersionUID = -8485323154637356774L;

    @Column(name = "ID", columnDefinition = "varchar(40) DEFAULT NULL COMMENT 'id'")
    private String id;
    @Column(name = "PRODUCT_ID", columnDefinition = "varchar(40) DEFAULT NULL COMMENT 'product id'")
    private String productId;
    @Column(name = "PRODUCT_NUM", columnDefinition = "int(2) DEFAULT NULL COMMENT 'product num for discount'")
    private Integer productNum;
    @Column(name = "DISCOUNT_TYPE", columnDefinition = "varchar(25) DEFAULT NULL COMMENT 'discount type which kind of discount'")
    private String discountType;
    @Column(name = "PRODUCT_TYPE", columnDefinition = "varchar(25) DEFAULT NULL COMMENT 'product type reserved for category product'")
    private String productType;
    @Column(name = "DISCOUNT_PRICE", columnDefinition = "decimal(10,2) DEFAULT NULL COMMENT 'discount price'")
    private BigDecimal discountPrice;
    @Column(name = "CREATED_TIME", columnDefinition = "date DEFAULT NULL COMMENT 'create time'")
    private Date createdTime;
    @Column(name = "CREATED_USER_ID", columnDefinition = "varchar(25) DEFAULT NULL COMMENT 'created user id'")
    private String createdUserId;
    @Column(name = "UPDATED_TIME", columnDefinition = "date DEFAULT NULL COMMENT 'updated time'")
    private Date updatedTime;
    @Column(name = "UPDATED_USER_ID", columnDefinition = "varchar(25) DEFAULT NULL COMMENT 'updated user id'")
    private String updatedUserId;
    @Column(name = "VALID_STATUS", columnDefinition = "int(2) DEFAULT NULL COMMENT '0: valid, 1: invalid'")
    private Integer validStatus = 1;
    @Version
    @Column(name="VERSION",columnDefinition="int(11) DEFAULT 0 COMMENT 'version'")
    private Integer version = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public Integer getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(Integer validStatus) {
        this.validStatus = validStatus;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountRule)) return false;
        DiscountRule that = (DiscountRule) o;
        return getId().equals(that.getId()) &&
                getProductId().equals(that.getProductId()) &&
                getProductNum().equals(that.getProductNum()) &&
                Objects.equals(getDiscountType(), that.getDiscountType()) &&
                Objects.equals(getProductType(), that.getProductType()) &&
                getDiscountPrice().equals(that.getDiscountPrice()) &&
                getCreatedTime().equals(that.getCreatedTime()) &&
                getCreatedUserId().equals(that.getCreatedUserId()) &&
                getUpdatedTime().equals(that.getUpdatedTime()) &&
                getUpdatedUserId().equals(that.getUpdatedUserId()) &&
                getValidStatus().equals(that.getValidStatus()) &&
                Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductId(), getProductNum(), getDiscountType(), getProductType(), getDiscountPrice(), getCreatedTime(), getCreatedUserId(), getUpdatedTime(), getUpdatedUserId(), getValidStatus(), getVersion());
    }

    @Override
    public String toString() {
        return "DiscountRule{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", productNum=" + productNum +
                ", discountType='" + discountType + '\'' +
                ", productType='" + productType + '\'' +
                ", discountPrice=" + discountPrice +
                ", createdTime=" + createdTime +
                ", createdUserId='" + createdUserId + '\'' +
                ", updatedTime=" + updatedTime +
                ", updatedUserId='" + updatedUserId + '\'' +
                ", validStatus=" + validStatus +
                ", version=" + version +
                '}';
    }
}
