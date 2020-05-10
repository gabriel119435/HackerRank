package hackerrank.crackingTheCodeInterview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFSShortestReachInAGraph {

    //https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int results = scan.nextInt();

        while (results-- > 0) {
            int nodes = scan.nextInt();
            int vertices = scan.nextInt();

            HashMap<Integer, HashSet<Integer>> graph = new HashMap<>(nodes);
            for (int i = 0; i < nodes; i++) graph.put(i, new HashSet<>()); // creating all nodes
            for (int i = 0; i < vertices; i++) {
                int n1 = scan.nextInt() - 1; // since we're using zeroIndexed structures
                int n2 = scan.nextInt() - 1;
                graph.get(n1).add(n2); // bidirectional
                graph.get(n2).add(n1);
            }
            int root = scan.nextInt() - 1; // since we're using zeroIndexed structures
            Queue<Integer> queue = new LinkedList<>(); // nodes to update their connections' weights
            queue.add(root);

            long[] w = new long[nodes]; // weight vector
            Arrays.fill(w, -1);
            w[root] = 0;

            boolean[] marked = new boolean[nodes]; // mark nodes which had their weights calculated
            marked[root] = true;

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int sub : graph.get(curr)) {
                    // if the node is marked, other path came here before and since all vertices
                    // have the same weight, it's the smallest weight possible from root
                    if (!marked[sub]) {
                        // this sub node won't be used in further calculations since itself will be added to next loop
                        marked[sub] = true;
                        queue.add(sub);
                        w[sub] = w[curr] + 1; // its weight is the weight of the previous node plus one
                    }
                }
            }
            for (int i = 0; i < nodes; i++) { // printing loop
                if (i == root) continue;
                System.out.print(w[i] == -1 ? -1 : w[i] * 6 + " ");
            }
        }
    }
}