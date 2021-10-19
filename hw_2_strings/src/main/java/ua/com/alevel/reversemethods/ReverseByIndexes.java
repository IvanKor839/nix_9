package ua.com.alevel.reversemethods;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.StringReverseUtil;
import ua.com.alevel.TaskHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class ReverseByIndexes implements TaskHelper {
    @Override
    public void run(BufferedReader bufferedReader) throws IOException {
        System.out.println("ReverseByIndexes.run");
        System.out.println("Enter text: ");
        String text = bufferedReader.readLine();
        System.out.println("Enter first index: ");
        int firstIndex = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Enter last index: ");
        int lastIndex = Integer.parseInt(bufferedReader.readLine());
        text = StringReverseUtil.reverse(text, firstIndex, lastIndex);
        System.out.println(text);
        ProgramRun.preview();
    }
}
