package Examples;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomMatrixGenerator {

    public static void run () {
        for (int i = 1; i <= 45; i++) {
            int[][] adjacencyMatrix = generateRandomAdjacencyMatrix(i);
            writeMatrixToFile(adjacencyMatrix, i);
        }

        System.out.println("Random adjacency matrices generated and written to files.");
    }

    private static int[][] generateRandomAdjacencyMatrix(int size) {
        int[][] matrix = new int[size][size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int randomEdge = random.nextInt(100);
                matrix[i][j] = randomEdge;
                matrix[j][i] = randomEdge; // Undirected, so set the corresponding edge as well
            }
        }

        return matrix;
    }

    private static void writeMatrixToFile(int[][] matrix, int fileNumber) {
        String fileName = "adjacency_matrix_" + fileNumber + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.write(matrix[i][j] + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}