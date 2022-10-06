package com.babakov.service;

import com.babakov.persistence.entity.BaseEntity;
import com.babakov.persistence.entity.Brand;
import com.babakov.persistence.entity.Product;
import com.babakov.web.dto.response.BrandPLPDto;

import java.util.List;
import java.util.Map;

public interface PLPService {

    List<Product> search(Map<String, Object> queryMap);
}
