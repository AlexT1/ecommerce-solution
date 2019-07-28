package com.solution.discount.service;

import com.solution.discount.api.IDiscountRuleService;
import com.solution.discount.constant.ValidStatus;
import com.solution.discount.dao.repository.DiscountRuleRepository;
import com.solution.discount.dto.DiscountProductRuleInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DiscountRuleService implements IDiscountRuleService {

    private static final Logger logger = LoggerFactory.getLogger(DiscountRuleService.class);

    @Autowired
    private DiscountRuleRepository discountRuleRepository;

    /**
     * query match product ids discount rule and separate to build discount rule info return
     * it's does not include case of one productId appears in different rules
     */
    @Override
    public List<DiscountProductRuleInfo> getProductsDiscountRate(List<String> productIds) {
        List<DiscountProductRuleInfo> discountProductRuleInfoList = new ArrayList<>();
        discountRuleRepository.findByValidStatusEqualsAndProductIdsContains(ValidStatus.VALID.getStatus(), Objects.toString(productIds))
                .forEach(discountRule -> {
                    productIds.forEach( id -> {
                        if (discountRule.getProductIds().contains(id)){
                            DiscountProductRuleInfo discountProductRuleInfo = new DiscountProductRuleInfo();
                            discountProductRuleInfo.setDiscountRate(discountRule.getDiscountRate());
                            discountProductRuleInfo.setProductId(id);
                            discountProductRuleInfo.setProductNum(discountRule.getProductNum());
                            discountProductRuleInfoList.add(discountProductRuleInfo);
                        }
                    });
                });
        return discountProductRuleInfoList;
    }
}
