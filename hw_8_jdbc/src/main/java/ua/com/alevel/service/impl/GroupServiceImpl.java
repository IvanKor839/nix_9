package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.dao.GroupStudentDao;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.GroupStudent;
import ua.com.alevel.service.GroupService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;
    private final GroupStudentDao groupStudentDao;

    public GroupServiceImpl(GroupDao groupDao, GroupStudentDao groupStudentDao) {
        this.groupDao = groupDao;
        this.groupStudentDao = groupStudentDao;
    }

    @Override
    public void create(Group entity) {
        groupDao.create(entity);
    }

    @Override
    public void delete(Long id) {
        if(groupDao.existById(id)){
            groupStudentDao.deleteAllStudent(id);
            groupDao.delete(id);
        }
    }

    @Override
    public void update(Group entity) throws SQLException {
        groupDao.update(entity);
    }

    @Override
    public Group findById(Long id) {
        return groupDao.findById(id);
    }

    @Override
    public List<Group> findAll() throws IOException {
        return groupDao.findAll();
    }
}
