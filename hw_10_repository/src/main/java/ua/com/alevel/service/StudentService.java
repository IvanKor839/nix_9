package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import java.util.Set;

public interface StudentService extends BaseService<Student> {

    Set<Group> getGroups(Long studentId);
}
