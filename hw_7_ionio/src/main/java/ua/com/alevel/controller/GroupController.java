package ua.com.alevel.controller;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.TaskHelper;
import ua.com.alevel.dto.group.GroupRequestDto;
import ua.com.alevel.dto.group.GroupResponseDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.facade.impl.GroupFacadeImpl;
import ua.com.alevel.facade.impl.StudentFacadeImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;

public class GroupController implements TaskHelper {

    private final GroupFacade groupFacade = new GroupFacadeImpl();
    private final StudentFacade studentFacade = new StudentFacadeImpl();

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

    public static String notNullGroupById(BufferedReader reader) {
        GroupFacade groupFacade = new GroupFacadeImpl();
        GroupResponseDto resultGroup = null;
        String idGroup = null;
        try {
            do {
                idGroup = reader.readLine();
                resultGroup = groupFacade.findById(idGroup);
                if (resultGroup == null || idGroup.equals(""))
                    System.out.println("Entry existing id!");
            }
            while (resultGroup == null || idGroup.equals(""));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return resultGroup.getId();
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
                findAll();
                break;
        }
        runNavigation();
    }

    public void create(BufferedReader reader) {
        try {
            System.out.println("Please, enter group title");
            String name = notNullInput(reader);
            System.out.println("Please, enter name mentor");
            String nameMentor = notNullInput(reader);
            GroupRequestDto groupRequestDto = new GroupRequestDto(name, nameMentor);
            groupFacade.create(groupRequestDto);
            System.out.println("Group was created successfully");
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = notNullGroupById(reader);
            System.out.println("Please, enter new group title");
            String name = notNullInput(reader);
            System.out.println("Please, enter new mentor name");
            String nameMentor = notNullInput(reader);
            GroupRequestDto groupRequestDto = new GroupRequestDto(name, nameMentor);
            groupFacade.update(groupRequestDto, id);
            System.out.println("Group was updated successfully");
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id group witch do you wand to delete");
            String id = notNullGroupById(reader);
            Collection<StudentResponseDto> collectionStudent = studentFacade.findAllStudentInGroup(id);
            collectionStudent.forEach(student -> {
                if (student != null) {
                    studentFacade.delete(student.getId());
                }
            });
            System.out.println("Group was deleted successfully");
            groupFacade.delete(id);
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = notNullGroupById(reader);
            GroupResponseDto groupResponseDto = groupFacade.findById(id);
            if (groupResponseDto == null) System.out.println("group not found");
            else System.out.println("group = " + groupResponseDto);
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll() {
        try {
            System.out.println("GroupController.findAll");
            Collection<GroupResponseDto> groupResponseDto = groupFacade.findAll();
            if (groupResponseDto.isEmpty()) {
                System.out.println("Group empty");
            }
            groupResponseDto.forEach(group -> {
                if (group != null) {
                    System.out.println("group" + group);
                } else {
                    System.out.println("Group is null");
                }
            });

        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}