package ua.com.alevel.controller;

import ua.com.alevel.TaskHelper;

public class ProfitableWayController implements TaskHelper {

    private final String pathInput;
    private final String pathOutput;

    public ProfitableWayController(String pathInput, String pathOutput) {
        this.pathInput = pathInput;
        this.pathOutput = pathOutput;
    }

    @Override
    public void run()  {}
}
