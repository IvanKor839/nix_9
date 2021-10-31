package ua.com.alevel;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.TaskHelper;
import ua.com.alevel.firstlevel.Chess;
import ua.com.alevel.firstlevel.TriangleSquare;
import ua.com.alevel.firstlevel.UniqueSymbol;
import ua.com.alevel.secondlevel.BinaryTree;
import ua.com.alevel.secondlevel.CorrectString;
import ua.com.alevel.thirdlevel.GameOfLife;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModuleMain {
    public static void main(String[] args) throws IOException {
        List<TaskHelper> taskHelpers = new ArrayList<>();
        taskHelpers.add(new UniqueSymbol());
        taskHelpers.add(new Chess());
        taskHelpers.add(new TriangleSquare());
        taskHelpers.add(new CorrectString());
        taskHelpers.add(new BinaryTree());
        taskHelpers.add(new GameOfLife());
        ProgramRun.run(taskHelpers);
    }
}
