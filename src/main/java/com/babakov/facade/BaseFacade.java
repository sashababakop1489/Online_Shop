package com.babakov.facade;

import com.babakov.dto.RequestDto;
import com.babakov.dto.ResponseDto;

import java.util.List;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto> {
    void create(REQ req);
    void update(REQ req, Long id);
    void delete(Long id);
    RES findById(Long id);
    List<RES> findAll();
}
