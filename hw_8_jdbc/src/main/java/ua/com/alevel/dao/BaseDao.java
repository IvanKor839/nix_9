package ua.com.alevel.dao;

import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.BaseEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BaseDao<ENTITY extends BaseEntity>{

    void create(ENTITY entity);
    void delete(Long id);
    void update(ENTITY entity) throws SQLException;
    boolean existById(Long id);
    ENTITY findById(Long id);
    DataTableResponse<ENTITY> findAll(DataTableRequest request) throws IOException;
    long count();
}
