package com.babakov.service;

import com.babakov.persistence.entity.BaseEntity;

import java.util.List;

public interface BaseService<E extends BaseEntity> {
    void create(E e);
    void update(E e);
    void delete(Long id);
    E findById(Long id);
    List<E> findAll();

}
