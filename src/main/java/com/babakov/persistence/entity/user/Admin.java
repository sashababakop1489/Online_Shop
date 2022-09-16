package com.babakov.persistence.entity.user;

import com.babakov.persistence.type.RoleType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    public Admin(){
        super();
        setRoleType(RoleType.ROLE_ADMIN);
    }

}
