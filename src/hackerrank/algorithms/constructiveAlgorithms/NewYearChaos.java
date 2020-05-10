package hackerrank.algorithms.constructiveAlgorithms;

import java.util.HashMap;
import java.util.Map;

public class NewYearChaos {

    //https://www.hackerrank.com/challenges/new-year-chaos

    public static void main(String[] args) {
        int[] q = new int[]{2, 1, 5, 3, 4};
        NewYearChaos.minimumBribes(q);
    }

    static void minimumBribes(int[] q) {
        Map<Integer, Integer> bribeCounter = new HashMap<>();
        boolean isTooChaotic = false;
        boolean itChanged = true;

        while (itChanged) {
            itChanged = false;
            for (int i = 0; i < q.length - 1 && !isTooChaotic; i++) {
                if (q[i + 1] < q[i]) {
                    // i bribed i+1
                    if (bribeCounter.containsKey(q[i])) {
                        if (bribeCounter.get(q[i]) == 2)
                            isTooChaotic = true;
                        else
                            bribeCounter.put(q[i], 2);
                    } else
                        bribeCounter.put(q[i], 1);

                    int aux = q[i];
                    q[i] = q[i + 1];
                    q[i + 1] = aux;
                    itChanged = true;

                }
            }
        }
        if (isTooChaotic)
            System.out.println("Too chaotic");
        else {
            int totalBribes = 0;
            for (int i : bribeCounter.keySet()) {
                totalBribes += bribeCounter.get(i);
            }
            System.out.println(totalBribes);
        }

    }
}