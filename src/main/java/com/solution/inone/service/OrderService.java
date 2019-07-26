package com.solution.inone.service;

import com.solution.inone.dto.AmountCalculateHelper;
import com.solution.inone.dto.CalculateResultDto;
import com.solution.inone.dto.DiscountProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

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

    public CalculateResultDto getRealPayAmount(List<String> productIds) {
        Map<String, Long> categoryMap =  orderDataProcessService.categoryProductCountById(productIds);
        Map<String, Integer> discountProductMap = orderDataProcessService.filterConditionalProduct(categoryMap);
        List<DiscountProductInfo> discountProductInfoList = discountService.getDiscountProductInfo();
        Map<String, BigDecimal> productPriceMap = orderDataProcessService.getAllProductPrice();
        AmountCalculateHelper amountCalculateHelper = calculateTotalAmountOriginal(categoryMap,productPriceMap);
        calculateDiscountAmount(discountProductMap, discountProductInfoList, amountCalculateHelper);
        return buildResponseCalculateResult(amountCalculateHelper);
    }

    private CalculateResultDto buildResponseCalculateResult(AmountCalculateHelper amountCalculateHelper){
        CalculateResultDto calculateResultDto = new CalculateResultDto();
        calculateResultDto.setPrice(
                amountCalculateHelper.getTotalPrice().subtract(amountCalculateHelper.getDiscountPrice()));
        return calculateResultDto;
    }

    public AmountCalculateHelper calculateTotalAmountOriginal(Map<String, Long> categoryMap, Map<String, BigDecimal> productPriceMap) {
        AmountCalculateHelper amountCalculateHelper = new AmountCalculateHelper();
        categoryMap.forEach((k, v) -> amountCalculateHelper.setTotalPrice(
                amountCalculateHelper.getTotalPrice().add(productPriceMap.get(k).multiply(BigDecimal.valueOf(v))).setScale(2, RoundingMode.DOWN)));
        return amountCalculateHelper;
    }

    public AmountCalculateHelper calculateDiscountAmount(Map<String, Integer> discountProductMap, List<DiscountProductInfo> discountProductInfoList, AmountCalculateHelper amountCalculateHelper) {
        discountProductInfoList.stream().filter(discountProductInfo -> discountProductMap.containsKey(discountProductInfo.getProductId()))
                .forEach(discountProductInfo -> {
                    BigInteger divisionValue = BigInteger.valueOf(discountProductMap.get(discountProductInfo.getProductId()))
                            .divide(BigInteger.valueOf(discountProductInfo.getProductNum()));
                    amountCalculateHelper.setDiscountPrice(BigDecimal.valueOf(divisionValue.intValue()).multiply(discountProductInfo.getDiscountPrice()));
                });
        return amountCalculateHelper;
    }

}
