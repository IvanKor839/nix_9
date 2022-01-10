package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.GroupService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Set<Student> getStudents(Long id) {
        return groupDao.getStudents(id);
    }

    @Override
    public void addStudent(Long groupId, Long studentId) {
        groupDao.addStudent(groupId,studentId);
    }

    @Override
    public void removeStudent(Long groupId, Long studentId) {
        groupDao.removeStudent(groupId,studentId);
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
}
