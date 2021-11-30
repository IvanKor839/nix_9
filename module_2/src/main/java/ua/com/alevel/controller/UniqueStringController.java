package ua.com.alevel.controller;

import ua.com.alevel.TaskHelper;

public class UniqueStringController implements TaskHelper {

    private final String path;

    public UniqueStringController(String path) {
        this.path = path;
    }

    @Override
    public void run() {}
}
