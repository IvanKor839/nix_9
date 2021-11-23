package ua.com.alevel;

import ua.com.alevel.enumerations.Month;
import ua.com.alevel.format.InputFormat;
import ua.com.alevel.format.OutputFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreateDate {

    public static final Map<String, Date> mapOfDate = new HashMap<>();

    public static void run(BufferedReader reader) {
        System.out.println("----------------");
        System.out.println("Create Date");
        System.out.println("Select type of operation:");
        String position;
        try {
            runCreateNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                InputFormat.run(reader);
                OutputFormat.run(reader);
                String id = UUID.randomUUID().toString();
                Date date = createDate(reader);
                if (date == null) {
                    runCreateNavigation();
                    continue;
                }
                mapOfDate.put(id, date);
                System.out.print("id:" + id + " ");
                OutputFormat.dateOutput(date);
                runCreateNavigation();
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private static void runCreateNavigation() {
        System.out.println("to create new Date, please enter 1");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private static Date createDate(BufferedReader reader) {
        String stringInput = "";
        InputFormat.presentInputFormat();
        System.out.println("Enter new date:");
        try {
            stringInput = reader.readLine();
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        if (!InputFormat.isValidInputFormat(stringInput)) {
            System.out.println("Invalid input.");
            return null;
        }
        Date date = stringToDate(stringInput);

        if (InputFormat.isValidDate(date)) return date;
        else {
            System.out.println("Invalid input.");
            return null;
        }
    }

    public static Date stringToDate(String stringInput) {
        Date date = new Date();
        String[] splitString = stringInput.split(" ");
        if (splitString.length < 2) {
            date.setHour(0);
            date.setMinute(0);
            date.setSecond(0);
            date.setMillisecond(0);
            setData(stringInput, date);
        } else {
            String[] splitTime = splitString[1].split(":");
            if (splitTime.length > 0 && !splitTime[0].equals("")) date.setHour(deleteZero(splitTime[0]));
            else date.setHour(0);
            if (splitTime.length > 1 && !splitTime[1].equals("")) date.setMinute(deleteZero(splitTime[1]));
            else date.setMinute(0);
            if (splitTime.length > 2 && !splitTime[2].equals("")) date.setSecond(deleteZero(splitTime[2]));
            else date.setSecond(0);
            if (splitTime.length > 3 && !splitTime[3].equals("")) date.setMillisecond(deleteZero(splitTime[3]));
            else date.setMillisecond(0);
            setData(splitString[0], date);
        }
        return date;
    }

    private static int deleteZero(String stringDate) {
        int index = 0;
        for (int i = 0; i < stringDate.length(); i++) {
            if (stringDate.charAt(i) == '0') {
                index++;
                continue;
            } else {
                stringDate.substring(index);
            }
        }
        return Integer.parseInt(stringDate);
    }

    public static void setData(String stringInput, Date date) {
        String[] stringsDate;
        switch (InputFormat.getInputFormat()) {
            //dd/mm/yyyy
            case 1: {
                stringsDate = stringInput.split("/", -1);
                if (stringsDate.length == 1) {
                    date.setYear(deleteZero(stringsDate[0]));
                    date.setDay(1);
                    date.setMonth(1);
                } else if (stringsDate.length == 2) {
                    if (!stringsDate[0].equals("")) date.setMonth(deleteZero(stringsDate[0]));
                    else date.setMonth(1);
                    if (!stringsDate[1].equals("")) date.setYear(deleteZero(stringsDate[1]));
                    else date.setYear(1);
                    date.setDay(1);
                } else if (stringsDate.length == 3) {
                    if (!stringsDate[0].equals("")) date.setDay(deleteZero(stringsDate[0]));
                    else date.setDay(1);
                    if (!stringsDate[1].equals("")) date.setMonth(deleteZero(stringsDate[1]));
                    else date.setMonth(1);
                    if (!stringsDate[2].equals("")) date.setYear(deleteZero(stringsDate[2]));
                    else date.setYear(1);
                } else {
                    date.setDay(1);
                    date.setMonth(1);
                    date.setYear(1);
                }
                break;
            }
            //m/d/yyyy
            case 2: {
                stringsDate = stringInput.split("/", -1);
                if (stringsDate.length == 1) {
                    date.setYear(deleteZero(stringsDate[0]));
                    date.setDay(1);
                    date.setMonth(1);
                } else if (stringsDate.length == 2) {
                    if (!stringsDate[0].equals("")) date.setDay(deleteZero(stringsDate[0]));
                    else date.setDay(1);
                    if (!stringsDate[1].equals("")) date.setYear(deleteZero(stringsDate[1]));
                    else date.setYear(1);
                    date.setMonth(1);
                } else if (stringsDate.length == 3) {
                    if (!stringsDate[0].equals("")) date.setMonth(deleteZero(stringsDate[0]));
                    else date.setMonth(1);
                    if (!stringsDate[1].equals("")) date.setDay(deleteZero(stringsDate[1]));
                    else date.setDay(1);
                    if (!stringsDate[2].equals("")) date.setYear(deleteZero(stringsDate[2]));
                    else date.setYear(1);
                } else {
                    date.setDay(1);
                    date.setMonth(1);
                    date.setYear(1);
                }
                break;
            }
            //mmm-d-yyyy
            case 3: {
                stringsDate = stringInput.split("-", -1);
                if (stringsDate.length == 1) {
                    date.setYear(deleteZero(stringsDate[0]));
                    date.setDay(1);
                    date.setMonth(1);
                } else if (stringsDate.length == 2) {
                    if (!stringsDate[0].equals("")) date.setDay(deleteZero(stringsDate[0]));
                    else date.setDay(1);
                    if (!stringsDate[1].equals("")) date.setYear(deleteZero(stringsDate[1]));
                    else date.setYear(1);
                    date.setMonth(1);
                } else if (stringsDate.length == 3) {
                    if (!stringsDate[0].equals("")) {
                        if (stringsDate[0].length() > 3) {
                            date.setMonth(Month.valueOf(stringsDate[0]).getMonthOrder());
                        } else {
                            date.setMonth((Month.fromString(stringsDate[0]).getMonthOrder()));
                        }
                    } else date.setMonth(1);
                    if (!stringsDate[1].equals("")) date.setDay(deleteZero(stringsDate[1]));
                    else date.setDay(1);
                    if (!stringsDate[2].equals("")) date.setYear(deleteZero(stringsDate[2]));
                    else date.setYear(1);
                } else {
                    date.setDay(1);
                    date.setMonth(1);
                    date.setYear(1);
                }
                break;
            }
            //dd-mmm-yyyy 00:00
            case 4: {
                stringsDate = stringInput.split("-", -1);
                if (stringsDate.length == 1) {
                    date.setYear(deleteZero(stringsDate[0]));
                    date.setDay(1);
                    date.setMonth(1);
                } else if (stringsDate.length == 2) {
                    if (!stringsDate[0].equals("")) {
                        if (stringsDate[0].length() > 3) {
                            date.setMonth(Month.valueOf(stringsDate[0]).getMonthOrder());
                        } else {
                            date.setMonth((Month.fromString(stringsDate[0]).getMonthOrder()));
                        }
                    } else date.setMonth(1);
                    if (!stringsDate[1].equals("")) date.setYear(deleteZero(stringsDate[1]));
                    else date.setYear(1);
                    date.setDay(1);
                } else if (stringsDate.length == 3) {
                    if (!stringsDate[0].equals("")) date.setDay(deleteZero(stringsDate[0]));
                    else date.setDay(1);
                    if (!stringsDate[1].equals("")) {
                        if (stringsDate[1].length() > 3) {
                            date.setMonth(Month.valueOf(stringsDate[1]).getMonthOrder());
                        } else {
                            date.setMonth((Month.fromString(stringsDate[1]).getMonthOrder()));
                        }
                    } else date.setMonth(1);
                    if (!stringsDate[2].equals("")) date.setYear(deleteZero(stringsDate[2]));
                    else date.setYear(1);
                } else {
                    date.setDay(1);
                    date.setMonth(1);
                    date.setYear(1);
                }
                break;
            }
        }
    }

    public static void showAllDate() {
        for (Map.Entry<String, Date> entry : mapOfDate.entrySet()) {
            System.out.print("id:" + entry.getKey() + " ");
            OutputFormat.dateOutput(entry.getValue());
        }
    }
}