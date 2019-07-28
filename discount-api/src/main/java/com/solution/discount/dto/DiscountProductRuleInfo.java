package com.solution.discount.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DiscountProductRuleInfo implements Serializable {

    private static final long serialVersionUID = -8053255238366516846L;
    private String productId;
    private Integer productNum;
    private BigDecimal discountRate;

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

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return "DiscountProductRuleInfo{" +
                "productId='" + productId + '\'' +
                ", productNum=" + productNum +
                ", discountRate=" + discountRate +
                '}';
    }
}
