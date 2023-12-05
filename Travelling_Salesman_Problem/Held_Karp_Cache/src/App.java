import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import HeldKarp.*;

public class App {
    public static void main(String[] args) throws FileNotFoundException { // args[0] = exact or aproximation algorithm (0 or 1), args[1] = file to read the adjacency matrix
        int [][] adjacencyMatrix;
        int size;
        try {

            File file = new File(args[0]);

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

            System.out.println(size);

            
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
                System.out.println();
            }

            HeldKarpAlgorithmTSP exactAlgorithm = new HeldKarpAlgorithmTSP(adjacencyMatrix, size, (int)Math.pow(2,15)*1024*10); // 10GB of memory

            long startTime = System.nanoTime(); // start timer

            System.out.println(exactAlgorithm.execute());

            long endTime = System.nanoTime(); // end timer

            System.out.println("Execution Time: " + TimeUnit.NANOSECONDS.toMillis(endTime - startTime));

            

        } catch(Exception e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

    }
}
