package com.solution.discount.dao.repository;

import com.solution.discount.dao.pojo.DiscountRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface DiscountRuleRepository extends JpaRepository<DiscountRule, String> {
    Stream<DiscountRule> findByValidStatusEqualsAndProductIdsContains(int validStatus, String productIds);
}
