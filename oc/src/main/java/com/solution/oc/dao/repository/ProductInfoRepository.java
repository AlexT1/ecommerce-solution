package com.solution.oc.dao.repository;

import com.solution.oc.dao.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    Stream<ProductInfo> findByValidStatusEqualsAndProductIdIn(int validStatus, List<String> productIds);
}
