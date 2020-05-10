package hackerrank.dataStructures.stacks;

import java.util.Stack;

public class BalancedBrackets {

    //https://www.hackerrank.com/challenges/balanced-brackets/

    public static void main(String[] args) {
        System.out.println(isBalanced("{{)[](}}"));
    }

    static String isBalanced(String s) {

        Stack<Character> stack = new Stack<>();

        for (Character c : s.toCharArray()) {
            addBracket(stack, c);
        }

        if (stack.isEmpty()) return "YES";
        else return "NO";
    }

    static void addBracket(Stack<Character> stack, Character c) {
        if (stack.isEmpty()) stack.add(c);
        else {
            Character stackTip = stack.pop();
            if (!areTheseAreOpposite(stackTip, c)) {
                stack.add(stackTip);
                stack.add(c);
            }
        }
    }

    static boolean areTheseAreOpposite(Character c1, Character c2) {
        return c1.equals('(') && c2.equals(')') ||
                c1.equals('{') && c2.equals('}') ||
                c1.equals('[') && c2.equals(']');
    }
}
