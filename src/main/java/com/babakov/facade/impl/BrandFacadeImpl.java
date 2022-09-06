package com.babakov.facade.impl;

import com.babakov.dto.brand.BrandRequestDto;
import com.babakov.dto.brand.BrandResponseDto;
import com.babakov.facade.BrandFacade;
import com.babakov.persistence.entity.Brand;
import com.babakov.service.BrandService;

import java.util.List;
import java.util.stream.Collectors;

public class BrandFacadeImpl implements BrandFacade {

    private final BrandService brandService;

    public BrandFacadeImpl(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public void create(BrandRequestDto brandRequestDto) {
        Brand brand = new Brand();
        brand.setName(brandRequestDto.getName());
        brandService.create(brand);
    }

    @Override
    public void update(BrandRequestDto brandRequestDto, Long id) {
        Brand brand = brandService.findById(id);
        brand.setName(brandRequestDto.getName());
        brandService.update(brand);
    }

    @Override
    public void delete(Long id) {
        brandService.delete(id);
    }

    @Override
    public BrandResponseDto findById(Long id) {
        Brand brand = brandService.findById(id);
        return new BrandResponseDto(brand);
    }

    @Override
    public List<BrandResponseDto> findAll() {
        return brandService.findAll().stream().map(BrandResponseDto::new).collect(Collectors.toList());
    }
}
