package com.babakov.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    public Brand(){
        super();
    }

    @Column(nullable = false, unique = true)
    private String brandName;

    @Column(name = "image_url")
    private String imageUrl;
}
