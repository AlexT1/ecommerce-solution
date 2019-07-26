package com.solution.inone.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName CalculateResultDto
 * @Author AlexTong
 * @Date 2019/07/26
 */

public class CalculateResultDto implements Serializable {

    private static final long serialVersionUID = -1070913412613693513L;

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
