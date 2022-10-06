package com.babakov.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDto extends RequestDto {

    private String productName;
    private String description;
    private String color;
    private String size;
    private String productImage;
    private Integer quantity;
    private Long brandId;
    private int price;

}
