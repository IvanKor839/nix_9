package ua.com.alevel.secondlevel;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.TaskHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class CorrectString implements TaskHelper {
    @Override
    public void run(BufferedReader bufferedReader) throws IOException {
        HashMap<String, Integer> openBrackets = new HashMap<>() {{
            put("(", 0);
            put("{", 1);
            put("[", 2);
        }};
        HashMap<String, Integer> closeBrackets = new HashMap<>() {{
            put(")", 0);
            put("}", 1);
            put("]", 2);
        }};
        System.out.println("Enter string with brackets: ");
        String text = bufferedReader.readLine();
        char[] chars = text.toCharArray();
        boolean check = validate(openBrackets, closeBrackets, chars);
        if (check) {
            System.out.println("Brackets were placed CORRENTLY");
        } else System.out.println("Brackets were placed INCORRENTLY");
        ProgramRun.preview();
    }

    public static boolean validate(HashMap<String, Integer> openBrackets,
                                   HashMap<String, Integer> closeBrackets, char[] chars) {
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{' || chars[i] == '(' || chars[i] == '[') stack.push(String.valueOf(chars[i]));
            else if (chars[i] == '}' || chars[i] == ')' || chars[i] == ']') {
                String bracket = stack.pop();
                Integer indexSymbolFromStackInHashMapOpenBracket = openBrackets.get(bracket);
                Integer indexSymbolFromCharArrayInHashMapCloseBracket = closeBrackets.get(String.valueOf(chars[i]));
                if (indexSymbolFromStackInHashMapOpenBracket.equals(indexSymbolFromCharArrayInHashMapCloseBracket))
                    continue;
                else return false;
            }
        }
        return stack.isEmpty();
    }
}
