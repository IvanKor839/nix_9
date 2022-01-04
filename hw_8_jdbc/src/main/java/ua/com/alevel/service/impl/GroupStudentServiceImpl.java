package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.GroupStudentDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.GroupStudent;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.service.GroupStudentService;

import java.io.IOException;
import java.sql.SQLException;

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
        if (!groupStudentDao.existById(id)) {
            throw new EntityNotFoundException("record not found");
        }
        groupStudentDao.delete(id);
    }

    @Override
    public void update(GroupStudent entity) throws SQLException {
        if (!groupStudentDao.existById(entity.getId())) {
            throw new EntityNotFoundException("record not found");
        }
        groupStudentDao.update(entity);
    }

    @Override
    public GroupStudent findById(Long id) {
        if (!groupStudentDao.existById(id)) {
            throw new EntityNotFoundException("record not found");
        }
        return groupStudentDao.findById(id);
    }

    @Override
    public DataTableResponse<GroupStudent> findAll(DataTableRequest request) throws IOException {
        DataTableResponse<GroupStudent> dataTableResponse = groupStudentDao.findAll(request);
        dataTableResponse.setItemsSize(groupStudentDao.count());
        return dataTableResponse;
    }
}
