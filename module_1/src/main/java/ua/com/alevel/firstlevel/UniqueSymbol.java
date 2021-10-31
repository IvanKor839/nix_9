package ua.com.alevel.firstlevel;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.TaskHelper;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UniqueSymbol implements TaskHelper {
    @Override
    public void run(BufferedReader bufferedReader) throws IOException {
        int arrSize = 0;
        int iterCount = 0;
        System.out.println("Enter array of number:");
        String text = bufferedReader.readLine();
        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) arrSize++;
        }
        Integer[] numArray = new Integer[arrSize];
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                numArray[iterCount] = Integer.parseInt(String.valueOf(chars[i]));
                iterCount++;
            }
        }
        Set<Integer> set = new HashSet<Integer>();
        set.addAll(Arrays.asList(numArray));
        System.out.println(set.size());
        ProgramRun.preview();
    }
}
