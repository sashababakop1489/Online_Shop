package com.babakov.persistence.repository;

import com.babakov.persistence.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends BaseRepository<Product> {

    List<Product> findAllByBrandId(Long brandId);
    List<Product> findByProductNameContaining(String productName);
}
