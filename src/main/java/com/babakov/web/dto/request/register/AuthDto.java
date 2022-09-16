package com.babakov.web.dto.request.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto {

    String email;
    String password;
    String passwordConfirm;
}
