package hackerrank.crackingTheCodeInterview;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {

    //https://www.hackerrank.com/challenges/ctci-ransom-note/problem

    public static void main(String[] args) {
        String[] magazine = {"two", "times", "three", "is", "not", "four"};
        String[] note = {"two", "times", "two", "is", "four"};
        checkMagazine(magazine, note);
    }

    static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> magMap = new HashMap<>();

        for (String word : magazine)
            if (magMap.containsKey(word))
                magMap.put(word, magMap.get(word) + 1);
            else
                magMap.put(word, 1);
        boolean isSubSet = true;

        for (String word : note) {
            if (magMap.containsKey(word)) {
                int quantity = magMap.get(word);
                if (quantity == 1)
                    magMap.remove(word);
                else
                    magMap.put(word, magMap.get(word) - 1);
            } else {
                isSubSet = false;
                break;
            }
        }

        if (isSubSet)
            System.out.println("Yes");
        else
            System.out.println("No");
    }

}
