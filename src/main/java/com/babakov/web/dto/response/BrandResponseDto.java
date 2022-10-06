package com.babakov.web.dto.response;

import com.babakov.persistence.entity.Brand;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BrandResponseDto extends ResponseDto {

    String brandName;

    private String imageUrl;

    public BrandResponseDto(){}

    public BrandResponseDto(Brand brand){
        setId(brand.getId());
        this.brandName = brand.getBrandName();
        this.imageUrl = brand.getImageUrl();
    }
}
