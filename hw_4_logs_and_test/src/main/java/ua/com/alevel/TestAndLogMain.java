package ua.com.alevel;

import ua.com.alevel.controller.GroupController;
import ua.com.alevel.controller.StudentController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestAndLogMain {

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.println("Choose what do you want work with:");
                System.out.println("Select 1: Group");
                System.out.println("Select 2: Student");
                System.out.println("Select 0: Exit");
                String choice = reader.readLine();
                switch (choice) {
                    case "1":
                        new GroupController().run();
                    case "2":
                        new StudentController().run();
                    case "0":
                        System.exit(0);
                    default:
                        System.out.println("Choose from the above options!");
                }
            }
            while (true);

        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}
