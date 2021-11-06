package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;

public class StudentService {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final StudentDao studentDao = new StudentDao();

    public void create(Student student) {
        LOGGER_INFO.info("student start created");
        studentDao.create(student);
        LOGGER_INFO.info("student finish created");
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public void delete(String id) {
        LOGGER_WARN.warn("student start deleted");
        studentDao.delete(id);
        LOGGER_WARN.warn("student finish deleted");
    }

    public Student findById(String id) {
        try {
            return studentDao.findById(id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("student not found by id: " + id);
        }
        return null;
    }


    public Student[] findAll() {
        return studentDao.findAll();
    }

    public Student[] findAllStudentInGroup(String idGroup) {
        return studentDao.findAllStudentInGroup(idGroup);
    }
}
