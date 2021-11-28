package ua.com.alevel.controller;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.TaskHelper;
import ua.com.alevel.dto.group.GroupResponseDto;
import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.facade.impl.GroupFacadeImpl;
import ua.com.alevel.facade.impl.StudentFacadeImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;

public class StudentController implements TaskHelper {

    StudentFacade studentFacade = new StudentFacadeImpl();
    GroupFacade groupFacade = new GroupFacadeImpl();
    GroupController groupController = new GroupController();

    @Override
    public void run(BufferedReader reader) {
        System.out.println("select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                if (position.equals("0")) {
                    break;
                }
                position = reader.readLine();
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        ProgramRun.preview();
    }

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
        StudentFacade studentFacade = new StudentFacadeImpl();
        StudentResponseDto resultStudent = null;
        String idStudent;
        try {
            do {
                idStudent = reader.readLine();
                resultStudent = studentFacade.findById(idStudent);
                if (resultStudent == null || idStudent.equals(""))
                    System.out.println("Entry existing id!");
            }
            while (resultStudent == null || idStudent.equals(""));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return resultStudent.getId();
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
                findAll();
                break;
            case "6":
                findAllStudentInGroup(reader);
                break;
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        try {
            System.out.println("Please, enter student first name ");
            String firstName = notNullInput(reader);
            System.out.println("Please, enter student last name ");
            String lastName = notNullInput(reader);
            String idGroup = selectGroup(reader);
            StudentRequestDto studentRequestDto = new StudentRequestDto(idGroup, firstName, lastName);
            studentFacade.create(studentRequestDto);
            System.out.println("Student was created successfully");
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("Please, enter id student");
            String idStudent = notNullStudentById(reader);
            System.out.println("Please, enter student first name ");
            String firstName = notNullInput(reader);
            System.out.println("Please, enter student last name ");
            String lastName = notNullInput(reader);
            System.out.println("Please, enter id group");
            String idGroup = notNullInput(reader);
            StudentRequestDto studentRequestDto = new StudentRequestDto(idGroup, firstName, lastName);
            studentFacade.update(studentRequestDto, idStudent);
            System.out.println("Student was updated successfully");
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = notNullStudentById(reader);
            studentFacade.delete(id);
            System.out.println("Student was deleted successfully");
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = notNullStudentById(reader);
            StudentResponseDto studentResponseDto = studentFacade.findById(id);
            if (studentResponseDto == null) System.out.println("student not found");
            else System.out.println("student = " + studentResponseDto);
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll() {
        try {
            Collection<StudentResponseDto> studentResponseDtos = studentFacade.findAll();
            if (studentResponseDtos.isEmpty()) {
                System.out.println("Students empty");
            }
            studentResponseDtos.forEach(student -> {
                if (student != null) {
                    System.out.println("student" + student);
                } else {
                    System.out.println("Student is null");
                }
            });
        } catch (RuntimeException | IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAllStudentInGroup(BufferedReader reader) {
        try {
            System.out.println("Please, enter id group");
            String id = groupController.notNullGroupById(reader);
            Collection<StudentResponseDto> studentResponseDtos = studentFacade.findAllStudentInGroup(id);
            if (studentResponseDtos.isEmpty()) {
                System.out.println("Students empty");
            }
            studentResponseDtos.forEach(student -> {
                if (student != null) {
                    System.out.println("student " + student);
                } else {
                    System.out.println("Student is null");
                }
            });
        } catch (RuntimeException | IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private String selectGroup(BufferedReader reader) {
        System.out.println("Entry existing id group:");
        String id;
        GroupResponseDto groupResponseDto = null;
        try {
            System.out.println("Enter id :");
            do {
                id = reader.readLine();
                groupResponseDto = groupFacade.findById(id);
                if (groupResponseDto == null || id.equals(""))
                    System.out.println("Entry existing id!");
            }
            while (groupResponseDto == null || id.equals(""));

        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return groupResponseDto.getId();
    }
}