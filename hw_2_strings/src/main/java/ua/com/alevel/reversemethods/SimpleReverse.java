package ua.com.alevel.reversemethods;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.StringReverseUtil;
import ua.com.alevel.TaskHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class SimpleReverse implements TaskHelper {
    @Override
    public void run(BufferedReader bufferedReader) throws IOException {
        System.out.println("SimpleReverse.run");
        System.out.println("Enter text: ");
        String text = bufferedReader.readLine();
        text = StringReverseUtil.reverse(text);
        System.out.println(text);
        ProgramRun.preview();
    }
}
