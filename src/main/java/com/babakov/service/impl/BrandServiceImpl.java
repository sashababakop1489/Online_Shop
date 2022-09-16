package com.babakov.service.impl;

import com.babakov.persistence.crud.CrudRepositoryHelper;
import com.babakov.persistence.datatable.DataTableRequest;
import com.babakov.persistence.datatable.DataTableResponse;
import com.babakov.persistence.entity.Brand;
import com.babakov.persistence.repository.BrandRepository;
import com.babakov.service.BrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final CrudRepositoryHelper<Brand, BrandRepository> crudRepositoryHelper;

    public BrandServiceImpl(BrandRepository brandRepository, CrudRepositoryHelper<Brand, BrandRepository> crudRepositoryHelper) {
        this.brandRepository = brandRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Brand entity) {
        crudRepositoryHelper.create(brandRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Brand entity) {
        crudRepositoryHelper.update(brandRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(brandRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Brand> findById(Long id) {
        return crudRepositoryHelper.findById(brandRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Brand> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(brandRepository, request);
    }
}
