package ua.com.alevel;

import ua.com.alevel.enumerations.DateInMillisecond;

import java.io.BufferedReader;
import java.io.IOException;

public class FindDifferenceInDate {

    public static void run(BufferedReader reader) {
        Date firstDate;
        Date secondDate;
        Date difference;
        try {
            System.out.println("First Date");
            firstDate = inputDateById(reader);
            System.out.println("Second Date");
            secondDate = inputDateById(reader);
            difference = millisecondsToDate(Math.abs(dateToMilliseconds(firstDate) - dateToMilliseconds(secondDate)));
            if (dateToMilliseconds(difference) == 0) {
                System.out.println("Dates are equals");
            } else {
                System.out.println("Difference: ");
                System.out.println(difference.getYear() + " years, " +
                        difference.getMonth() + " months, "
                        + difference.getDay() + " days, "
                        + difference.getHour() + " hours, "
                        + difference.getMinute() + " minutes, "
                        + difference.getSecond() + " seconds, "
                        + difference.getMillisecond() + " milliseconds.");
            }
        } catch (RuntimeException e) {
            return;
        }
    }

    public static Date inputDateById(BufferedReader reader) {
        System.out.println("-----------");
        System.out.println("if you want exit, please enter 0");
        System.out.println("enter id of Date:");
        String inputRow;
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("0")) {
                    System.out.println("--------------------");
                    break;
                }
                Date baseDate = CreateDate.mapOfDate.get(inputRow);
                if (baseDate == null) {
                    System.out.println("input id isn't exist, try again please");
                } else {
                    return baseDate;
                }
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        throw new RuntimeException("Date is not found");
    }

    public static Long dateToMilliseconds(Date date) {
        return (DateInMillisecond.YEAR.getValueInMillisecondOrder() * date.getYear() +
                DateInMillisecond.MONTH.getValueInMillisecondOrder() * date.getMonth() +
                DateInMillisecond.DAY.getValueInMillisecondOrder() * date.getDay() +
                DateInMillisecond.HOUR.getValueInMillisecondOrder() * date.getHour() +
                DateInMillisecond.MINUTE.getValueInMillisecondOrder() * date.getMinute() +
                DateInMillisecond.SECOND.getValueInMillisecondOrder() * date.getSecond() +
                DateInMillisecond.MILLISECOND.getValueInMillisecondOrder() * date.getMillisecond());
    }

    public static Date millisecondsToDate(Long milli) {
        Date date = new Date();
        date.setYear(Integer.parseInt(String.valueOf(milli / DateInMillisecond.YEAR.getValueInMillisecondOrder())));
        milli -= milli / DateInMillisecond.YEAR.getValueInMillisecondOrder() * DateInMillisecond.YEAR.getValueInMillisecondOrder();
        date.setMonth(Integer.parseInt(String.valueOf(milli / DateInMillisecond.MONTH.getValueInMillisecondOrder())));
        milli -= milli / DateInMillisecond.MONTH.getValueInMillisecondOrder() * DateInMillisecond.MONTH.getValueInMillisecondOrder();
        date.setDay(Integer.parseInt(String.valueOf(milli / DateInMillisecond.DAY.getValueInMillisecondOrder())));
        milli -= milli / DateInMillisecond.DAY.getValueInMillisecondOrder() * DateInMillisecond.DAY.getValueInMillisecondOrder();
        date.setHour(Integer.parseInt(String.valueOf(milli / DateInMillisecond.HOUR.getValueInMillisecondOrder())));
        milli -= milli / DateInMillisecond.HOUR.getValueInMillisecondOrder() * DateInMillisecond.HOUR.getValueInMillisecondOrder();
        date.setMinute(Integer.parseInt(String.valueOf(milli / DateInMillisecond.MINUTE.getValueInMillisecondOrder())));
        milli -= milli / DateInMillisecond.MINUTE.getValueInMillisecondOrder() * DateInMillisecond.MINUTE.getValueInMillisecondOrder();
        date.setSecond(Integer.parseInt(String.valueOf(milli / DateInMillisecond.SECOND.getValueInMillisecondOrder())));
        milli -= milli / DateInMillisecond.SECOND.getValueInMillisecondOrder() * DateInMillisecond.SECOND.getValueInMillisecondOrder();
        date.setMillisecond(milli.intValue());
        return date;
    }
}