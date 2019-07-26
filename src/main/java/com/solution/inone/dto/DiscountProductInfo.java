package com.solution.inone.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DiscountProductInfo implements Serializable {
    private static final long serialVersionUID = -2561852140060973343L;

    private String productId;
    private Integer productNum;
    private BigDecimal discountPrice;

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

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "DiscountProductInfo{" +
                "productId='" + productId + '\'' +
                ", productNum=" + productNum +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
