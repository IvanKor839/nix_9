package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void delete(Long id);
    void update(ENTITY entity) throws SQLException;
    ENTITY findById(Long id);
    List<ENTITY> findAll() throws IOException;
}
