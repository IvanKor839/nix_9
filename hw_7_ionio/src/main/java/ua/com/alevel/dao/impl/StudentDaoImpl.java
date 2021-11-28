package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.db.StudentDb;
import ua.com.alevel.db.impl.StudentDbImpl;
import ua.com.alevel.entity.Student;

import java.io.IOException;
import java.util.Collection;

public class StudentDaoImpl implements StudentDao {

    private final StudentDb studentDb = new StudentDbImpl().getInstance();

    @Override
    public void create(Student entity) {
        studentDb.create(entity);
    }

    @Override
    public void delete(String id) {
        studentDb.delete(id);
    }

    @Override
    public void update(Student entity) {
        studentDb.update(entity);
    }

    @Override
    public Student findById(String id) {
        return studentDb.findById(id);
    }

    @Override
    public Collection<Student> findAll() throws IOException {
        return studentDb.findAll();
    }

    @Override
    public Collection<Student> findAllStudentInGroup(String idGroup) throws IOException {
        return studentDb.findAllStudentInGroup(idGroup);
    }
}
