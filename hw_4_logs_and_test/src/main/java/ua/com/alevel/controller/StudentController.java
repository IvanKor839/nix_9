package ua.com.alevel.controller;

import ua.com.alevel.TestAndLogMain;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentController {
    StudentService studentService = new StudentService();
    GroupService groupService = new GroupService();
    GroupController groupController = new GroupController();

    public static String notNullInput(BufferedReader reader) {
        String result = null;
        try {
            do {
                result = reader.readLine();
                if (result.isEmpty())
                    System.out.println("It must be at least one symbol!!!");
            }
            while (result.isEmpty());
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return result;
    }

    public static String notNullStudentById(BufferedReader reader) {
        StudentService authorService = new StudentService();
        Student resultStudent = null;
        String idStudent;
        try {
            do {
                idStudent = reader.readLine();
                resultStudent = authorService.findById(idStudent);
                if (resultStudent == null || idStudent.equals(""))
                    System.out.println("Entry existing id!");
            }
            while (resultStudent == null || idStudent.equals(""));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return resultStudent.getId();
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                if (position.equals("0")) {
                    TestAndLogMain.mainMenu();
                }
                position = reader.readLine();
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("if you want create student, please enter 1");
        System.out.println("if you want update student, please enter 2");
        System.out.println("if you want delete student, please enter 3");
        System.out.println("if you want findById student, please enter 4");
        System.out.println("if you want findAll student, please enter 5");
        System.out.println("if you want findAllStudentInGroup student, please enter 6");
        System.out.println("if you want exit to main menu, please enter 0");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                create(reader);
                break;
            case "2":
                update(reader);
                break;
            case "3":
                delete(reader);
                break;
            case "4":
                findById(reader);
                break;
            case "5":
                findAll(reader);
                break;
            case "6":
                findAllStudentInGroup(reader);
                break;
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("StudentController.create");
        System.out.println("Please, enter student name");
        String name = notNullInput(reader);
        String idGroup = createOrSelectGroup(reader);
        Student student = new Student();
        student.setName(name);
        student.setIdGroup(idGroup);
        studentService.create(student);
        System.out.println("Student was created successfully");
    }

    private void update(BufferedReader reader) {
        System.out.println("StudentController.update");
        System.out.println("Please, enter id student");
        String idStudent = notNullStudentById(reader);
        System.out.println("Please, enter student name");
        String name = notNullInput(reader);
        System.out.println("Please, enter your age");
        String idGroup = notNullInput(reader);
        Student student = new Student();
        student.setId(idStudent);
        student.setIdGroup(idGroup);
        student.setName(name);
        studentService.update(student);
        System.out.println("Student was updated successfully");
    }

    private void delete(BufferedReader reader) {
        System.out.println("StudentController.delete");
        System.out.println("Please, enter id");
        String id = notNullStudentById(reader);
        studentService.delete(id);
        System.out.println("Student was deleted successfully");

    }

    private void findById(BufferedReader reader) {
        System.out.println("StudentController.findById");
        System.out.println("Please, enter id");
        String id = notNullStudentById(reader);
        Student student = studentService.findById(id);
        if (student == null) System.out.println("student not found");
        else System.out.println("student = " + student);
    }

    private void findAll(BufferedReader reader) {
        System.out.println("StudentController.findAll");
        Student[] students = studentService.findAll();
        if (students != null && students.length != 0) {
            for (Student student : students) {
                if (student != null) {
                    System.out.println("student = " + student);
                }
            }
        } else {
            System.out.println("student empty");
        }
    }

    private void findAllStudentInGroup(BufferedReader reader) {
        System.out.println("StudentController.findAllStudentInGroup");
        System.out.println("Please, enter id group");
        String id = groupController.notNullGroupById(reader);
        Student[] students = studentService.findAllStudentInGroup(id);
        if (students != null && students.length != 0) {
            for (Student student : students) {
                if (student != null) {
                    System.out.println("student = " + student);
                }
            }
        } else {
            System.out.println("student empty");
        }
    }

    private String createOrSelectGroup(BufferedReader reader) {
        System.out.println("How do you want to enter student`s group:");
        System.out.println("if you want select existing group, please enter 1:");
        System.out.println("if you want create group, please enter 2");
        String menu;
        Group group = new Group();
        String id;
        try {
            do {

                menu = reader.readLine();
                switch (menu) {
                    case "1": {
                        System.out.println("Enter id :");
                        do {
                            id = reader.readLine();
                            group = groupService.findById(id);
                            if (group == null || id.equals(""))
                                System.out.println("Entry existing id!");
                        }
                        while (group == null || id.equals(""));
                        break;
                    }
                    case "2":
                        group = groupController.create(reader);
                        break;
                    default:
                        System.out.println("Enter 1 or 2");
                }
            }
            while (!menu.equals("1") && !menu.equals("2"));

        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return group.getId();
    }
}
