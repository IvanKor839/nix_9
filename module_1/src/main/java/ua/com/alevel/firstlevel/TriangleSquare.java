package ua.com.alevel.firstlevel;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.TaskHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class TriangleSquare implements TaskHelper {
    @Override
    public void run(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter coordinates of point A:");
        int xPointA = Integer.parseInt(bufferedReader.readLine());
        int yPointA = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Enter coordinates of point B:");
        int xPointB = Integer.parseInt(bufferedReader.readLine());
        int yPointB = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Enter coordinates of point C:");
        int xPointC = Integer.parseInt(bufferedReader.readLine());
        int yPointC = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Triangle square = " + (double) (Math.abs((xPointB - xPointA) * (yPointC - yPointA) - (xPointC - xPointA) * (yPointB - yPointA)) / 2));
        ProgramRun.preview();
    }
}
