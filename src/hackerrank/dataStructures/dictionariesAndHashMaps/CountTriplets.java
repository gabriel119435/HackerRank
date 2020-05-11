package hackerrank.dataStructures.dictionariesAndHashMaps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountTriplets {
    //https://www.hackerrank.com/challenges/count-triplets-1

private static long countTriplets(List<Long> arr, long r) {
    /*
    triplet = a b c (positions)

    for(number : arr){
        count += how many triplets are complete with number on 3rd position? -- what if number's position is c?

        if any triplets are complete with n on 2nd position:
            update v3 with the third value = n*r                             -- what if number's position is b?
            with v3NewValue = v3OldValue + v2Value

        update v2 with the second value = n*r                                -- what if number's position is a?
        with v2NewValue = v2OldValue + 1
    }

    v2/v3 answers the question: if the next number is (key),
    I have (value) triplets where it can fit on the 2nd/3rd position
    */
    Map<Long, Long> v2 = new HashMap<>();
    Map<Long, Long> v3 = new HashMap<>();
    long count = 0L;
    for (long value : arr) {
        count += v3.getOrDefault(value, 0L);

        if (v2.containsKey(value)) {
            v3.compute(
                    value * r,
                    (key, v) -> v2.get(value) + (v != null ? v : 0)
            );
        }

        v2.compute(
                value * r,
                (k, v) -> 1 + (v != null ? v : 0)
        );
    }
    return count;
}

    public static void main(String[] args) {
        List<Long> numbers = Arrays.asList(1L, 1L, 1L, 1L, 1L);
        System.out.println(countTriplets(numbers, 1));

        numbers = Arrays.asList(1L, 3L, 1L, 3L, 9L);
        System.out.println(countTriplets(numbers, 3));
    }
}
