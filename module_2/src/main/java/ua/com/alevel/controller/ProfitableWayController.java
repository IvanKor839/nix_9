package ua.com.alevel.controller;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.TaskHelper;
import ua.com.alevel.task.ProfitableWay;

import java.io.*;

public class ProfitableWayController implements TaskHelper {

    private final String pathInput;
    private final String pathOutput;

    public ProfitableWayController(String pathInput, String pathOutput) {
        this.pathInput = pathInput;
        this.pathOutput = pathOutput;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathInput));
            int countCity = Integer.parseInt(reader.readLine());
            String[] names = new String[countCity];
            int[][] matrixAdjacency = new int[countCity][countCity];
            for (int i = 0; i < countCity; i++) {
                String name = reader.readLine();
                names[i] = name;
                int number = Integer.parseInt(reader.readLine());
                for (int j = 0; j < number; j++) {
                    String[] string = reader.readLine().split(" ");
                    int townNumber = Integer.parseInt(string[0]);
                    int size = Integer.parseInt(string[1]);
                    matrixAdjacency[i][townNumber - 1] = size;
                }
            }

            ProfitableWay profitableWay = new ProfitableWay(countCity);
            int numberOfTests = Integer.parseInt(reader.readLine());
            int[] answer = new int[numberOfTests];
            for (int i = 0; i < numberOfTests; i++) {
                String[] string = reader.readLine().split(" ");
                String nameFirst = string[0];
                String nameSecond = string[1];
                int indexOfTownFirst = profitableWay.findIndex(names, nameFirst);
                int indexOfTownSecond = profitableWay.findIndex(names, nameSecond);
                answer[i] = profitableWay.algoDijkstra(matrixAdjacency, indexOfTownFirst, indexOfTownSecond);

            }
            try (FileWriter writer = new FileWriter(pathOutput)) {
                for (int i = 0; i < numberOfTests; i++) {
                    writer.write(String.valueOf(answer[i]));
                    writer.write(System.lineSeparator());
                }
                System.out.println("Result was written in profitablewayoutput.txt file "+'\n');
            } catch (IOException e) {
                System.out.println("problem:" + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("problem:" + e.getMessage());
        }
        ProgramRun.preview();
    }
}
