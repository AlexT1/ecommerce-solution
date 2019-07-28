package com.solution.oc.service;

import com.solution.discount.api.IDiscountRuleService;
import com.solution.discount.dto.DiscountProductRuleInfo;
import com.solution.oc.api.IOrderCenterService;
import com.solution.oc.dto.AmountCalculateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OrderCenterService implements IOrderCenterService {

    private static final Logger logger = LoggerFactory.getLogger(OrderCenterService.class);

    @Autowired
    private IDiscountRuleService iDiscountRuleService;

    @Autowired
    private OrderDataProcessService orderDataProcessService;

    /**
     *  calculate given products final pay amount and return
     */
    @Override
    public Map<String, BigDecimal> getFinalPayAmount(List<String> productIds) {
        Map<String, BigDecimal> resultMap = new HashMap<>();
        Map<String, Long> categoryMap =  orderDataProcessService.categoryProductCountById(productIds);
        Map<String, Integer> discountProductMap = orderDataProcessService.filterConditionalProduct(categoryMap);
        Map<String, BigDecimal> productPriceMap = orderDataProcessService.getAllProductPrice(productIds);
        AmountCalculateHelper amountCalculateHelper = calculateTotalAmountOriginal(categoryMap,productPriceMap);
        if (discountProductMap.size() > 0) {
            List<DiscountProductRuleInfo> discountProductInfoList = iDiscountRuleService.getProductsDiscountRate(
                    new ArrayList<>(discountProductMap.keySet()));
            calculateDiscountAmount(discountProductMap, discountProductInfoList, productPriceMap, amountCalculateHelper);
        }
        resultMap.put("price", amountCalculateHelper.getTotalPrice().subtract(amountCalculateHelper.getDiscountPrice()).setScale(2,RoundingMode.DOWN));
        return resultMap;
    }

    /**
     *  calculate original price for whole given productIds
     */
    private AmountCalculateHelper calculateTotalAmountOriginal(Map<String, Long> categoryMap, Map<String, BigDecimal> productPriceMap) {
        AmountCalculateHelper amountCalculateHelper = new AmountCalculateHelper();
        categoryMap.forEach((k, v) -> amountCalculateHelper.setTotalPrice(
                amountCalculateHelper.getTotalPrice().add(productPriceMap.get(k).multiply(BigDecimal.valueOf(v))).setScale(2, RoundingMode.DOWN)));
        return amountCalculateHelper;
    }

    /**
     *  calculate whole discount price for given products
     */
    private AmountCalculateHelper calculateDiscountAmount(Map<String, Integer> discountProductMap, List<DiscountProductRuleInfo> discountProductRuleInfoList,
                                                          Map<String, BigDecimal> productPriceMap, AmountCalculateHelper amountCalculateHelper) {
        discountProductRuleInfoList.forEach(discountProductInfo -> {
                    BigInteger divisionValue = BigInteger.valueOf(discountProductMap.get(discountProductInfo.getProductId()))
                            .divide(BigInteger.valueOf(discountProductInfo.getProductNum()));
                    BigDecimal discountTotalRate = BigDecimal.valueOf(divisionValue.intValue()).multiply(discountProductInfo.getDiscountRate());
                    amountCalculateHelper.setDiscountPrice(
                            amountCalculateHelper.getDiscountPrice().add(
                                    discountTotalRate.multiply(productPriceMap.get(discountProductInfo.getProductId())))
                                    .setScale(2, RoundingMode.DOWN));
                });
        return amountCalculateHelper;
    }

}
