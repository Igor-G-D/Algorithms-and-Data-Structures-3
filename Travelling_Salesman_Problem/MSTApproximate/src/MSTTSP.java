import java.util.ArrayList;
import java.util.List;

public class MSTTSP {

    private static final int INT_MAX = 2147483647;
    private static int nbNodes;
    private static int[][] matrix;

    private static List<Integer> nodes = new ArrayList<>();
    private static List<Integer> reached = new ArrayList<>();
    private static List<Integer> unreached = new ArrayList<>();
    private static List<Integer>[] tree;

    private static int rootNode;
    private static List<Integer> path = new ArrayList<>();
    private static int cost = 0;

    public static int execute(int[][] matrix, int size) {
        initialize(matrix, size);
        solve();
        return cost;
    }

    private static void initialize(int[][] matrix, int size) {
        for (int i = 0; i < nbNodes; i++) {
            nodes.add(i);
        }

        tree = new ArrayList[nbNodes];
        for (int i = 0; i < nbNodes; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < nbNodes; i++) {
            unreached.add(i);
        }
    }

    private static void prim() {
        int max;
        int record = 0;
        int parent = 0;
        int newNode = 0;
        int indexNewNode = 0;
        rootNode = unreached.get(0);
        reached.add(unreached.get(0));
        unreached.remove(0);

        while (!unreached.isEmpty()) {
            max = INT_MAX;
            for (int i = 0; i < reached.size(); i++) {
                for (int j = 0; j < unreached.size(); j++) {
                    record = matrix[reached.get(i)][unreached.get(j)];
                    if (record < max) {
                        max = record;
                        indexNewNode = j;
                        parent = reached.get(i);
                        newNode = unreached.get(j);
                    }
                }
            }
            reached.add(unreached.get(indexNewNode));
            unreached.remove(indexNewNode);
            tree[parent].add(newNode);
        }
    }

    private static void preorder(int index) {
        path.add(index);

        for (int i = 0; i < tree[index].size(); i++) {
            preorder(tree[index].get(i));
        }
    }

    private static void solve() {
        int src;
        int dest;
        prim();
        preorder(rootNode);
        path.add(rootNode); // to create the cycle
        for (int i = 0; i < path.size() - 1; i++) {
            src = path.get(i);
            dest = path.get(i + 1);
            cost = cost + matrix[src][dest];
        }
    }
}


// // import java.io.BufferedReader;
// // import java.io.FileReader;
// // import java.io.IOException;
// // import java.util.ArrayList;
// // import java.util.List;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// public class MSTTSP {
//     public static int mstTSP(int[][] graph) {
//         int V = graph.length;
//         int[] parent = new int[V];
//         int[] key = new int[V];
//         boolean[] mstSet = new boolean[V];

//         Arrays.fill(key, Integer.MAX_VALUE);
//         key[0] = 0;
//         parent[0] = -1;

//         for (int count = 0; count < V - 1; count++) {
//             int u = minKey(key, mstSet);
//             mstSet[u] = true;

//             for (int v = 0; v < V; v++) {
//                 if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
//                     parent[v] = u;
//                     key[v] = graph[u][v];
//                 }
//             }
//         }

//         TSPSolution(parent, graph);

//         return TSPSolution(parent, graph);
//     }

//     private static int minKey(int[] key, boolean[] mstSet) {
//         int min = Integer.MAX_VALUE, minIndex = -1;

//         for (int v = 0; v < key.length; v++) {
//             if (!mstSet[v] && key[v] < min) {
//                 min = key[v];
//                 minIndex = v;
//             }
//         }

//         return minIndex;
//     }

//     private static int TSPSolution(int[] parent, int[][] graph) {
//         List<Integer> result = new ArrayList<>();
//         preOrderTraversal(parent, 0, result);
//         result.add(0); //complete the loop
//         int totalCost = 0;
//         System.out.print("Path: ");
//         for(int i=0;i<result.size();i++) {
            
//             System.out.print(result.get(i) + ",");
            
//         }
//         System.out.println(" ");

//         for(int i =0; i < result.size()-1; i++) {
//             totalCost += graph[result.get(i)][result.get(i+1)];
//         }

//         return totalCost;
//     }

//     private static void preOrderTraversal(int[] parent, int node, List<Integer> result) {
//         if (node == -1) {
//             return;
//         }

//         result.add(node);

//         for (int i = 0; i < parent.length; i++) {
//             if (parent[i] == node) {
//                 preOrderTraversal(parent, i, result);
//             }
//         }
//     }
// }