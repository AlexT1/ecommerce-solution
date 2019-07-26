package com.solution.inone.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AmountCalculateHelper implements Serializable {

    private static final long serialVersionUID = 2581151807409463877L;

    private BigDecimal totalPrice = BigDecimal.ZERO;
    private BigDecimal discountPrice = BigDecimal.ZERO;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }
}
