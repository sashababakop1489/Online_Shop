package com.babakov.facade;

import com.babakov.dto.product.ProductRequestDto;
import com.babakov.dto.product.ProductResponseDto;

import java.util.List;

public interface ProductFacade extends BaseFacade<ProductRequestDto, ProductResponseDto> {
    List<ProductResponseDto> findAllByBrandId(Long brandId);
}
