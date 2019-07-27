package com.solution.inone.service;

import com.solution.inone.dto.AmountCalculateHelper;
import com.solution.inone.dto.CalculateResultDto;
import com.solution.inone.dto.DiscountProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

/**
 * @ClassName OrderService
 * @Author AlexTong
 * @Date 2019/07/26
 */

@Service
public class OrderService {
    @Autowired
    private DiscountService discountService;
    @Autowired
    private OrderDataProcessService orderDataProcessService;

    /**
     *  calculate given products real pay amount and return
     */
    public CalculateResultDto getRealPayAmount(List<String> productIds) {
        Map<String, Long> categoryMap =  orderDataProcessService.categoryProductCountById(productIds);
        Map<String, Integer> discountProductMap = orderDataProcessService.filterConditionalProduct(categoryMap);
        Map<String, BigDecimal> productPriceMap = orderDataProcessService.getAllProductPrice(productIds);
        AmountCalculateHelper amountCalculateHelper = calculateTotalAmountOriginal(categoryMap,productPriceMap);
        if (discountProductMap.size() > 0) {
            List<DiscountProductInfo> discountProductInfoList = discountService.getDiscountProductInfo();
            calculateDiscountAmount(discountProductMap, discountProductInfoList, amountCalculateHelper);
        }
        return buildResponseCalculateResult(amountCalculateHelper);
    }

    /**
     *  build response request dto CalculateResultDto
     */
    private CalculateResultDto buildResponseCalculateResult(AmountCalculateHelper amountCalculateHelper){
        CalculateResultDto calculateResultDto = new CalculateResultDto();
        calculateResultDto.setPrice(
                amountCalculateHelper.getTotalPrice().subtract(amountCalculateHelper.getDiscountPrice()).setScale(2, RoundingMode.DOWN));
        return calculateResultDto;
    }

    /**
     *  calculate original price for whole given productIds
     */
    public AmountCalculateHelper calculateTotalAmountOriginal(Map<String, Long> categoryMap, Map<String, BigDecimal> productPriceMap) {
        AmountCalculateHelper amountCalculateHelper = new AmountCalculateHelper();
        categoryMap.forEach((k, v) -> amountCalculateHelper.setTotalPrice(
                amountCalculateHelper.getTotalPrice().add(productPriceMap.get(k).multiply(BigDecimal.valueOf(v))).setScale(2, RoundingMode.DOWN)));
        return amountCalculateHelper;
    }

    /**
     *  calculate whole discount price for given products
     */
    public AmountCalculateHelper calculateDiscountAmount(Map<String, Integer> discountProductMap, List<DiscountProductInfo> discountProductInfoList, AmountCalculateHelper amountCalculateHelper) {
        discountProductInfoList.stream().filter(discountProductInfo -> discountProductMap.containsKey(discountProductInfo.getProductId()))
                .forEach(discountProductInfo -> {
                    BigInteger divisionValue = BigInteger.valueOf(discountProductMap.get(discountProductInfo.getProductId()))
                            .divide(BigInteger.valueOf(discountProductInfo.getProductNum()));
                    amountCalculateHelper.setDiscountPrice(
                            amountCalculateHelper.getDiscountPrice().add(BigDecimal.valueOf(divisionValue.intValue())
                                    .multiply(discountProductInfo.getDiscountPrice())).setScale(2, RoundingMode.DOWN));
                });
        return amountCalculateHelper;
    }

}
