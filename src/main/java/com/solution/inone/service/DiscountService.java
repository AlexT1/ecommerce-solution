package com.solution.inone.service;

import com.solution.inone.constant.ValidStatus;
import com.solution.inone.dao.repository.DiscountRuleRepository;
import com.solution.inone.dto.DiscountProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
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

    private static final Logger logger = LoggerFactory.getLogger(DiscountService.class);

    @Autowired
    private DiscountRuleRepository discountRuleRepository;

    /**
     *  query discount rule product info from data base
     */
    public List<DiscountProductInfo> getDiscountProductInfo(){
        List<DiscountProductInfo> discountProductInfoList = new ArrayList<>();
        discountRuleRepository.findAllByValidStatusEquals(ValidStatus.VALID.getStatus()).forEach(discountRule -> {
            DiscountProductInfo discountProductInfo = new DiscountProductInfo();
            try {
                BeanUtils.copyProperties(discountRule, discountProductInfo);
            } catch (BeansException beansException) {
                logger.error(beansException.getMessage());
            }
            discountProductInfoList.add(discountProductInfo);
        });
        return discountProductInfoList;
    }
}
