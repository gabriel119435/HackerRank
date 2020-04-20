package javaCode.tries;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Contacts {

    //https://www.hackerrank.com/challenges/contacts

    static String add = "add";

    static class TrieNode {
        private final Map<Character, TrieNode> children = new HashMap<>();
        int timesThisLetterAppeared = 0;

        void putChildIfAbsent(Character c) {
            children.putIfAbsent(c, new TrieNode());
        }

        TrieNode getChild(Character c) {
            return children.get(c);
        }
    }

    static class Trie {
        final TrieNode rootNode = new TrieNode();

        void add(String contact) {
            TrieNode currentNode = rootNode;
            for (Character c : contact.toCharArray()) {
                currentNode.putChildIfAbsent(c);
                currentNode = currentNode.getChild(c);
                currentNode.timesThisLetterAppeared++;
            }
        }

        int find(String prefix) {
            TrieNode currentNode = rootNode;
            for (Character c : prefix.toCharArray()) {
                currentNode = currentNode.getChild(c);
                if (currentNode == null) {
                    return 0;
                }
            }
            return currentNode.timesThisLetterAppeared;
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        String operation, word;
        Trie trie = new Trie();

        n = in.nextInt();
        while (n-- > 0) {
            operation = in.next();
            word = in.next();
            if (operation.equals(add))
                trie.add(word);
            else
                System.out.println(trie.find(word));
        }
    }

}
