package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.repository.GroupsRepository;
import ua.com.alevel.persistence.repository.StudentRepository;
import ua.com.alevel.service.GroupService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {

    private final CrudRepositoryHelper<Group, GroupsRepository> groupRepositoryHelper;
    private final CrudRepositoryHelper<Student, StudentRepository> studentRepositoryHelper;
    private final GroupsRepository groupsRepository;
    private final StudentRepository studentRepository;

    public GroupServiceImpl(CrudRepositoryHelper<Group, GroupsRepository> groupRepositoryHelper,
                            CrudRepositoryHelper<Student,StudentRepository> studentRepositoryHelper,
                            GroupsRepository groupsRepository,
                            StudentRepository studentRepository) {
        this.groupRepositoryHelper = groupRepositoryHelper;
        this.studentRepositoryHelper = studentRepositoryHelper;
        this.groupsRepository = groupsRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Set<Student> getStudents(Long id) {
        return groupRepositoryHelper.findById(groupsRepository, id).get().getStudents();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addStudent(Long groupId, Long studentId) {
        Group group = groupRepositoryHelper.findById(groupsRepository, groupId).get();
        Student student = studentRepositoryHelper.findById(studentRepository, studentId).get();
        group.addStudent(student);
        groupRepositoryHelper.update(groupsRepository, group);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void removeStudent(Long groupId, Long studentId) {
        Group group = groupRepositoryHelper.findById(groupsRepository, groupId).get();
        Student student = studentRepositoryHelper.findById(studentRepository, studentId).get();
        group.removeStudent(student);
        groupRepositoryHelper.update(groupsRepository, group);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Group entity) {
        groupRepositoryHelper.create(groupsRepository,entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        groupRepositoryHelper.delete(groupsRepository, id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Group entity) throws SQLException {
        groupRepositoryHelper.update(groupsRepository,entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Group> findById(Long id) {
        return groupRepositoryHelper.findById(groupsRepository,id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Group> findAll(DataTableRequest request) throws IOException {
        return groupRepositoryHelper.findAll(groupsRepository,request);
    }
}
