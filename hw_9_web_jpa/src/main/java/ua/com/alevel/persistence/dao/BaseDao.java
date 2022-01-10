package ua.com.alevel.persistence.dao;


import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;

import java.io.IOException;
import java.sql.SQLException;

public interface BaseDao<ENTITY extends BaseEntity>{

    void create(ENTITY entity);
    void delete(Long id);
    void update(ENTITY entity) throws SQLException;
    boolean existById(Long id);
    ENTITY findById(Long id);
    DataTableResponse<ENTITY> findAll(DataTableRequest request) throws IOException;
    long count();
}
