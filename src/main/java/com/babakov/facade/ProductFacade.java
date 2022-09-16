package com.babakov.facade;

import com.babakov.web.dto.request.ProductRequestDto;
import com.babakov.web.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductFacade extends BaseFacade<ProductRequestDto, ProductResponseDto> {

    List<ProductResponseDto> findAllByBrandId(Long brandId);

}
