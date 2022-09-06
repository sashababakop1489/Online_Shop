package com.babakov.service;

import com.babakov.persistence.entity.Product;
import com.babakov.persistence.repository.ProductRepository;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    List<Product> findAllByBrandId(Long brandId);
}
