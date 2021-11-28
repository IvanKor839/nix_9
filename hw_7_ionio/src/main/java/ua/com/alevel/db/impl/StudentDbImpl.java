package ua.com.alevel.db.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ua.com.alevel.db.StudentDb;
import ua.com.alevel.entity.Student;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.UUID;

public class StudentDbImpl implements StudentDb {

    private static final String PATH_JSON = "hw_7_ionio/students.json";
    private final File file = new File(PATH_JSON);
    private Collection<Student> students;
    private static StudentDbImpl instance;

    public static StudentDbImpl getInstance() {
        if (instance == null) {
            instance = new StudentDbImpl();
        }
        return instance;
    }

    public StudentDbImpl() {
        try {
            file.createNewFile();
            students = findAll();
            if (students == null) {
                students = new LinkedHashSet<>();
            }
        } catch (IOException e) {
            System.out.println("problem: " + e.getMessage());
        }
    }

    @Override
    public void create(Student entity) {
        entity.setId(generateId());
        students.add(entity);
        try (FileWriter fileWriter = new FileWriter(file)) {
            Gson gson = new Gson();
            gson.toJson(students, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student entity) {
        Student current = findById(entity.getId());
        current.setFirstName(entity.getFirstName());
        current.setLastName(entity.getLastName());
        try (FileWriter fileWriter = new FileWriter(file)) {
            Gson gson = new Gson();
            gson.toJson(students, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        students.remove(findById(id));
        try (FileWriter fileWriter = new FileWriter(file)) {
            Gson gson = new Gson();
            gson.toJson(students, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student findById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        throw new RuntimeException("Student does not exist with id= " + id);
    }

    @Override
    public Collection<Student> findAll() throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            Gson gson = new Gson();
            return gson.fromJson(fileReader, new TypeToken<Collection<Student>>() {
            }.getType());
        }
    }

    @Override
    public Collection<Student> findAllStudentInGroup(String idGroup) {
        Collection<Student> studentsRes = new LinkedHashSet<>();
        Collection<Student> tempStudent = null;
        try (FileReader fileReader = new FileReader(file)) {
            Gson gson = new Gson();
            tempStudent = gson.fromJson(fileReader, new TypeToken<Collection<Student>>() {
            }.getType());
            if (tempStudent.isEmpty()) throw new NullPointerException("In this group no one student");
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempStudent.forEach(student -> {
            if (student.getIdGroup().equals(idGroup)) {
                studentsRes.add(student);
            }
        });
        return studentsRes;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}