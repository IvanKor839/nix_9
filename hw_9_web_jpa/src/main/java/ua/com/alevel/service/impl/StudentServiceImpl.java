package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.StudentService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Set<Group> getGroups(Long studentId) {
       return studentDao.getGroups(studentId);
    }

    @Override
    public void create(Student entity) {
        studentDao.create(entity);
    }

    @Override
    public void delete(Long id) {
        if (!studentDao.existById(id)) {
            throw new EntityNotFoundException("student not found");
        }
        studentDao.delete(id);
    }

    @Override
    public void update(Student entity) throws SQLException {
        if (!studentDao.existById(entity.getId())) {
            throw new EntityNotFoundException("student not found");
        }
        studentDao.update(entity);
    }

    @Override
    public Student findById(Long id) {
        if (!studentDao.existById(id)) {
            throw new EntityNotFoundException("student not found");
        }
        return studentDao.findById(id);
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) throws IOException {
        DataTableResponse<Student> dataTableResponse = studentDao.findAll(request);
        dataTableResponse.setItemsSize(studentDao.count());
        return dataTableResponse;
    }
}
