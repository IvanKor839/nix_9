package ua.com.alevel;

public class StringReverseUtil {
    public static String reverse(String str) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = str.length() - 1; i >= 0; i--) {
            stringBuilder.append(str.charAt(i));
        }

        return stringBuilder.toString();
    }

    public static String reverse(String str, boolean word) {
        String[] strMas = str.split(" ");
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < strMas.length; i++) {
            for (int j = strMas[i].length() - 1; j >= 0; j--) {
                stringBuilder.append(strMas[i].charAt(j));
            }
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public static String reverse(String str, String dest) {
        StringBuilder stringBuilder = new StringBuilder("");
        int index = str.indexOf(dest);
        if (index == -1) return "Error! The substring is not in string";
        stringBuilder.append(str.substring(0, index)).append(reverse(dest)).append(str.substring(index + dest.length()));
        return stringBuilder.toString();
    }

    public static String reverse(String str, int firstIndex, int lastIndex) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (firstIndex < 0 || firstIndex > str.length() || lastIndex < 0 || lastIndex > str.length())
            return "Error! First and Last index must be in string length diapason";
        if (firstIndex > lastIndex) return "Error! First index must be less than Last index";
        stringBuilder.append(str.substring(0, firstIndex)).append(reverse(str.substring(firstIndex, lastIndex))).append(str.substring(lastIndex));

        return stringBuilder.toString();
    }
}
