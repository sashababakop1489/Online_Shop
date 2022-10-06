package com.babakov.facade;

import com.babakov.web.dto.response.ProductPLPDto;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public interface PLPFacade {

    List<ProductPLPDto> search(WebRequest webRequest);
}
