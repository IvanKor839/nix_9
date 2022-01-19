package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void delete(Long id);
    void update(ENTITY entity) throws SQLException;
    Optional<ENTITY> findById(Long id);
    DataTableResponse<ENTITY> findAll(DataTableRequest request) throws IOException;
}
