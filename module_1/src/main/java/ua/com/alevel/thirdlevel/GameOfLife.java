package ua.com.alevel.thirdlevel;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.TaskHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class GameOfLife implements TaskHelper {

    private static boolean secondGeneration = true;

    @Override
    public void run(BufferedReader bufferedReader) throws IOException {

        String menu = "";
        int[][] nextGenerationMatrix;
        int[][] lifeMatrix;
        int mSize = 0;
        int nSize = 0;

        do {
            try {
                System.out.println("Enter valid M size: ");
                mSize = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Enter valid N size: ");
                nSize = Integer.parseInt(bufferedReader.readLine());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Try again.");
            }
        } while (mSize <= 0 || nSize <= 0);

        System.out.println("New life game: ");
        lifeMatrix = randLifeGame(mSize, nSize);
        nextGenerationMatrix = new int[mSize][nSize];

        do {
            System.out.println("1. New life game [M x N].");
            System.out.println("2. Next generation.");
            System.out.println("3. Exit to main menu.");
            menu = bufferedReader.readLine();
            switch (menu) {
                case "1": {
                    do {
                        try {
                            System.out.println("Enter valid M size: ");
                            mSize = Integer.parseInt(bufferedReader.readLine());
                            System.out.println("Enter valid N size: ");
                            nSize = Integer.parseInt(bufferedReader.readLine());
                        } catch (NumberFormatException ex) {
                            System.out.println("Invalid input.");
                        }
                    } while (mSize <= 0 || nSize <= 0);
                    System.out.println("New life game: ");
                    lifeMatrix = randLifeGame(mSize, nSize);
                    nextGenerationMatrix = new int[mSize][nSize];
                    menu = "";
                    break;
                }
                case "2": {
                    if (secondGeneration) {
                        nextGenerationMatrix = nextGeneration(lifeMatrix, mSize, nSize);
                        secondGeneration = false;
                    } else {
                        nextGenerationMatrix = nextGeneration(nextGenerationMatrix, mSize, nSize);
                    }
                    System.out.println("Next generation: ");
                    for (int i = 0; i < mSize; i++) {
                        for (int j = 0; j < nSize; j++) {
                            System.out.print(nextGenerationMatrix[i][j] + " ");
                        }
                        System.out.println();
                    }
                    menu = "";
                    break;
                }
            }
        } while (!menu.equals("3"));
        ProgramRun.preview();
    }


    public static int[][] randLifeGame(int mSize, int nSize) {
        Random random = new Random();
        int[][] lifeMatrix = new int[mSize][nSize];
        for (int i = 0; i < mSize; i++) {
            for (int j = 0; j < nSize; j++) {
                lifeMatrix[i][j] = random.nextInt(2);
                System.out.print(lifeMatrix[i][j] + " ");
            }
            System.out.println();
        }
        secondGeneration = true;

        return lifeMatrix;
    }

    public static int[][] nextGeneration(int[][] generationMatrix, int mSize, int nSize) {
        int[][] nextGenerationMatrix = new int[mSize][nSize];

        for (int i = 0; i < mSize; i++) {
            for (int j = 0; j < nSize; j++) {
                if (liveNeighbours(generationMatrix, i, j, mSize, nSize) < 2 && generationMatrix[i][j] == 1)
                    nextGenerationMatrix[i][j] = 0;
                if ((liveNeighbours(generationMatrix, i, j, mSize, nSize) == 2 || liveNeighbours(generationMatrix, i, j, mSize, nSize) == 3) && generationMatrix[i][j] == 1)
                    nextGenerationMatrix[i][j] = 1;
                if (liveNeighbours(generationMatrix, i, j, mSize, nSize) > 3 && generationMatrix[i][j] == 1)
                    nextGenerationMatrix[i][j] = 0;
                if (liveNeighbours(generationMatrix, i, j, mSize, nSize) == 3 && generationMatrix[i][j] == 0)
                    nextGenerationMatrix[i][j] = 1;
            }
        }

        return nextGenerationMatrix;
    }

    public static int liveNeighbours(int[][] board, int i, int j, int m, int n) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }
}
