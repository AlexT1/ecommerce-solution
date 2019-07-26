package com.solution.inone.dao.repository;

import com.solution.inone.dao.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    Stream<ProductInfo> findAllByValidStatusEquals(int validStatus);
}
