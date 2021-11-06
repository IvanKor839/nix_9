package ua.com.alevel.controller;

import ua.com.alevel.TestAndLogMain;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GroupController {

    GroupService groupService = new GroupService();
    StudentService studentService = new StudentService();

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

    public static String notNullGroupById(BufferedReader reader) {
        GroupService groupService = new GroupService();
        Group resultGroup = null;
        String idGroup = null;
        try {
            do {
                idGroup = reader.readLine();
                resultGroup = groupService.findById(idGroup);
                if (resultGroup == null || idGroup.equals(""))
                    System.out.println("Entry existing id!");
            }
            while (resultGroup == null || idGroup.equals(""));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return resultGroup.getId();
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
        System.out.println("if you want create group, please enter 1");
        System.out.println("if you want update group, please enter 2");
        System.out.println("if you want delete group, please enter 3");
        System.out.println("if you want findById group, please enter 4");
        System.out.println("if you want findAll group, please enter 5");
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
        }
        runNavigation();
    }

    public Group create(BufferedReader reader) {
        System.out.println("GroupController.create");
        System.out.println("Please, enter group title");
        String name = notNullInput(reader);
        System.out.println("Please, enter name mentor");
        String nameMentor = notNullInput(reader);
        Group group = new Group();
        group.setName(name);
        group.setNameMentor(nameMentor);
        groupService.create(group);
        System.out.println("Group was created successfully");
        return group;
    }

    private void update(BufferedReader reader) {
        System.out.println("GroupController.update");
        System.out.println("Please, enter id");
        String id = notNullGroupById(reader);
        System.out.println("Please, enter new group title");
        String name = notNullInput(reader);
        System.out.println("Please, enter new mentor name");
        String nameMentor = notNullInput(reader);
        Group group = new Group();
        group.setId(id);
        group.setNameMentor(nameMentor);
        group.setName(name);
        groupService.update(group);
        System.out.println("Group was updated successfully");
    }

    private void delete(BufferedReader reader) {
        System.out.println("GroupController.delete");
        System.out.println("Please, enter id group witch do you wand to delete");
        String id = notNullGroupById(reader);
        Student[] students = studentService.findAllStudentInGroup(id);
        System.out.println(students.length);
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                studentService.delete(students[i].getId());
            }

        }
        System.out.println("Group was deleted successfully");
        groupService.delete(id);
    }

    private void findById(BufferedReader reader) {
        System.out.println("GroupController.findById");
        System.out.println("Please, enter id");
        String id = notNullGroupById(reader);
        Group group = groupService.findById(id);
        if (group == null) System.out.println("group not found");
        else System.out.println("group = " + group);

    }

    private void findAll(BufferedReader reader) {
        System.out.println("GroupController.findAll");
        Group[] groups = groupService.findAll();
        if (groups != null && groups.length != 0) {
            for (Group group : groups) {
                if (group != null) {
                    System.out.println("group = " + group);
                }
            }
        } else {
            System.out.println("group empty");
        }
    }
}
