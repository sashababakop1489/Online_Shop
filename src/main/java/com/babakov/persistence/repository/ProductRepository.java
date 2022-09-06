package com.babakov.persistence.repository;

import com.babakov.persistence.entity.Product;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product> {

    List<Product> findAllByBrandId(Long brandId);
}
