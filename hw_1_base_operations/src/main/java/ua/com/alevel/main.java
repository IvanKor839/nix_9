package ua.com.alevel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        List<TaskHelper> taskHelpers = new ArrayList<>();
        taskHelpers.add(new SumNumbersAndSortLetters());
        taskHelpers.add(new EndLessons());
        ProgramRun.run(taskHelpers);

    }
}
