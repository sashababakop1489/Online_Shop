package com.babakov.persistence.crud;

import com.babakov.persistence.datatable.DataTableRequest;
import com.babakov.persistence.datatable.DataTableResponse;
import com.babakov.persistence.entity.BaseEntity;
import com.babakov.persistence.repository.BaseRepository;

import java.util.Optional;

public interface CrudRepositoryHelper<E extends BaseEntity, R extends BaseRepository<E>>{

    void create(R repository, E entity);
    void update(R repository, E entity);
    void delete(R repository, Long id);
    Optional<E> findById(R repository, Long id);
    DataTableResponse<E> findAll(R repository, DataTableRequest request);
}
