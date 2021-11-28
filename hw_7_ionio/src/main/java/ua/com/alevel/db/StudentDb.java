package ua.com.alevel.db;

import ua.com.alevel.entity.Student;

import java.io.IOException;
import java.util.Collection;

public interface StudentDb extends BaseDb<Student> {

    Collection<Student> findAllStudentInGroup(String idGroup) throws IOException;
}
