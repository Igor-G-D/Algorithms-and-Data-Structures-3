package Examples;

public class TriangleInequalityCheck {

    public static void check(int[][] matrix) {
        int numVertices = matrix.length;

        // Check Triangle Inequality Rule
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                for (int k = 0; k < numVertices; k++) {
                    if (i != j && j != k && i != k) {
                        if (matrix[i][j] + matrix[j][k] < matrix[i][k]) {
                            System.out.println("Triangle Inequality Rule Violated!");
                            return;
                        }
                    }
                }
            }
        }

        System.out.println("The matrix satisfies the Triangle Inequality Rule.");
    }
}