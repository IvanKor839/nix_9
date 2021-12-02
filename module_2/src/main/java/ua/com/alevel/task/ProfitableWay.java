package ua.com.alevel.task;

public class ProfitableWay {

    private final int numVertices;

    public ProfitableWay(int numVertices) {
        this.numVertices = numVertices;
    }

    int minDistance(int[] pathArray, Boolean[] sptSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < numVertices; i++)
            if (sptSet[i] == false && pathArray[i] <= min) {
                min = pathArray[i];
                minIndex = i;
            }
        return minIndex;
    }

    public int algoDijkstra(int[][] graph, int srcNode, int indexOfTown) {
        int[] pathArray = new int[numVertices];
        Boolean[] sptSet = new Boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathArray[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        pathArray[srcNode] = 0;
        for (int count = 0; count < numVertices - 1; count++) {
            int u = minDistance(pathArray, sptSet);
            sptSet[u] = true;
            for (int i = 0; i < numVertices; i++)
                if (!sptSet[i] && graph[u][i] != 0 && pathArray[u] !=
                        Integer.MAX_VALUE && pathArray[u]
                        + graph[u][i] < pathArray[i])
                    pathArray[i] = pathArray[u] + graph[u][i];
        }
        return pathArray[indexOfTown];
    }

    public int findIndex(String[] Names, String name) {
        for (int i = 0; i < Names.length; i++) {
            if (Names[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
