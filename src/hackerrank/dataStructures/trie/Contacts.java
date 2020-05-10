package hackerrank.dataStructures.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contacts {
    //https://www.hackerrank.com/challenges/contacts

    public static void main(String[] args) {
        String[][] queries = {
                {"add", "hack"},
                {"add", "hackerrank"},
                {"find", "hack"},
                {"find", "hak"}
        };
        System.out.println(Arrays.toString(contacts(queries)));
    }

    static int[] contacts(String[][] queries) {
        List<Integer> results = new ArrayList<>();
        Trie root = new Trie();

        for (String[] query : queries) {
            String operation = query[0];
            String data = query[1];

            if (operation.equals("add")) {
                add(root, data);
            } else {
                results.add(find(root, data));
            }
        }
        int[] arrResults = new int[results.size()];
        for (int i = 0; i < results.size(); i++) {
            arrResults[i] = results.get(i);
        }
        return arrResults;
    }

    private static Integer find(Trie root, String contact) {
        Trie currNode = root;
        for (Character c : contact.toCharArray()) {
            currNode = currNode.children.get(c);
            if (currNode == null) return 0;
        }
        return currNode.counter;
    }

    private static void add(Trie root, String contact) {
        Trie currNode = root;
        for (Character c : contact.toCharArray()) {
            currNode = currNode.getOrCreateChildrenByLetter(c);
            currNode.counter += 1;
        }
    }

    public static class Trie {
        Character letter;
        Map<Character, Trie> children;
        Integer counter;

        public Trie() {
            this.children = new HashMap<>();
        }

        private Trie getOrCreateChildrenByLetter(Character c) {
            if (children.containsKey(c)) {
                return children.get(c);
            } else {
                Trie newChild = new Trie();
                newChild.letter = c;
                newChild.counter = 0;
                children.put(c, newChild);
                return newChild;
            }


        }
    }
}
