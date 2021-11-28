package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.db.impl.GroupDbImpl;
import ua.com.alevel.entity.Group;

import java.io.IOException;
import java.util.Collection;

public class GroupDaoImpl implements GroupDao {

    @Override
    public void create(Group entity) {
        GroupDbImpl.getInstance().create(entity);
    }

    @Override
    public void delete(String id) {
        GroupDbImpl.getInstance().delete(id);
    }

    @Override
    public void update(Group entity) {
        GroupDbImpl.getInstance().update(entity);
    }

    @Override
    public Group findById(String id) {
        return GroupDbImpl.getInstance().findById(id);
    }

    @Override
    public Collection<Group> findAll() throws IOException {
        return GroupDbImpl.getInstance().findAll();
    }
}