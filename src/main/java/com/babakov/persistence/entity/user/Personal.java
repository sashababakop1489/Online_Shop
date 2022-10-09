package com.babakov.persistence.entity.user;

import com.babakov.persistence.listener.FullNameGenerationListener;
import com.babakov.persistence.type.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
@DiscriminatorValue("PERSONAL")
@EntityListeners({
        FullNameGenerationListener.class,
})
public class Personal extends User{

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    @Transient
    private Integer age;

     public Personal(){
         super();
         setRoleType(RoleType.ROLE_PERSONAL);
     }
}
