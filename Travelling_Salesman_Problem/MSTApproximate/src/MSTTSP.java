// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

import java.util.Arrays;

public class MSTTSP {
    public static int mstTSP(int[][] graph) {
        int V = graph.length;
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        return TSPSolution(parent, graph);
    }

    private static int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < key.length; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private static int TSPSolution(int[] parent, int[][] graph) {
        int V = graph.length;
        int totalCost = 0;

        for (int i = 1; i < V; i++) {
            totalCost += graph[i][parent[i]];
        }

            totalCost += graph[1][parent[1]];


        return totalCost;
    }
}
    // public static void main(String[] args) {
    //     //Uso: java MSTTSP inputs nomedoarquivo.txt
    //     String pastaInputs = args[0];
    //     String nomeArquivo = args[1];
    //     String caminhoArquivo = pastaInputs + System.getProperty("file.separator") + nomeArquivo;

    //     try {
    //         int[][] graph = lerMatrizDoArquivo(caminhoArquivo);
    //         mstTSP(graph);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

//     private static int[][] lerMatrizDoArquivo(String nomeArquivo) throws IOException {
//         List<int[]> linhas = new ArrayList<>();

//         try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
//             String linha;
//             while ((linha = br.readLine()) != null) {
//                 if (!linha.trim().isEmpty()) {  // Ignora linhas vazias
//                     String[] valores = linha.split("\\s+");
//                     int[] linhaMatriz = new int[valores.length];

//                     for (int i = 0; i < valores.length; i++) {
//                         try {
//                             linhaMatriz[i] = Integer.parseInt(valores[i]);
//                         } catch (NumberFormatException e) {
//                             // Trata erros de conversão
//                             System.err.println("Erro ao converter para inteiro: " + valores[i]);
//                             e.printStackTrace();
//                         }
//                     }
//                     linhas.add(linhaMatriz);
//                 }
//             }
//         }

//         int[][] matriz = new int[linhas.size()][];
//         for (int i = 0; i < linhas.size(); i++) {
//             matriz[i] = linhas.get(i);
//         }

//         return matriz;
//     }
// }
