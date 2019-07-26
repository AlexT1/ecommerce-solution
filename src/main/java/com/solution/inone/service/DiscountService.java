package com.solution.inone.service;

import com.solution.inone.dao.repository.DiscountRuleRepository;
import com.solution.inone.dto.DiscountProductInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DiscountService
 * @Author AlexTong
 * @Date 2019/07/26
 */

@Service
public class DiscountService {

    @Autowired
    private DiscountRuleRepository discountRuleRepository;

    public List<DiscountProductInfo> getDiscountProductInfo(){
        List<DiscountProductInfo> discountProductInfoList = new ArrayList<>();
        discountRuleRepository.findAllByValidStatusEquals(0).forEach(discountRule -> {
            DiscountProductInfo discountProductInfo = new DiscountProductInfo();
            BeanUtils.copyProperties(discountRule, discountProductInfo);
            discountProductInfoList.add(discountProductInfo);
        });
        return discountProductInfoList;
    }
}
