import java.util.ArrayList;
import java.util.List;

public class MSTTSP {

    private final int INT_MAX = 2147483647;
    private int size;
    private int[][] matrix;

    private List<Integer> nodes;
    private List<Integer> reached;
    private List<Integer> unreached;
    private List<Integer>[] tree;

    private int rootNode;
    private List<Integer> path = new ArrayList<>();
    private int cost = 0;

    MSTTSP (int[][] matrix, int size) {
        this.matrix = matrix;
        this. size = size;
        nodes = new ArrayList<>();
        reached = new ArrayList<>();
        unreached = new ArrayList<>();
        tree = new ArrayList[size];

        rootNode = 0;
        path = new ArrayList<>();
        cost = 0;
    }
    
    public int execute() {
        initialize();
        solve();
        return cost;
    }

    private void initialize() {
        for (int i = 0; i < size; i++) {
            nodes.add(i);
        }
        for (int i = 0; i < size; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < size; i++) {
            unreached.add(i);
        }
    }

    private void prim() {
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

    private void preorder(int index) {
        path.add(index);

        for (int i = 0; i < tree[index].size(); i++) {
            preorder(tree[index].get(i));
        }
    }

    private void solve() {
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

        // System.out.print("Path: ");
        // for(int i=0;i<path.size();i++) {
        //     System.out.print(path.get(i)+",");
        // }
        // System.out.print("\n");

    }
}