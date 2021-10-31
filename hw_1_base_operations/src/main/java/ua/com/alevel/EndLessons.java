package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;

public class EndLessons implements TaskHelper {

    @Override
    public void run(BufferedReader bufferedReader) throws IOException {
        System.out.println("Task2.run");
        System.out.println("Enter number lessons from 1 to 10");
        String numLess = bufferedReader.readLine();
        int num = Integer.parseInt(numLess);
        num = num * 45 + (num / 2) * 5 + ((num + 1) / 2 - 1) * 15;
        System.out.println(num / 60 + 9 + ":" + num % 60);
        ProgramRun.preview();
    }
}


