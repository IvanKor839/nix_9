package ua.com.alevel;

import ua.com.alevel.controller.ParseDateController;
import ua.com.alevel.controller.ProfitableWayController;
import ua.com.alevel.controller.UniqueStringController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SecondModuleMain {

    private static final String CATALOG = "./src/main/resources/inputdata/";

    public static void main(String[] args) throws IOException {

        List<TaskHelper> list = new ArrayList<>();
        list.add(new ParseDateController(CATALOG + "date.txt"));
        list.add(new UniqueStringController(CATALOG + "uniquestring.txt"));
        list.add(new ProfitableWayController(CATALOG + "profitablewayinput.txt", CATALOG + "profitablewayoutput.txt"));
        ProgramRun.run(list);
    }
}
