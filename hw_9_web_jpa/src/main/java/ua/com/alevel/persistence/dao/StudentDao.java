package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import java.util.Set;

public interface StudentDao extends BaseDao<Student> {

    Set<Group> getGroups(Long studentId);
}
