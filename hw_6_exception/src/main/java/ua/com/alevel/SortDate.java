package ua.com.alevel;

import ua.com.alevel.format.InputFormat;
import ua.com.alevel.format.OutputFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class SortDate {

    public static void run(BufferedReader reader) {
        String menu = "";
        do {
            InputFormat.presentInputFormat();
            OutputFormat.presentOutputFormat();
            System.out.println("1. Sort Ascending exist Date.");
            System.out.println("2. Sort Descending exist Date.");
            System.out.println("3. Back.");

            try {
                menu = reader.readLine();
            } catch (IOException e) {
                System.out.println("Error.");
            }
            switch (menu) {
                case "1": {
                    if (CreateDate.mapOfDate.isEmpty()) {
                        System.out.println("No dates to sort.");
                        break;
                    }
                    Map<String, Date> result = new LinkedHashMap<>();
                    Stream<Map.Entry<String, Date>> stream = CreateDate.mapOfDate.entrySet().stream();
                    stream.sorted(Comparator.comparing(e -> FindDifferenceInDate.dateToMilliseconds(e.getValue())))
                            .forEach(e -> result.put(e.getKey(), e.getValue()));
                    for (Map.Entry<String, Date> entry : result.entrySet()) {
                        System.out.print("id:" + entry.getKey() + " ");
                        OutputFormat.dateOutput(entry.getValue());
                    }
                    break;
                }
                case "2": {
                    if (CreateDate.mapOfDate.isEmpty()) {
                        System.out.println("No dates to sort.");
                        break;
                    }
                    Map<String, Date> result = new LinkedHashMap<>();
                    Stream<Map.Entry<String, Date>> stream = CreateDate.mapOfDate.entrySet().stream();
                    stream.sorted(((o1, o2) -> Long.compare(FindDifferenceInDate.dateToMilliseconds(o2.getValue()), FindDifferenceInDate.dateToMilliseconds(o1.getValue()))
                    )).forEach(o2 -> result.put(o2.getKey(), o2.getValue()));
                    for (Map.Entry<String, Date> entry : result.entrySet()) {
                        System.out.print("id:" + entry.getKey() + " ");
                        OutputFormat.dateOutput(entry.getValue());
                    }
                    break;
                }
                case "3": {
                    MainMenu.run();
                    break;
                }
            }
        } while (true);
    }
}