package ua.com.alevel.dao;

import ua.com.alevel.entity.Student;

import java.io.IOException;
import java.util.Collection;

public interface StudentDao extends BaseDao<Student>{
    Collection<Student> findAllStudentInGroup(String idGroup) throws IOException;
}