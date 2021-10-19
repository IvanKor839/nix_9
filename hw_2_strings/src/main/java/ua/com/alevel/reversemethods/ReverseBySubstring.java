package ua.com.alevel.reversemethods;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.StringReverseUtil;
import ua.com.alevel.TaskHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class ReverseBySubstring implements TaskHelper {
    @Override
    public void run(BufferedReader bufferedReader) throws IOException {
        System.out.println("ReverseBySubstring.run");
        System.out.println("Enter text: ");
        String text = bufferedReader.readLine();
        System.out.println("Enter substring: ");
        String desc = bufferedReader.readLine();
        text = StringReverseUtil.reverse(text, desc);
        System.out.println(text);
        ProgramRun.preview();
    }
}
