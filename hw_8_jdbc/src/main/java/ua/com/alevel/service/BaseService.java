package ua.com.alevel.service;

import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.BaseEntity;

import java.io.IOException;
import java.sql.SQLException;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void delete(Long id);
    void update(ENTITY entity) throws SQLException;
    ENTITY findById(Long id);
    DataTableResponse<ENTITY> findAll(DataTableRequest request) throws IOException;
}
