package hackerrank.algorithms.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class MarkAndToys {

    // https://www.hackerrank.com/challenges/mark-and-toys/problem

    static int maximumToys(long[] p, long b) {
        int c = 0;
        System.out.println(Arrays.toString(p));
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p.length - 1 - i; j++) {
                if (p[j] < p[j + 1]) {
                    long aux = p[j];
                    p[j] = p[j + 1];
                    p[j + 1] = aux;
                }
            }
            System.out.println(Arrays.toString(p));
            if (c + p[p.length - 1 - i] < b) {
                c += p[p.length - 1 - i];
            } else {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        long[] prices = new long[t];
        long k = scan.nextLong();
        for (int i = 0; i < t; i++) {
            prices[i] = scan.nextLong();
        }
        System.out.println(maximumToys(prices, k));
        scan.close();
    }
}
