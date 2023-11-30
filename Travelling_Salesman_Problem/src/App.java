import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException { // args[0] = exact or aproximation algorithm (0 or 1), args[1] = file to read the adjacency matrix
        int [][] adjacencyMatrix;
        int size;
        try {

            File file = new File(args[0]);

            // String[] files = file.list(); 
            // System.out.println("Files are:"); 
            // // Display the names of the files 
            // for (int i = 0; i < files.length; i++) { 
            //     System.out.println(files[i]); 
            // } 

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

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
                System.out.println();
            }

        } catch(Exception e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}
