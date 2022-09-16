package com.babakov.web.dto.response;

import com.babakov.persistence.entity.Brand;

public class BrandResponseDto extends ResponseDto {

    String brandName;

    public BrandResponseDto(){}

    public BrandResponseDto(Brand brand){
        this.brandName = brand.getBrandName();
    }

    public String getName() {
        return brandName;
    }

    public void setName(String brandName) {
        this.brandName = brandName;
    }
}
