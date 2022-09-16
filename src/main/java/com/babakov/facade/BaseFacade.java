package com.babakov.facade;

import com.babakov.web.dto.request.RequestDto;
import com.babakov.web.dto.response.ResponseDto;
import com.babakov.web.dto.response.PageData;
import org.springframework.web.context.request.WebRequest;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto> {
    void create(REQ req);
    void update(REQ req, Long id);
    void delete(Long id);
    RES findById(Long id);
    PageData<RES> findAll(WebRequest request);
}
