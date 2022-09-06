package com.babakov.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(name = "name")
    private String name;
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

    @OneToMany
    private Brand brand;

    public Product(){
        super();
    }
}
