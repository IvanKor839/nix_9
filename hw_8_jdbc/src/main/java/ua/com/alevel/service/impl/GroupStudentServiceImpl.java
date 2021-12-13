package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.GroupStudentDao;
import ua.com.alevel.entity.GroupStudent;
import ua.com.alevel.service.GroupStudentService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class GroupStudentServiceImpl implements GroupStudentService {

    private final GroupStudentDao groupStudentDao;

    public GroupStudentServiceImpl(GroupStudentDao groupStudentDao) {
        this.groupStudentDao = groupStudentDao;
    }

    @Override
    public void create(GroupStudent entity) {
        groupStudentDao.create(entity);
    }

    @Override
    public void delete(Long id) {
        groupStudentDao.delete(id);
    }

    @Override
    public void update(GroupStudent entity) throws SQLException {
        groupStudentDao.update(entity);
    }

    @Override
    public GroupStudent findById(Long id) {
        return groupStudentDao.findById(id);
    }

    @Override
    public List<GroupStudent> findAll() throws IOException {
        return groupStudentDao.findAll();
    }
}
