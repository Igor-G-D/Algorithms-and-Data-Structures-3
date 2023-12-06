import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// import Examples.TriangleInequalityCheck;
import HeldKarp.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException { // args[0] = what algorithm to use: "heldkarp_m" for heldkarp using memoization matrix, "heldkarp_c" for heldkarp using memoization cache, "MST_TSP" for aproximation using Tree algorithm, args[1] = read from file (0) or from examples (1), args[2] = file to read(use "null" if using examples), args[3] = cache size in GB if using heldkarp_c
        String algorithm = args[0];

        if(args[1].equals("1")) {
            for(int i = 2; i < 45; i++) {
                int[][] adjacencyMatrix = readAdjacencyMatrix("./Examples/adjacency_matrix_"+i+".txt");
                //TriangleInequalityCheck.check(adjacencyMatrix);
                int size = adjacencyMatrix.length;
                switch(algorithm) {
                    case "heldkarp_m":
                        HeldKarpAlgorithmTSP_Matrix heldkarp_m = new HeldKarpAlgorithmTSP_Matrix(adjacencyMatrix, size);

                        long startTime_m = System.nanoTime(); // start timer
            
                        int result_m = heldkarp_m.execute();
            
                        long endTime_m = System.nanoTime(); // end timer

                        long execTime_m = TimeUnit.NANOSECONDS.toMillis(endTime_m - startTime_m);

                        writeToFile(algorithm, size, result_m, execTime_m, "ms");
                    break;
                    case "heldkarp_c":
                        HeldKarpAlgorithmTSP_Cache heldkarp_c = new HeldKarpAlgorithmTSP_Cache(adjacencyMatrix, size, (int)Math.pow(2,15)*1024*Integer.parseInt(args[3])); // N GB of memory

                        long startTime_c = System.nanoTime(); // start timer
            
                        int result_c = heldkarp_c.execute();
            
                        long endTime_c = System.nanoTime(); // end timer

                        long execTime_c = TimeUnit.NANOSECONDS.toMillis(endTime_c - startTime_c);

                        writeToFile(algorithm, size, result_c, execTime_c, "ms");
                        
                    break;
                    case "MST_TSP":
                        MSTTSP msttsp = new MSTTSP(adjacencyMatrix, size);

                        long startTime_mst = System.nanoTime(); // start timer
            
                        int result_mst = msttsp.execute();
            
                        long endTime_mst = System.nanoTime(); // end timer

                        long execTime_mst = TimeUnit.NANOSECONDS.toMicros(endTime_mst - startTime_mst);

                        writeToFile(algorithm, size, result_mst, execTime_mst, "μs");
                    break;
                    default:
                        System.out.println("Invalid Algorithm");
                        return;
                }

                System.out.println("Finished size " + size);
            }
        } else if (args[1].equals("0")) {
            int[][] adjacencyMatrix = readAdjacencyMatrix(args[2]);
            //TriangleInequalityCheck.check(adjacencyMatrix);
            int size = adjacencyMatrix.length;
            switch(algorithm) {
                case "heldkarp_m":
                    HeldKarpAlgorithmTSP_Matrix heldkarp_m = new HeldKarpAlgorithmTSP_Matrix(adjacencyMatrix, size);

                    long startTime_m = System.nanoTime(); // start timer
        
                    int result_m = heldkarp_m.execute();
        
                    long endTime_m = System.nanoTime(); // end timer

                    long execTime_m = TimeUnit.NANOSECONDS.toMillis(endTime_m - startTime_m);

                    writeToFile(algorithm, size, result_m, execTime_m, "ms");
                break;
                case "heldkarp_c":
                    HeldKarpAlgorithmTSP_Cache heldkarp_c = new HeldKarpAlgorithmTSP_Cache(adjacencyMatrix, size, (int)Math.pow(2,15)*1024*Integer.parseInt(args[3])); // N GB of memory

                    long startTime_c = System.nanoTime(); // start timer
        
                    int result_c = heldkarp_c.execute();
        
                    long endTime_c = System.nanoTime(); // end timer

                    long execTime_c = TimeUnit.NANOSECONDS.toMillis(endTime_c - startTime_c);

                    writeToFile(algorithm, size, result_c, execTime_c, "ms");
                    ;
                break;
                case "MST_TSP":
                    MSTTSP msttsp = new MSTTSP(adjacencyMatrix, size);

                    long startTime_mst = System.nanoTime(); // start timer
        
                    int result_mst = msttsp.execute();
        
                    long endTime_mst = System.nanoTime(); // end timer

                    long execTime_mst = TimeUnit.NANOSECONDS.toMicros(endTime_mst - startTime_mst);

                    writeToFile(algorithm, size, result_mst, execTime_mst, "μs");
                break;
                default:
                    System.out.println("Invalid Algorithm");
                    return;
            }
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

    private static void writeToFile(String algorithm, int matrixSize, int result, long resultTime, String unit) {
        String fileName = "Results_"+ algorithm +".txt";

        try (FileWriter writer = new FileWriter("./Results/"+fileName, true)) {
            writer.write("Matrix size "+ matrixSize + " : " + "result = " + result + "  resultTime = " +  resultTime + unit);
            writer.write("\n");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
