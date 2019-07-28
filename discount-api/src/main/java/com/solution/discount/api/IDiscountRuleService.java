package com.solution.discount.api;

import com.solution.discount.dto.DiscountProductRuleInfo;

import java.util.List;

public interface IDiscountRuleService {

    List<DiscountProductRuleInfo> getProductsDiscountRate(List<String> productIds);
}
