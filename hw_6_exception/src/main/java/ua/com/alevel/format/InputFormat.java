package ua.com.alevel.format;

import ua.com.alevel.Date;

import java.io.BufferedReader;
import java.io.IOException;

public class InputFormat {

    private static int inputFormat = 1;

    public static int getInputFormat() {
        return inputFormat;
    }

    public static void setInputFormat(int inputFormat) {
        InputFormat.inputFormat = inputFormat;
    }

    public static void run(BufferedReader reader) {
        String menu = "";
        do {
            System.out.println("Choose input format:");
            System.out.println("1. dd/mm/yyyy");
            System.out.println("2. m/d/yyyy");
            System.out.println("3. mmm-d-yyyy");
            System.out.println("4. dd-mmm-yyyy 00:00");
            System.out.println("Your choice: ");
            try {
                menu = reader.readLine();
            } catch (IOException e) {
                System.out.println("problem: = " + e.getMessage());
            }
            try {
                if (Integer.parseInt(menu) > 0 && Integer.parseInt(menu) < 5) {
                    setInputFormat(Integer.parseInt(menu));
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input.");
                System.out.println("Setting default input format.");
                break;
            }
        } while (true);
    }

    public static void presentInputFormat() {
        switch (getInputFormat()) {
            case 1: {
                System.out.println("Input: dd/mm/yyyy");
                break;
            }
            case 2: {
                System.out.println("Input: m/d/yyyy");
                break;
            }
            case 3: {
                System.out.println("Input: mmm-d-yyyy");
                break;
            }
            case 4: {
                System.out.println("Input: dd-mmm-yyyy 00:00");
                break;
            }
        }
    }

    public static Boolean isValidDate(Date date) {
        int[] bigMonth = {0, 2, 4, 6, 7, 9, 11};
        if (date == null) return false;
        if (date.getMonth() < 0 || date.getMonth() > 12) {
            System.out.println("Invalid month number.");
            return false;
        }
        if (date.getDay() <= 0 || date.getDay() > 31) {
            System.out.println("Day number out of bounds.");
            return false;
        }
        if (date.getDay() == 30 && date.getMonth() == 2) {
            System.out.println("No 30 in February.");
            return false;
        }
        if (date.getDay() == 29 && date.getMonth() == 2 && date.getYear() % 4 != 0) {
            System.out.println("Not a leap year.");
            return false;
        }
        if (date.getDay() == 29 && date.getMonth() == 2 && date.getYear() % 4 == 0 && date.getYear() % 100 == 0 && date.getYear() % 400 != 0) {
            System.out.println("Not a leap year.");
            return false;
        }
        if (date.getDay() == 29 && date.getMonth() == 2 && date.getYear() % 100 == 0 && date.getYear() % 400 != 0) {
            System.out.println("Not a leap year.");
            return false;
        }
        if (date.getYear() < 0) {
            System.out.println("Before Christ.");
            return false;
        }
        if (date.getHour() < 0 || date.getHour() > 23) {
            System.out.println("Hour number out of bounds.");
            return false;
        }
        if (date.getMinute() < 0 || date.getMinute() > 59) {
            System.out.println("Minute number out of bounds");
            return false;
        }
        if (date.getSecond() < 0 || date.getSecond() > 59) {
            System.out.println("Second number out of bounds.");
            return false;
        }
        if (date.getMillisecond() < 0 || date.getMillisecond() > 999) {
            System.out.println("Millisecond number out of bounds.");
            return false;
        }
        for (int i : bigMonth) {
            if (date.getDay() == 31 && date.getMonth() == i) {
                System.out.println("Not a \"Big\" month.");
                return false;
            }
        }
        return true;
    }

    public static Boolean isValidInputFormat(String stringInput) {
        switch (inputFormat) {
            case 1, 2: {
                if (stringInput.contains(".-")) return false;
                break;
            }
            case 3, 4: {
                if (stringInput.contains("./")) return false;
                break;
            }
        }
        return true;
    }
}