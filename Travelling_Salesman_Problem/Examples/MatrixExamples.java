/* 
package Examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MatrixExamples {

    public static void main (String[] args) {
        int[][] originGraph;
        try {
            originGraph = readAdjacencyMatrix("./Examples/tsp4_7013.txt");

            for (int i = 2; i <= 44; i++) {
                int[][] newGraph = cutGraph(originGraph, i);
                writeMatrixToFile(newGraph, newGraph.length);
            }

            System.out.println("Random adjacency matrices generated and written to files.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

    }

    public static int[][] cutGraph(int[][] graphOrigin, int size) {

        int[][] adjacencyMatrix = new int[size][size];

        if (graphOrigin.length <= size) {
            return graphOrigin;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adjacencyMatrix[i][j] = graphOrigin[i][j];
                adjacencyMatrix[j][i] = graphOrigin[j][i];
            }
        }

        return adjacencyMatrix;
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

    private static int[][] readAdjacencyMatrix(String filename) throws FileNotFoundException  {
        int [][] adjacencyMatrix;
        int size;
        try {

            File file = new File(filename);

            Scanner scanner = new Scanner(file);

            size = 0;
            while (scanner.hasNextLine()) { // read size of adjacency matrix
                size++;
                scanner.nextLine();
            }

            scanner.close();

            scanner = new Scanner(file); // reset the scanner to the beginning of the file

            adjacencyMatrix = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    adjacencyMatrix[i][j] = scanner.nextInt();
                }
            }

            scanner.close();

            return adjacencyMatrix;

        } catch(Exception e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        return null;
    }
}
*/