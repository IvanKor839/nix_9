package ua.com.alevel.service;

import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.GroupService;

import java.util.Arrays;

public class GenerationUtil {

    public static final String NAME_GROUP = "name group";
    public static final String NAME_STUDENT = "name student";
    public static final String NAME_MENTOR = "name mentor";
    private final static GroupService groupService = new GroupService();

    public static Student generateStudent(){
        Student student = new Student();
        student.setName(NAME_STUDENT);
        student.setIdGroup(getRandomIdFromGroupArray());
        return student;
    }
    public static Student generateStudent(String name){
        Student student = new Student();
        student.setName(name);
        student.setIdGroup(generateGroup("test group").getId());
        return student;
    }
    public static Student generateStudent(String name, String idGroup){
        Student student = new Student();
        student.setName(name);
        student.setIdGroup(idGroup);
        return student;
    }
    public static Group generateGroup(String name, String nameMentor){
        Group group = new Group();
        group.setName(name);
        group.setNameMentor(nameMentor);
        return group;
    }
    public static Group generateGroup(String name){
        Group group = new Group();
        group.setName(name);
        group.setNameMentor(NAME_MENTOR);
        return group;
    }
    private static String getRandomIdFromGroupArray() {
        return Arrays.stream(groupService.findAll()).findFirst().get().getId();
    }
}
