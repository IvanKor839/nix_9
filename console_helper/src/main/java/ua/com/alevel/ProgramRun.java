package ua.com.alevel;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.io.BufferedReader;

public class ProgramRun {


    public static void run(List<TaskHelper> taskHelpers) throws IOException {
        preview();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String event;
        try {
            while ((event = bufferedReader.readLine()) != null) {

                for (int i = 0; i < taskHelpers.size(); i++) {
                    if (Integer.parseInt(event) > taskHelpers.size()) System.exit(0);
                    if (Integer.parseInt(event) == i) {
                        taskHelpers.get(i).run(bufferedReader);
                    }

                }

            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error! You must select only number");
            e.printStackTrace();

        }

    }

    public static void preview() {
        System.out.println("if you need to reverse all string, please select 0");
        System.out.println("if you need to reverse by indexes, please select 1");
        System.out.println("if you need to reverse by substring, please select 2");
        System.out.println("if you need to reverse by word, please select 3");
        System.out.println("select any other number for exit");
        System.out.println();

    }


}
