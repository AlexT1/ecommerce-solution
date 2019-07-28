package com.solution.inone.controller;

import com.solution.inone.dto.CalculateResultDto;
import com.solution.inone.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName OrderInfoController
 * @Author AlexTong
 * @Date 2019/07/26
 */

@RestController
public class OrderInfoController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/checkout", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public CalculateResultDto checkoutRealPayAmount(@RequestBody List<String> productIds) {
        try {
            if (!productIds.isEmpty()) {
                return orderService.getRealPayAmount(productIds);
            }
        } catch (Exception e) {
            String errorMsg = String.format("checkoutRealPayAmount productIds: %s  error", productIds);
            throw new RuntimeException(errorMsg, e);
        }
        return new CalculateResultDto();
    }
}
