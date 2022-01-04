package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.GroupStudentDao;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Student;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.service.StudentService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private final GroupStudentDao groupStudentDao;

    public StudentServiceImpl(StudentDao studentDao, GroupStudentDao groupStudentDao) {
        this.studentDao = studentDao;
        this.groupStudentDao = groupStudentDao;
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
        groupStudentDao.deleteAllGroup(id);
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

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public DataTableResponse<Student> findByGroupId(DataTableRequest dataTableRequest, Long groupId) {
        DataTableResponse<Student> dataTableResponse = studentDao.findByGroupId(dataTableRequest, groupId);
        dataTableResponse.setItemsSize(studentDao.countByGroupId(groupId));
        return dataTableResponse;
    }
}
