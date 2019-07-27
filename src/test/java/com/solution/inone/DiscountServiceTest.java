package com.solution.inone;

import com.solution.inone.service.DiscountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SolutionApplication.class,webEnvironment = DEFINED_PORT)
public class DiscountServiceTest {

    @Autowired
    private DiscountService discountService;

    @Test
    public void getDiscountProductInfo() {
        discountService.getDiscountProductInfo();
    }
}
