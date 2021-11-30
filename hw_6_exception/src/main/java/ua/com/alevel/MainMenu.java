package ua.com.alevel;

import ua.com.alevel.format.InputFormat;
import ua.com.alevel.format.OutputFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenu {

    public static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("----------------");
        System.out.println("All Date will be saved and you can use them for testing methods");
        System.out.println("Select type of operation:");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                mainOperations(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private static void runNavigation() {
        System.out.println("----------------");
        System.out.println("If you want to create Date, please enter 1");
        System.out.println("If you to get all Date which was created , please enter 2");
        System.out.println("If you want to find different in Dates , please enter 3");
        System.out.println("If you want to Increase or Decrease Date , please enter 4");
        System.out.println("If you want to Sort Date , please enter 5");
        System.out.println("If you want to change Input format , please enter 6");
        System.out.println("If you want to change Output format , please enter 7");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private static void mainOperations(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                CreateDate.run(reader);
                break;
            case "2":
                CreateDate.showAllDate();
                break;
            case "3":
                FindDifferenceInDate.run(reader);
                break;
            case "4":
                IncreaseDecreaseDate.run(reader);
                break;
            case "5":
                SortDate.run(reader);
                break;
            case "6":
                InputFormat.run(reader);
                break;
            case "7":
                OutputFormat.run(reader);
                break;
        }
        runNavigation();
    }
}