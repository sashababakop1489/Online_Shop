package com.babakov.service;

import com.babakov.persistence.datatable.DataTableRequest;
import com.babakov.persistence.datatable.DataTableResponse;
import com.babakov.persistence.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity> {
    void create(E entity);
    void update(E entity);
    void delete(Long id);
    Optional<E> findById(Long id);
    DataTableResponse<E> findAll(DataTableRequest request);

}
