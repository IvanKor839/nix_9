package ua.com.alevel.service;

import ua.com.alevel.entity.Student;

import java.io.IOException;
import java.util.Collection;

public interface StudentService extends BaseService<Student>{

    Collection<Student> findAllStudentInGroup(String idGroup) throws IOException;
}