package ua.com.alevel;

import ua.com.alevel.controller.GroupController;
import ua.com.alevel.controller.StudentController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IoNioMain {


    public static void main(String[] args) throws IOException {

        List<TaskHelper> taskHelpers = new ArrayList<>();
        taskHelpers.add(new StudentController());
        taskHelpers.add(new GroupController());
        ProgramRun.run(taskHelpers);
    }
}
