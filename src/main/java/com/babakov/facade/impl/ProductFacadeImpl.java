package com.babakov.facade.impl;

import com.babakov.dto.product.ProductRequestDto;
import com.babakov.dto.product.ProductResponseDto;
import com.babakov.facade.ProductFacade;
import com.babakov.persistence.entity.Brand;
import com.babakov.persistence.entity.Product;
import com.babakov.service.BrandService;
import com.babakov.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

public class ProductFacadeImpl implements ProductFacade {

    private final ProductService productService;
    private final BrandService brandService;

    public ProductFacadeImpl(ProductService productService, BrandService brandService) {
        this.productService = productService;
        this.brandService = brandService;
    }

    @Override
    public void create(ProductRequestDto productRequestDto) {
        Brand brand = brandService.findById(productRequestDto.getBrandId());
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setColor(productRequestDto.getColor());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setQuantity(productRequestDto.getQuantity());
        product.setBrand(brand);
        productService.create(product);
    }

    @Override
    public void update(ProductRequestDto productRequestDto, Long id) {
        Product product = productService.findById(id);
        product.setName(productRequestDto.getName());
        product.setColor(productRequestDto.getColor());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setQuantity(productRequestDto.getQuantity());;
        productService.update(product);
    }

    @Override
    public void delete(Long id) {
        productService.delete(id);
    }

    @Override
    public ProductResponseDto findById(Long id) {

        return new ProductResponseDto(productService.findById(id));
    }

    @Override
    public List<ProductResponseDto> findAll() {
        return convertToDtoByEntity(productService.findAll());
    }

    @Override
    public List<ProductResponseDto> findAllByBrandId(Long brandId) {
        return convertToDtoByEntity(productService.findAllByBrandId(brandId));
    }

    private List<ProductResponseDto> convertToDtoByEntity(List<Product> players) {
        return players.stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }
}
