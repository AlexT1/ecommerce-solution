package com.solution.inone;

import com.solution.inone.dto.AmountCalculateHelper;
import com.solution.inone.dto.DiscountProductInfo;
import com.solution.inone.service.DiscountService;
import com.solution.inone.service.OrderDataProcessService;
import com.solution.inone.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SolutionApplication.class,webEnvironment = DEFINED_PORT)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private OrderDataProcessService orderDataProcessService;

    private static final  List<String> productIds = Arrays.asList("001","002","003","004");

    @Test
    public void getRealPayAmount() {
        orderService.getRealPayAmount(productIds);
    }

    @Test
    public void categoryProductCountById() {
        orderDataProcessService.categoryProductCountById(productIds);
    }

    @Test
    public void calculateTotalAmountOriginal() {
        Map<String, Long> categoryMap = orderDataProcessService.categoryProductCountById(productIds);
        Map<String, BigDecimal > productPriceMap= orderDataProcessService.getAllProductPrice(productIds);
        AmountCalculateHelper amountCalculateHelper = orderService.calculateTotalAmountOriginal(categoryMap, productPriceMap);
        System.out.println("original total price: " + amountCalculateHelper.getTotalPrice());
    }

    public void calculateDiscountAmount() {
        Map<String, Long> categoryMap = orderDataProcessService.categoryProductCountById(productIds);
        Map<String, Integer> discountProductMap = orderDataProcessService.filterConditionalProduct(categoryMap);
        List< DiscountProductInfo > discountProductInfoList =  discountService.getDiscountProductInfo();
        AmountCalculateHelper amountCalculateHelper= new AmountCalculateHelper();
        amountCalculateHelper = orderService.calculateDiscountAmount(discountProductMap, discountProductInfoList, amountCalculateHelper);
    }

    public List<DiscountProductInfo>  testDataSet(){
        List<DiscountProductInfo> discountProductInfos = new ArrayList<>();
        DiscountProductInfo discountProductInfo = new DiscountProductInfo();
        discountProductInfo.setDiscountPrice(BigDecimal.valueOf(100));
        discountProductInfo.setProductId("001");
        discountProductInfo.setProductNum(3);
        discountProductInfos.add(discountProductInfo);
        discountProductInfo.setProductNum(2);
        discountProductInfo.setProductId("002");
        discountProductInfo.setDiscountPrice(BigDecimal.valueOf(40));
        discountProductInfos.add(discountProductInfo);
        return discountProductInfos;
    }
}
