package com.babakov.service.impl;

import com.babakov.persistence.entity.Brand;
import com.babakov.persistence.entity.Product;
import com.babakov.persistence.repository.BrandRepository;
import com.babakov.persistence.repository.ProductRepository;
import com.babakov.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private final BrandRepository brandRepository;
    @Autowired
    private final ProductRepository productRepository;

    public BrandServiceImpl(BrandRepository brandRepository, ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void create(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void update(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            List<Product> products = productRepository.findAllByBrandId(id);
            for (Product product : products)
                productRepository.deleteById(product.getId());
            brandRepository.delete(brand.get());
        }
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).get();
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
