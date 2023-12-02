package stockbird.tspproxy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class TSPproxy {

    public static int[] TSPproxy(int[][] grafo) {
        int numVertices = grafo.length;
        boolean[] visitados = new boolean[numVertices];
        int[] caminho = new int[numVertices + 1];
        caminho[0] = 0;  // Começa a partir do vértice 0

        for (int i = 1; i < numVertices; i++) {
            visitados[caminho[i - 1]] = true;
            int verticeAtual = caminho[i - 1];
            int vizinhoMaisProximo = encontrarVizinhoMaisProximo(grafo, verticeAtual, visitados);
            caminho[i] = vizinhoMaisProximo;
        }

        // Volta para o ponto de partida
        caminho[numVertices] = caminho[0];

        return caminho;
    }

    private static int encontrarVizinhoMaisProximo(int[][] grafo, int vertice, boolean[] visitados) {
        int numVertices = grafo.length;
        int vizinhoMaisProximo = -1;
        int menorDistancia = Integer.MAX_VALUE;

        for (int i = 0; i < numVertices; i++) {
            if (!visitados[i] && grafo[vertice][i] < menorDistancia) {
                menorDistancia = grafo[vertice][i];
                vizinhoMaisProximo = i;
            }
        }

        return vizinhoMaisProximo;
    }

    public static int[][] lerMatrizDoArquivo(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
           
            int numRows = (int) br.lines().count();
            br.close();

           
            int[][] matrix;
            try (BufferedReader newBr = new BufferedReader(new FileReader(filePath))) {
                matrix = new int[numRows][];
                String line;
                int row = 0;
                while ((line = newBr.readLine()) != null) {
                    
                    String[] values = line.trim().split("\\s+");
                    
                    
                    matrix[row] = new int[values.length];
                    
                    
                    for (int col = 0; col < values.length; col++) {
                        matrix[row][col] = Integer.parseInt(values[col]);
                    }
                    
                    row++;
                }
            }
            return matrix;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int calcularCustoDoCaminho(int[][] grafo, int[] caminho) {
        int custoTotal = 0;

        for (int i = 0; i < caminho.length - 1; i++) {
            custoTotal += grafo[caminho[i]][caminho[i + 1]];
        }

        return custoTotal;
    }

    public static void main(String[] args) {

        String filepath = "tsp5_27603.txt";
        int[][] grafo = lerMatrizDoArquivo(filepath);

        int[] caminho = TSPproxy(grafo);
        int custoDaSolucao = calcularCustoDoCaminho(grafo, caminho);

        System.out.println("Custo total do Caixeiro Viajante: " + custoDaSolucao);
    }
}
