package com.babakov.service.impl;

import com.babakov.exception.EntityNotFoundException;
import com.babakov.persistence.entity.BaseEntity;
import com.babakov.persistence.entity.Brand;
import com.babakov.persistence.entity.Product;
import com.babakov.persistence.repository.BrandRepository;
import com.babakov.persistence.repository.ProductRepository;
import com.babakov.service.PLPService;
import com.babakov.util.WebUtil;
import com.babakov.web.dto.response.BrandPLPDto;
import com.babakov.web.dto.response.ProductPLPDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PLPServiceImpl implements PLPService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    public PLPServiceImpl(ProductRepository productRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Product> search(Map<String, Object> queryMap) {
        if (queryMap.get(WebUtil.BRAND_PARAM) != null) {
            Long brandId = (Long) queryMap.get(WebUtil.BRAND_PARAM);
            Optional<Brand> brand = brandRepository.findById(brandId);
            if (brand.isEmpty()) {
                throw new EntityNotFoundException("this publisher not found");
            }
            return productRepository.findAllByBrandId(brand.get().getId());
        }
        if (queryMap.get(WebUtil.PRODUCT_SEARCH_PARAM) != null) {
            String productName = (String) queryMap.get(WebUtil.PRODUCT_SEARCH_PARAM);
            return productRepository.findByProductNameContaining(productName);
        }
        return productRepository.findAll();
    }

}
