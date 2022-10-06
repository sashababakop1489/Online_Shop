package com.babakov.service;

import com.babakov.persistence.entity.Brand;
import java.util.List;
import java.util.Map;

public interface BrandPLPService {

    List<Brand> search(Map<String, Object> queryMap);

}
