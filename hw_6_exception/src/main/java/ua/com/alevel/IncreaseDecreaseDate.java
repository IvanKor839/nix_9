package ua.com.alevel;

import ua.com.alevel.enumerations.DateInMillisecond;
import ua.com.alevel.format.OutputFormat;

import java.io.BufferedReader;
import java.io.IOException;

public class IncreaseDecreaseDate {

    public static void run(BufferedReader reader) {
        String menu = "";
        String stringValue = "";
        int value = 0;
        Date date = FindDifferenceInDate.inputDateById(reader);
        do {
            OutputFormat.dateOutput(date);
            System.out.println("If you want to decrease Date enter NEGATIVE number");
            System.out.println("Choose what to increase.");
            System.out.println("1. Year.");
            System.out.println("2. Month.");
            System.out.println("3. Day.");
            System.out.println("4. Hour.");
            System.out.println("5. Minute.");
            System.out.println("6. Second.");
            System.out.println("7. Millisecond.");
            System.out.println("8. Back.");
            System.out.println("Your choice: ");
            try {
                menu = reader.readLine();
            } catch (IOException e) {
                System.out.println("Error.");
            }
            System.out.println("Enter value: ");
            try {
                stringValue = reader.readLine();
                if (stringValue != null) value = Integer.parseInt(stringValue);
                else {
                    System.out.println("Invalid input.");
                    return;
                }
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Invalid input.");
            }

            switch (menu) {
                case "1": {
                    if (FindDifferenceInDate.dateToMilliseconds(date) + value * DateInMillisecond.YEAR.getValueInMillisecondOrder() > 0) {
                        OutputFormat.dateOutput(FindDifferenceInDate.millisecondsToDate(FindDifferenceInDate.dateToMilliseconds(date)
                                + value * DateInMillisecond.YEAR.getValueInMillisecondOrder()));
                    } else {
                        System.out.println("Jesus was not born yet");
                    }
                    break;
                }

                case "2": {
                    if (FindDifferenceInDate.dateToMilliseconds(date) + value * DateInMillisecond.MONTH.getValueInMillisecondOrder() > 0) {
                        OutputFormat.dateOutput(FindDifferenceInDate.millisecondsToDate(FindDifferenceInDate.dateToMilliseconds(date)
                                + value * DateInMillisecond.MONTH.getValueInMillisecondOrder()));
                    } else {
                        System.out.println("Jesus was not born yet");
                    }
                    break;
                }

                case "3": {
                    if (FindDifferenceInDate.dateToMilliseconds(date) + value * DateInMillisecond.DAY.getValueInMillisecondOrder() > 0) {
                        OutputFormat.dateOutput(FindDifferenceInDate.millisecondsToDate(FindDifferenceInDate.dateToMilliseconds(date)
                                + value * DateInMillisecond.DAY.getValueInMillisecondOrder()));
                    } else {
                        System.out.println("Jesus was not born yet");
                    }
                    break;
                }

                case "4": {
                    if (FindDifferenceInDate.dateToMilliseconds(date) + value * DateInMillisecond.HOUR.getValueInMillisecondOrder() > 0) {
                        OutputFormat.dateOutput(FindDifferenceInDate.millisecondsToDate(FindDifferenceInDate.dateToMilliseconds(date)
                                + value * DateInMillisecond.HOUR.getValueInMillisecondOrder()));
                    } else {
                        System.out.println("Jesus was not born yet");
                    }
                    break;
                }

                case "5": {
                    if (FindDifferenceInDate.dateToMilliseconds(date) + value * DateInMillisecond.MINUTE.getValueInMillisecondOrder() > 0) {
                        OutputFormat.dateOutput(FindDifferenceInDate.millisecondsToDate(FindDifferenceInDate.dateToMilliseconds(date)
                                + value * DateInMillisecond.MINUTE.getValueInMillisecondOrder()));
                    } else {
                        System.out.println("Jesus was not born yet");
                    }
                    break;
                }

                case "6": {
                    if (FindDifferenceInDate.dateToMilliseconds(date) + value * DateInMillisecond.SECOND.getValueInMillisecondOrder() > 0) {
                        OutputFormat.dateOutput(FindDifferenceInDate.millisecondsToDate(FindDifferenceInDate.dateToMilliseconds(date)
                                + value * DateInMillisecond.SECOND.getValueInMillisecondOrder()));
                    } else {
                        System.out.println("Jesus was not born yet");
                    }
                    break;
                }

                case "7": {
                    if (FindDifferenceInDate.dateToMilliseconds(date) + value * DateInMillisecond.MILLISECOND.getValueInMillisecondOrder() > 0) {
                        OutputFormat.dateOutput(FindDifferenceInDate.millisecondsToDate(FindDifferenceInDate.dateToMilliseconds(date) + value));
                    } else {
                        System.out.println("Jesus was not born yet");
                    }
                    break;
                }

                case "8": {
                    return;
                }
            }
        } while (true);
    }
}