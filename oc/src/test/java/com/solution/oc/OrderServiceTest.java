package com.solution.oc;

import com.solution.oc.service.OrderCenterService;
import com.solution.oc.service.OrderDataProcessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderCenterService orderCenterService;

    @Autowired
    private OrderDataProcessService orderDataProcessService;

    private static final  List<String> productIds = Arrays.asList("001","002","003","004");

    @Test
    public void getFinalPayAmount() {
        orderCenterService.getFinalPayAmount(productIds);
    }

    @Test
    public void categoryProductCountById() {
        orderDataProcessService.categoryProductCountById(productIds);
    }

    @Test
    public void filterConditionalProduct(){
        orderDataProcessService.filterConditionalProduct(orderDataProcessService.categoryProductCountById(productIds));
    }
}
