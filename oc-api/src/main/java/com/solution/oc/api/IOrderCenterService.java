package com.solution.oc.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IOrderCenterService {

    Map<String, BigDecimal> getFinalPayAmount(List<String> productIds);
}
