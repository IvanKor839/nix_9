package ua.com.alevel.controller;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.TaskHelper;
import ua.com.alevel.task.ParseDate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseDateController implements TaskHelper {

    private final String path;

    public ParseDateController(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String> lines = new ArrayList<>();
            while (reader.ready()) {
                lines.add(reader.readLine());
            }
            ParseDate parse = new ParseDate();
            parse.parseDate(lines);
        } catch (IOException e) {
            System.out.println("problem:" + e.getMessage());
        }
        ProgramRun.preview();
    }
}
