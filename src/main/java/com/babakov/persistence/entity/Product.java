package com.babakov.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    public Product(){
        super();
    }

    @Column(name = "name")
    private String productName;
    @Column(name = "description")
    private String description;
    @Column(name = "color")
    private String color;
    @Column(name = "size")
    private String size;
    @Column(name = "price")
    private int price;

    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    private Brand brand;
}
