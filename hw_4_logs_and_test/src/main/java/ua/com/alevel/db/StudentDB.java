package ua.com.alevel.db;

import ua.com.alevel.entity.Student;

import java.util.Arrays;
import java.util.UUID;

public class StudentDB {

    int index = 0;
    public final int SIZE = 0;
    private Student[] students;
    private static StudentDB instance;

    private StudentDB() {
        students = new Student[SIZE];
    }

    public static StudentDB getInstance() {
        if (instance == null) {
            instance = new StudentDB();
        }
        return instance;
    }

    private void add(Student student) {
        students = Arrays.copyOf(students, students.length + 1);
        students[students.length - 1] = student;
    }

    public void create(Student student) {
        student.setId(generateId());
        add(student);
    }

    public void update(Student student) {
        Student current = findById(student.getId());
        current.setIdGroup(student.getIdGroup());
        current.setName(student.getName());
    }

    public void delete(String id) {
        Student studentDelete = findById(id);
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId().equals(String.valueOf(studentDelete.getId()))) {
                students[i] = null;
                index = i;
            }
        }
        for (int i = index; i < students.length - 1; i++) {
            students[i] = students[i + 1];
        }
        students = Arrays.copyOf(students, students.length - 1);
    }

    public Student findById(String id) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId().equals(id)) {
                return students[i];
            }
        }
        return null;
    }

    public Student[] findAll() {
        return students;
    }

    public Student[] findAllStudentInGroup(String idGroup) {
        Student[] tempStudents = new Student[students.length];
        int currentStudent = 0;
        for (Student student : students) {
            if (student != null && student.getIdGroup().equals(idGroup)) {
                tempStudents[currentStudent] = student;
                currentStudent++;
            }

        }
        Student[] studentsResult = new Student[currentStudent];
        System.arraycopy(tempStudents, 0, studentsResult, 0, studentsResult.length);
        return studentsResult;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
