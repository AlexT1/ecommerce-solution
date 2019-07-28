package com.solution.oc.dao.pojo;

import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(appliesTo = "product_info", comment = "product info")
public class ProductInfo implements Serializable {
    private static final long serialVersionUID = -5754627668740847083L;

    @Column(name = "ID", columnDefinition = "varchar(40) DEFAULT NULL COMMENT 'id'")
    private String id;
    @Column(name = "PRODUCT_ID", columnDefinition = "varchar(40) DEFAULT NULL COMMENT 'product id'")
    private String productId;
    @Column(name = "PRODUCT_PRICE", columnDefinition = "decimal(10,2) DEFAULT NULL COMMENT 'product original price'")
    private BigDecimal productPrice;
    @Column(name = "PRODUCT_NUM", columnDefinition = "int(2) DEFAULT NULL COMMENT 'product num for sale'")
    private Integer productNum;
    @Column(name = "CREATED_TIME", columnDefinition = "date DEFAULT NULL COMMENT 'create time'")
    private Date createdTime;
    @Column(name = "CREATED_USER_ID", columnDefinition = "varchar(25) DEFAULT NULL COMMENT 'created user id'")
    private String createdUserId;
    @Column(name = "UPDATED_TIME", columnDefinition = "date DEFAULT NULL COMMENT 'updated time'")
    private Date updatedTime;
    @Column(name = "UPDATED_USER_ID", columnDefinition = "varchar(25) DEFAULT NULL COMMENT 'updated user id'")
    private String updatedUserId;
    @Column(name = "VALID_STATUS", columnDefinition = "int(2) DEFAULT NULL COMMENT '0: valid, 1: invalid'")
    private Integer validStatus = 0;
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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
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
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfo that = (ProductInfo) o;
        return getId().equals(that.getId()) &&
                getProductId().equals(that.getProductId()) &&
                getProductPrice().equals(that.getProductPrice()) &&
                getProductNum().equals(that.getProductNum()) &&
                Objects.equals(getCreatedTime(), that.getCreatedTime()) &&
                Objects.equals(getCreatedUserId(), that.getCreatedUserId()) &&
                Objects.equals(getUpdatedTime(), that.getUpdatedTime()) &&
                Objects.equals(getUpdatedUserId(), that.getUpdatedUserId()) &&
                getValidStatus().equals(that.getValidStatus()) &&
                getVersion().equals(that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductId(), getProductPrice(), getProductNum(), getCreatedTime(), getCreatedUserId(), getUpdatedTime(), getUpdatedUserId(), getValidStatus(), getVersion());
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", productPrice=" + productPrice +
                ", productNum=" + productNum +
                ", createdTime=" + createdTime +
                ", createdUserId='" + createdUserId + '\'' +
                ", updatedTime=" + updatedTime +
                ", updatedUserId='" + updatedUserId + '\'' +
                ", validStatus=" + validStatus +
                ", version=" + version +
                '}';
    }
}
