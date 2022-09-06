package com.babakov.dto.brand;

import com.babakov.dto.ResponseDto;
import com.babakov.persistence.entity.Brand;

public class BrandResponseDto extends ResponseDto {

    String name;

    public BrandResponseDto(){}

    public BrandResponseDto(Brand brand){
        this.name = brand.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
