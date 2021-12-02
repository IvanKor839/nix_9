package ua.com.alevel.controller;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.TaskHelper;
import ua.com.alevel.task.UniqueString;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UniqueStringController implements TaskHelper {

    private final String path;

    public UniqueStringController(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String> names = new ArrayList<>();
            while (reader.ready()) {
                names.add(reader.readLine());
            }
            UniqueString uniqueString = new UniqueString();
            System.out.println(uniqueString.firstUniqueNameInList(names));
        } catch (IOException e) {
            System.out.println("problem:" + e.getMessage());
        }
        ProgramRun.preview();
    }
}
