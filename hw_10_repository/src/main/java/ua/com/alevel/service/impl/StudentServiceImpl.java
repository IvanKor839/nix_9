package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.repository.StudentRepository;
import ua.com.alevel.service.StudentService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final CrudRepositoryHelper<Student, StudentRepository> studentRepositoryHelper;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(CrudRepositoryHelper<Student, StudentRepository> studentRepositoryHelper,
                              StudentRepository studentRepository) {
        this.studentRepositoryHelper = studentRepositoryHelper;
        this.studentRepository = studentRepository;
    }

    @Override
    public Set<Group> getGroups(Long studentId) {
       return studentRepositoryHelper.findById(studentRepository, studentId).get().getGroups();
    }

    @Override
    public void create(Student entity) {
        studentRepositoryHelper.create(studentRepository,entity);
    }

    @Override
    public void delete(Long id) {
        studentRepositoryHelper.delete(studentRepository, id);
    }

    @Override
    public void update(Student entity) throws SQLException {
        studentRepositoryHelper.update(studentRepository,entity);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepositoryHelper.findById(studentRepository,id);
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) throws IOException {
        return studentRepositoryHelper.findAll(studentRepository,request);
    }
}
