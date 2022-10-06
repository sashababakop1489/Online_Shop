package com.babakov.facade.impl;

import com.babakov.facade.PLPFacade;
import com.babakov.persistence.entity.BaseEntity;
import com.babakov.persistence.entity.Brand;
import com.babakov.persistence.entity.Product;
import com.babakov.service.PLPService;
import com.babakov.util.WebUtil;
import com.babakov.web.dto.response.BrandPLPDto;
import com.babakov.web.dto.response.PLPDto;
import com.babakov.web.dto.response.ProductPLPDto;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PLPFacadeImpl implements PLPFacade {

    private final PLPService plpService;

    public PLPFacadeImpl(PLPService plpService) {
        this.plpService = plpService;
    }

    @Override
    public List<ProductPLPDto> search(WebRequest webRequest) {
        Map<String, Object> queryMap = new HashMap<>();
        if (webRequest.getParameterMap().get(WebUtil.BRAND_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebUtil.BRAND_PARAM);
            Long brandId = Long.parseLong(params[0]);
            queryMap.put(WebUtil.BRAND_PARAM, brandId);
            //loggerService.commit(LoggerLevel.INFO, "add " + WebUtil.PUBLISHER_PARAM + ": " + publisherId);
        }
        if (webRequest.getParameterMap().get(WebUtil.PRODUCT_SEARCH_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebUtil.PRODUCT_SEARCH_PARAM);
            String productName = params[0];
            queryMap.put(WebUtil.PRODUCT_SEARCH_PARAM, productName);
            //loggerService.commit(LoggerLevel.INFO, "add " + WebUtil.BOOK_SEARCH_PARAM + ": " + bookName);
        }

        List<Product> products = plpService.search(queryMap);
        List<ProductPLPDto> productPLPDtos = products.stream().map(ProductPLPDto::new).collect(Collectors.toList());
        return productPLPDtos;
    }
}
