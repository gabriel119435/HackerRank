package classicProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

// shortest path in a graph

/*
input
5 7
0 1 3
0 2 2
0 3 1
3 4 3
2 4 4
1 2 1
1 4 5
2 3
output
3=[2, 0, 3]
* */

public class Dijkstra {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nn = scan.nextInt(); // node number
        int vn = scan.nextInt(); // vertices number
        HashMap<Integer, HashSet<Integer[]>> graph = new HashMap<>(nn);
        for (int i = 0; i < nn; i++) graph.put(i, new HashSet<>()); // creating all nodes
        for (int i = 0; i < vn; i++) {
            Integer n1 = scan.nextInt();
            Integer n2 = scan.nextInt();
            int w = scan.nextInt(); // weight
            Integer[] v12 = new Integer[]{n2, w}; // each vertice has destiny and weight
            Integer[] v21 = new Integer[]{n1, w};
            graph.get(n1).add(v12);
            graph.get(n2).add(v21);
        }
        Integer r = scan.nextInt(); // root index
        Integer d = scan.nextInt(); // destiny index
        System.out.println();

        PriorityQueue<Integer[]> np = new PriorityQueue<>(Comparator.comparing(n -> n[1])); // nodesToProcess, smaller weights come first
        for (int i = 0; i < nn; i++) {
            if (i == r)
                np.add(new Integer[]{r, 0, r}); // index, weight, previousNode
            else
                np.add(new Integer[]{i, Integer.MAX_VALUE, -1}); // index, weight, previousNode
        }

        Map<Integer, Integer[]> pn = new HashMap<>(nn); // processed nodes
        boolean[] pb = new boolean[nn]; // processed booleans

        while (!np.isEmpty()) {
            Integer[] c = np.poll(); // index, weight and previousNode
            Set<Integer[]> vs = graph.get(c[0]); // get vertices from currNode
            for (Integer[] v : vs) { // destiny and weight
                if (!pb[v[0]]) {
                    int newW = c[1] + v[1];
                    Integer[] na = getByIndex(v[0], np); // node after vertice
                    if (newW < na[1]) {
                        na[1] = newW;
                        na[2] = c[0];
                    }
                    np.add(na);
                }
            }
            pn.put(c[0], c);
            pb[c[0]] = true;
            if (c[0].equals(d)) break;
        }

        List<Integer> p = new ArrayList<>(); // path
        Integer[] c = pn.get(d);
        Integer tw = c[1]; // total weight
        while (!c[0].equals(r)) {
            p.add(c[0]);
            c = pn.get(c[2]);
        }
        p.add(r);

        Collections.reverse(p);
        System.out.println(tw + "=" + p);
    }

    static Integer[] getByIndex(Integer i, PriorityQueue<Integer[]> queue) {
        Integer[] node = queue.stream().filter(n -> n[0].equals(i)).findAny().orElseThrow(
                () -> new NullPointerException("node " + i + " not found on np")
        );
        queue.remove(node);
        return node;
    }
}
