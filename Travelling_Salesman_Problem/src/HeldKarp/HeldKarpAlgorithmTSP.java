package HeldKarp;

import java.util.Arrays;

public class HeldKarpAlgorithmTSP {
    int[][] graphMatrix;
    int[][] memoizationTable;
    int infinity;
    int size;

    public HeldKarpAlgorithmTSP(int[][] graphMatrix, int size) {
        this.memoizationTable = new int[size + 1][(int) Math.pow(2, size)];
        this.infinity = Integer.MAX_VALUE;
        this.size = size;
        this.graphMatrix = graphMatrix;
    
        for (int i = 0; i < size + 1; i++) { // Ensure you fill the entire row with -1
            Arrays.fill(memoizationTable[i], -1);
        }
    }

    public int execute() {
        return heldKarp(0,1); // bitmask starts a 1 since by starting at 0, we can assume city 0 is already visited, and the bit representing that is the least significant bit
    }

    int heldKarp(int pos, int bitmask) { // recursive function to calculate TSP

        if (bitmask == (1<<(size))-1) { // if all bits in bitmask = 1, means that all nodes were visited
            return graphMatrix[pos][0]; 
            // all nodes have been visited, it means that the round trip is completed, so the 
            // distance between the starting node and the current node is 0, since they are the same
        }

        if (memoizationTable[pos][bitmask] != -1) { // if not equal to -1, means that the result has already been computed before
            return memoizationTable[pos][bitmask]; // return the already computed value
        }  

        int answer = infinity; // set answer to a really high value to start


        for (int i = 0; i < size; i++) { // iterate through all nodes
            if (i != pos && (bitmask & (1 << i)) == 0) { // skips if the node to visit is itself, or if the bitmask isn't = 0, meaning it was already visited
                answer = Math.min(answer, graphMatrix[pos][i] + heldKarp(i, bitmask | (1 << i)));
                // calculates the distance from the current node to the city in position i, adding the cost of completing the tour with the updated bitmask
                // then, chooses the lowest value between the precious answer and the calculated value
            }
        }

        memoizationTable[pos][bitmask] = answer; // stores the result that was calculated for this position and bitmask so it can be used later
        return answer;
    }
}