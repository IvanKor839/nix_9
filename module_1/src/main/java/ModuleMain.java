import ua.com.alevel.TaskHelper;
import ua.com.alevel.firstlevel.Chess;

import java.util.ArrayList;
import java.util.List;

public class ModuleMain {
    public static void main(String[] args) {
        List<TaskHelper> taskHelpers = new ArrayList<>();
        taskHelpers.add(new Chess());
        taskHelpers.add(new ReverseByIndexes());
        taskHelpers.add(new ReverseBySubstring());
        taskHelpers.add(new ReverseByWord());
        ProgramRun.run(taskHelpers);
    }
}
