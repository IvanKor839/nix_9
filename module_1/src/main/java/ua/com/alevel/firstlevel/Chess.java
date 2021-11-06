package ua.com.alevel.firstlevel;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.TaskHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class Chess implements TaskHelper {
    final int SIZE = 8;
    int x1, y1, x2, y2;

    @Override
    public void run(BufferedReader bufferedReader) throws IOException {

        boolean[][] chessField = new boolean[SIZE][SIZE];

        while (true) {
            System.out.println("Enter start position of figure:");
            x1 = Integer.parseInt(bufferedReader.readLine());
            y1 = Integer.parseInt(bufferedReader.readLine());
            if (x1 < 1 || y1 < 1 || x1 > 8 || y1 > 8) System.out.println("Wrong starting point...");
            else break;
        }
        System.out.println("Chess board with start position figure:");
        printBoard(x1, y1);

        while (true) {
            System.out.println("Enter finish position of figure:");
            x2 = Integer.parseInt(bufferedReader.readLine());
            y2 = Integer.parseInt(bufferedReader.readLine());
            if (x2 < 1 || y2 < 1 || x2 > 8 || y2 > 8) System.out.println("Wrong final point...");
            else break;
        }

        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
        if (dx == 1 && dy == 2 || dx == 2 && dy == 1) {
            System.out.println("YES");
            System.out.println("Chess board with start position figure:");
            printBoard(x2, y2);
        } else System.out.println("NO");


        ProgramRun.preview();
    }

    void printBoard(int x, int y) {
        for (int i = 1; i <= SIZE; i++) {
            System.out.println(" ");
            for (int j = 1; j <= SIZE; j++) {
                if (i == SIZE - y + 1 && j == x) System.out.print("1 ");
                else System.out.print("0 ");
            }
        }
        System.out.println();
    }
}
