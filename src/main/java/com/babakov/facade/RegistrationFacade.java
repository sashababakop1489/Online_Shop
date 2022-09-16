package com.babakov.facade;

import com.babakov.web.dto.request.register.AuthDto;

public interface RegistrationFacade {

    void registration(AuthDto dto);
}
