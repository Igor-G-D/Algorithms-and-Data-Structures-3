import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// import Examples.TriangleInequalityCheck;
import HeldKarp.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException { // args[0] = what algorithm to use: "heldkarp_m" for heldkarp using memoization matrix, "heldkarp_c" for heldkarp using memoization cache, "MST_TSP" for aproximation using Tree algorithm, args[1] = output folder, args[2] = read from file (true) or from examples (false), args[3] = file to read, or cache size (in GB) if using cache and not reading from file, args[4] = cache size (in GB) if using cache and reading from file
        
        if(!(args.length >= 3 && args.length <= 5)) {
            System.out.println("Invalid number of arguments!");
            return;
        }

        String algorithm = args[0];
        String outputFolder = args[1];
        boolean readFromFile = Boolean.parseBoolean(args[2]);
        String fileToRead = "";
        int cacheSize = 0;
        if(args.length == 4) {
            if(readFromFile) {
                fileToRead = args[3];
            } else if (algorithm.equals("heldkarp_c")) {
                cacheSize = Integer.parseInt(args[3]);
            }
        } else if (args.length == 5) {
            fileToRead = args[3];
            cacheSize = Integer.parseInt(args[4]);
        }

        if(!readFromFile) {
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

                        writeToFile(algorithm, size, result_m, execTime_m, "ms", outputFolder);
                    break;
                    case "heldkarp_c":
                        HeldKarpAlgorithmTSP_Cache heldkarp_c = new HeldKarpAlgorithmTSP_Cache(adjacencyMatrix, size, (int)Math.pow(2,15)*1024*cacheSize); // N GB of memory

                        long startTime_c = System.nanoTime(); // start timer
            
                        int result_c = heldkarp_c.execute();
            
                        long endTime_c = System.nanoTime(); // end timer

                        long execTime_c = TimeUnit.NANOSECONDS.toMillis(endTime_c - startTime_c);

                        writeToFile(algorithm, size, result_c, execTime_c, "ms", outputFolder);
                        
                    break;
                    case "MST_TSP":
                        MSTTSP msttsp = new MSTTSP(adjacencyMatrix, size);

                        long startTime_mst = System.nanoTime(); // start timer
            
                        int result_mst = msttsp.execute();
            
                        long endTime_mst = System.nanoTime(); // end timer

                        long execTime_mst = TimeUnit.NANOSECONDS.toMicros(endTime_mst - startTime_mst);

                        writeToFile(algorithm, size, result_mst, execTime_mst, "μs", outputFolder);
                    break;
                    default:
                        System.out.println("Invalid Algorithm");
                        return;
                }

                System.out.println("Finished size " + size);
            }
        } else if (readFromFile) {
            int[][] adjacencyMatrix = readAdjacencyMatrix(fileToRead);
            //TriangleInequalityCheck.check(adjacencyMatrix);
            int size = adjacencyMatrix.length;
            switch(algorithm) {
                case "heldkarp_m":
                    HeldKarpAlgorithmTSP_Matrix heldkarp_m = new HeldKarpAlgorithmTSP_Matrix(adjacencyMatrix, size);

                    long startTime_m = System.nanoTime(); // start timer
        
                    int result_m = heldkarp_m.execute();
        
                    long endTime_m = System.nanoTime(); // end timer

                    long execTime_m = TimeUnit.NANOSECONDS.toMillis(endTime_m - startTime_m);

                    writeToFile(algorithm, size, result_m, execTime_m, "ms", outputFolder);
                break;
                case "heldkarp_c":
                    HeldKarpAlgorithmTSP_Cache heldkarp_c = new HeldKarpAlgorithmTSP_Cache(adjacencyMatrix, size, (int)Math.pow(2,15)*1024*cacheSize); // N GB of memory

                    long startTime_c = System.nanoTime(); // start timer
        
                    int result_c = heldkarp_c.execute();
        
                    long endTime_c = System.nanoTime(); // end timer

                    long execTime_c = TimeUnit.NANOSECONDS.toMillis(endTime_c - startTime_c);

                    writeToFile(algorithm, size, result_c, execTime_c, "ms", outputFolder);
                    ;
                break;
                case "MST_TSP":
                    MSTTSP msttsp = new MSTTSP(adjacencyMatrix, size);

                    long startTime_mst = System.nanoTime(); // start timer
        
                    int result_mst = msttsp.execute();
        
                    long endTime_mst = System.nanoTime(); // end timer

                    long execTime_mst = TimeUnit.NANOSECONDS.toMicros(endTime_mst - startTime_mst);

                    writeToFile(algorithm, size, result_mst, execTime_mst, "μs", outputFolder);
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

    private static void writeToFile(String algorithm, int matrixSize, int result, long resultTime, String unit, String outputFolder) {
        String fileName = "Results_"+ algorithm +".txt";

        try (FileWriter writer = new FileWriter(outputFolder+"/"+fileName, true)) {
            writer.write("Matrix size "+ matrixSize + " : " + "result = " + result + "  resultTime = " +  resultTime + unit);
            writer.write("\n");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
