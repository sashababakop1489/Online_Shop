package com.babakov.facade.impl;

import com.babakov.facade.RegistrationFacade;
import com.babakov.persistence.entity.user.Personal;
import com.babakov.service.PersonalService;
import com.babakov.web.dto.request.register.AuthDto;
import org.springframework.stereotype.Service;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final PersonalService personalService;

    public RegistrationFacadeImpl(PersonalService personalService) {
        this.personalService = personalService;
    }

    @Override
    public void registration(AuthDto dto) {
        Personal personal = new Personal();
        personal.setEmail(dto.getEmail());
        personal.setPassword(dto.getPassword());
        personalService.create(personal);
    }
}
