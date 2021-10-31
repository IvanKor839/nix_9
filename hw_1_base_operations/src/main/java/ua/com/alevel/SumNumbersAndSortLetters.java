package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SumNumbersAndSortLetters implements TaskHelper {

    @Override
    public void run(BufferedReader bufferedReader) throws IOException {
        System.out.println("Task1.run");
        String text = bufferedReader.readLine();
        char[] chars = text.toCharArray();
        int sum = 0;

        TreeMap<Character, Integer> treeMap = new TreeMap<>();
        int tempCount = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') sum += Integer.parseInt(String.valueOf(chars[i]));
            else if (treeMap.containsKey(chars[i])) {
                tempCount = treeMap.get(chars[i]) + 1;
                treeMap.put(chars[i], tempCount);
            } else {
                treeMap.put(chars[i], 1);
            }
        }
        toString(treeMap);
        System.out.println("Sum= " + sum);
        ProgramRun.preview();
    }

    public void toString(TreeMap<Character, Integer> treeMap) {
        int i = 1;
        for (Map.Entry entry : treeMap.entrySet()) {
            System.out.println(i++ + ". " + entry.getKey() + " - " + entry.getValue());
        }
    }
}

