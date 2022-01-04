package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.dao.GroupStudentDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Group;
import ua.com.alevel.exception.EntityNotFoundException;
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
        if (!groupDao.existById(id)) {
            throw new EntityNotFoundException("group not found");
        }
        groupStudentDao.deleteAllStudent(id);
        groupDao.delete(id);
    }

    @Override
    public void update(Group entity) throws SQLException {
        if (!groupDao.existById(entity.getId())) {
            throw new EntityNotFoundException("group not found");
        }
        groupDao.update(entity);
    }

    @Override
    public Group findById(Long id) {
        if (!groupDao.existById(id)) {
            throw new EntityNotFoundException("group not found");
        }
        return groupDao.findById(id);
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) throws IOException {
        DataTableResponse<Group> dataTableResponse = groupDao.findAll(request);
        dataTableResponse.setItemsSize(groupDao.count());
        return dataTableResponse;
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public DataTableResponse<Group> findByStudentId(DataTableRequest dataTableRequest, Long studentId) {
        DataTableResponse<Group> dataTableResponse = groupDao.findByStudentId(dataTableRequest, studentId);
        dataTableResponse.setItemsSize(groupDao.countByStudentId(studentId));
        return dataTableResponse;
    }
}
