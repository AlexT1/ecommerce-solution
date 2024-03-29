package com.solution.inone.service;

import com.solution.inone.constant.ValidStatus;
import com.solution.inone.dao.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName DiscountProcessService
 * @Author AlexTong
 * @Date 2019/07/26
 */

@Component
public class OrderDataProcessService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     *  group given productIds by productId and count appear frequency
     */
    public Map<String, Long> categoryProductCountById(List<String> productIds){
        return productIds.stream().
        collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    }

    /**
     *  use given category map to return match discount condition productId
     */
    public Map<String, Integer> filterConditionalProduct(Map<String,Long> categoryMap){
        Map<String, Integer> resultMap = new HashMap<String, Integer>();
        categoryMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .filter(map -> map.getValue().intValue() > 1)
                .forEachOrdered(x -> resultMap.put(x.getKey(), Math.toIntExact(x.getValue())));
        return resultMap;
    }

    /**
     *  query given productId price from data base
     */
    public Map<String, BigDecimal> getAllProductPrice(List<String> productIds){
        Map<String, BigDecimal> resultMap = new HashMap<>();
        productInfoRepository.findByValidStatusEqualsAndProductIdIn(ValidStatus.VALID.getStatus(),productIds).forEach(productInfo -> {
            resultMap.put(productInfo.getProductId(), productInfo.getProductPrice());
        });
        return resultMap;
    }

}
