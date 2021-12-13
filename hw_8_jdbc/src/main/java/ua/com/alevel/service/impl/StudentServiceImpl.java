package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.GroupStudentDao;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;
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
        if(studentDao.existById(id)){
            groupStudentDao.deleteAllGroup(id);
            studentDao.delete(id);
        }
    }

    @Override
    public void update(Student entity) throws SQLException {
        studentDao.update(entity);
    }

    @Override
    public Student findById(Long id) {
        return studentDao.findById(id);
    }

    @Override
    public List<Student> findAll() throws IOException {
        return studentDao.findAll();
    }
}
