package com.babakov.service.impl;

import com.babakov.persistence.entity.Product;
import com.babakov.persistence.repository.ProductRepository;
import com.babakov.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void create(Product team) {
        productRepository.save(team);
    }

    @Override
    public void update(Product team) {
        productRepository.save(team);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(findById(id));
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByBrandId(Long brandId) {
        return productRepository.findAllByBrandId(brandId);
    }
}
