import java.util.List;

// https://github.com/VigilTech/backend-challenge

public class SplitString {
    static public String breakLines(String s, int limit) {
        List<String> words = List.of(s.split(" "));
        String result = "";
        int limitUsed = 0;
        for (String currWord : words) {
            if (limitUsed + currWord.length() > limit) { // check if limit doesn't fit a new word
                // doesn't fit, remove last space, break line and reset limitUsed
                String resultWithoutEndingSpace = removeLastChar(result);
                if (resultWithoutEndingSpace.length() > 0) result = resultWithoutEndingSpace + "\n";
                limitUsed = 0;
            }
            // keep building line with word and new space, increasing counter
            limitUsed += currWord.length() + 1;
            result += currWord + " ";
        }
        // remove last trailing space
        if (endsWithSpace(result)) result = removeLastChar(result);
        return result;
    }

    static private String removeLastChar(String s) {
        if (s.length() == 0) return s;
        return s.substring(0, s.length() - 1);
    }

    static private boolean endsWithSpace(String s) {
        if (s.length() == 0) return false;
        return s.charAt(s.length() - 1) == ' ';
    }

    public static void main(String[] args) {
        /*
         * assumptions:
         * 1. if there's a word bigger than the limit itself, the word will be printed alone on its own
         * ex: ("a bbb", 1) will result in "a\nbbb"
         * 2. limit should be >= 1
         * 3. spaces are always 1 char long only
         */

        /*
         * time complexity: O(n) where n = s.length()
         *
         * 1. List.of(s.split(" ")); // O(n) where n = s.length()
         * 2. for (String currWord : words) { // O(m) where m = words.size(). since words.size() <= s.length, ignored
         * 3. all other operations were index related, so constant complexity, ignored
         * */

        System.out.println("starting spaces");
        System.out.println(breakLines(" b", 10));
        System.out.println(breakLines(" b", 1));
        System.out.println("between spaces");
        System.out.println(breakLines(" b ", 10));
        System.out.println(breakLines(" b ", 1));
        System.out.println("ending spaces");
        System.out.println(breakLines("b ", 10));
        System.out.println(breakLines("b ", 1));
        System.out.println("longer words");
        System.out.println(breakLines("aaaaa aaaaa", 1));
        System.out.println("empty string");
        System.out.println(breakLines("", 1));
        System.out.println(breakLines("", 100));
        System.out.println(breakLines("In 1991, while studying computer science at University of Helsinki, Linus Torvalds began a project that later became the Linux kernel. He wrote the program specifically for the hardware he was using and independent of an operating system because he wanted to use the functions of his new PC with an 80386 processor. Development was done on MINIX using the GNU C Compiler.", 40));
    }
}
