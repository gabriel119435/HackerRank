package hackerrank.algorithms.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SherlockAndAnagrams {

    //https://www.hackerrank.com/challenges/sherlock-and-anagrams

    public static void main(String[] args) {
        System.out.println(sherlockAndAnagrams("cdcd"));
    }

    static int sherlockAndAnagrams(String s) {
        int result = 0;

        for (int size = 1; size < s.length(); size++) {
            List<String> subStrings = new ArrayList<>();

            Set<Integer> startingIndexes = new HashSet<>();
            for (int start = 0; start < s.length(); start++) {
                if (start + size <= s.length()) {
                    startingIndexes.add(start);
                } else break;
            }

            for (Integer startingIndex : startingIndexes) {
                subStrings.add(s.substring(startingIndex, startingIndex + size));
            }

            result += countPairs(countDiffAnagrams(subStrings));
        }

        return result;
    }

    // with n diff elements, how many pairs can I get?
    // this methods sums up all the answers for each n on numbers
    static Integer countPairs(Collection<Integer> numbers) {
        int pairs = 0;
        for (Integer number : numbers) {
            if (number >= 2) {
                pairs += number * (number - 1) / 2;
            }
        }
        return pairs;
    }


    static Collection<Integer> countDiffAnagrams(List<String> words) {
        Map<String, Integer> anagramsQuantity = new HashMap<>();
        for (String word : words) {

            Arrays.sort(word.toCharArray());
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sorted = String.valueOf(chars);

            if (anagramsQuantity.containsKey(sorted)) {
                anagramsQuantity.put(sorted, anagramsQuantity.get(sorted) + 1);
            } else {
                anagramsQuantity.put(sorted, 1);
            }
        }
        return anagramsQuantity.values();
    }
}
