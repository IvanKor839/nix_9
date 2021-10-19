package ua.com.alevel;

import ua.com.alevel.reversemethods.ReverseByIndexes;
import ua.com.alevel.reversemethods.ReverseBySubstring;
import ua.com.alevel.reversemethods.ReverseByWord;
import ua.com.alevel.reversemethods.SimpleReverse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringsMain {
    public static void main(String[] args) throws IOException {
        List<TaskHelper> taskHelpers = new ArrayList<>();
        taskHelpers.add(new SimpleReverse());
        taskHelpers.add(new ReverseByIndexes());
        taskHelpers.add(new ReverseBySubstring());
        taskHelpers.add(new ReverseByWord());
        ProgramRun.run(taskHelpers);
    }
}
