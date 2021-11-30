package ua.com.alevel.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ParseDate {

    public  void parseDate(List<String> inputDates){
        inputDates.forEach(date->{
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
            List<DateTimeFormatter> formatters = new ArrayList<>();
            formatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            formatters.add(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            formatters.forEach(formatter->{
                if(isValidFormat(formatter, date)){
                    LocalDate localDateTime =  LocalDate.parse(date, formatter);
                    System.out.println(outputFormat.format(localDateTime));
                }
            });
        });

    }
    public static boolean isValidFormat(DateTimeFormatter format, String inputString) {
       try {
           LocalDate.parse(inputString , format);
       }catch (DateTimeParseException e){
           return false;
       }
       return true;
    }
}
