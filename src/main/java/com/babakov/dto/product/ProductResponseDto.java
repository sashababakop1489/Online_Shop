package com.babakov.dto.product;

import com.babakov.dto.ResponseDto;
import com.babakov.persistence.entity.Brand;
import com.babakov.persistence.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponseDto extends ResponseDto {
    private String name;
    private String description;
    private String color;
    private String size;
    private int price;

    private String imageUrl;
    private Integer quantity;
    Brand brand;

    public ProductResponseDto() { }

    public ProductResponseDto(Product product) {
        setId(product.getId());
        this.name = product.getName();
        this.description = product.getDescription();
        this.color = product.getColor();
        this.size = product.getSize();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        this.quantity = product.getQuantity();
        if (product.getBrand() != null) {
            this.brand = product.getBrand();
        }
    }
}
