package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import java.util.Set;

public interface GroupDao extends BaseDao<Group>{

    Set<Student> getStudents(Long id);
    void addStudent(Long groupId, Long studentId);
    void removeStudent(Long groupId, Long studentId);
}
