package com.babakov.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    public Brand(){
        super();
    }

    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
