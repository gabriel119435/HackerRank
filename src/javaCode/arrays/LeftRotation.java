package javaCode.arrays;

import java.util.Arrays;

public class LeftRotation {

    //https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        int d = 4;
        System.out.println(Arrays.toString(rotLeft(a, d)));
    }

    static int[] rotLeft(int[] a, int d) {

        int length = a.length;

        int positionToBegin = d % length;

        if (positionToBegin == 0)
            return a;

        // 0 until 4
        int positionToBeInserted = 0;
        int[] b = a.clone();

        while (positionToBeInserted < length) {

            if (positionToBegin >= length)
                positionToBegin = 0;

            b[positionToBeInserted] = a[positionToBegin];

            positionToBegin++;
            positionToBeInserted++;
        }

        return b;
    }
}
