package hackerrank.dataStructures.dictionariesAndHashMaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FrequencyQueries {

    //https://www.hackerrank.com/challenges/frequency-queries

    public static List<Integer> freqQuery(List<int[]> queries) {
        Map<Integer, Integer> v = new HashMap<>(); // values with quantity
        Map<Integer, Integer> h = new HashMap<>(); // histogram of v

        List<Integer> results = new ArrayList<>();
        for (int[] query : queries) {
            int instruction = query[0];
            int value = query[1];
            if (instruction == 1) {
                Integer old = v.get(value);
                addOneCreatingIfNull(v, value);
                int curr = v.get(value);
                subtractOneRemovingIfZeroWithNonNullKey(h, old);
                addOneCreatingIfNull(h, curr);
            } else if (instruction == 2) {
                if (v.get(value) != null) {
                    if (v.get(value) == 1) {
                        subtractOneRemovingIfZeroWithNonNullKey(v, value);
                        subtractOneRemovingIfZeroWithNonNullKey(h, 1);
                    } else {
                        Integer old = v.get(value);
                        subtractOneRemovingIfZeroWithNonNullKey(v, value);
                        int curr = v.get(value);
                        subtractOneRemovingIfZeroWithNonNullKey(h, old);
                        addOneCreatingIfNull(h, curr);
                    }
                }
            } else {
                results.add(h.get(value) != null ? 1 : 0);
            }
        }
        return results;
    }

    private static void subtractOneRemovingIfZeroWithNonNullKey(Map<Integer, Integer> map, Integer key) {
        if (key != null) {
            if (map.get(key) == 1) {
                map.remove(key);
            }
            if (map.get(key) != null) {
                map.put(key, map.get(key) - 1);
            }
        }
    }

    private static void addOneCreatingIfNull(Map<Integer, Integer> map, Integer key) {
        map.merge(key, 1, Integer::sum);
    }


    public static void main(String[] args) {
        /*
        input:
        8
        1 5
        1 6
        3 2
        1 10
        1 10
        1 6
        2 5
        3 2

        output:
        0
        1
        */

        List<int[]> input = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        // compare and subtract
        while (t-- > 0) {
            int[] p = new int[2];
            p[0] = Integer.parseInt(scan.next());
            p[1] = Integer.parseInt(scan.next());
            input.add(p);
        }
        freqQuery(input).forEach(System.out::println);
        scan.close();
    }
}