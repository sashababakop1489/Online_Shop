package com.babakov.dto.product;

import com.babakov.dto.RequestDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDto extends RequestDto {

    private String name;
    private String description;
    private String color;
    private String size;
    private String imageUrl;
    private Integer quantity;
    private Long brandId;
    private int price;
}
