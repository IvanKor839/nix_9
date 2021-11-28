package ua.com.alevel.dao;

import ua.com.alevel.entity.BaseEntity;

import java.io.IOException;
import java.util.Collection;

public interface BaseDao<ENTITY extends BaseEntity> {

    void create(ENTITY entity);

    void delete(String id);

    void update(ENTITY entity);

    ENTITY findById(String id);

    Collection<ENTITY> findAll() throws IOException;
}
