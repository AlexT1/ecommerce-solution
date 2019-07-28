package com.solution.discount;

import com.solution.discount.service.DiscountRuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscountServiceTest {
    private static final List<String> productIds = Arrays.asList("001","002","003","004");

    @Autowired
    private DiscountRuleService discountRuleService;


    @Test
    public void getDiscountProductInfo() {
        discountRuleService.getProductsDiscountRate(productIds);
    }
}
