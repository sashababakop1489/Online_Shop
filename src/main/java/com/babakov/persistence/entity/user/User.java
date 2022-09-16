package com.babakov.persistence.entity.user;

import com.babakov.persistence.entity.BaseEntity;
import com.babakov.persistence.type.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false)
    private RoleType roleType;

    public User(){
        super();
        this.enabled = true;
    }
}
