package hackerrank.dataStructures.arrays;

import java.util.Arrays;

public class MinimumSwaps2 {

    //https://www.hackerrank.com/challenges/minimum-swaps-2

    public static void main(String[] args) {
        int[] arr = {4, 3, 1, 2};
        System.out.println(minimumSwaps(arr));
    }

    static int minimumSwaps(int[] arr) {
        // creating an position array, where the position indicates value and value indicates position
        int[] posArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            posArr[arr[i] - 1] = i;
        }

        System.out.println("arr: " + Arrays.toString(arr));
        System.out.println("pos: " + Arrays.toString(posArr));
        System.out.println();

        int i, pos, value, aux, swapsCounter;
        swapsCounter = 0;

        for (i = 0; i < arr.length; i++) {
            pos = posArr[i];
            value = arr[i];

            if (arr[i] != arr[pos]) {

                aux = arr[i];
                arr[i] = arr[pos];
                arr[pos] = aux;

                aux = posArr[i];
                posArr[i] = posArr[value - 1];
                posArr[value - 1] = aux;

                swapsCounter++;

                System.out.println("arr: " + Arrays.toString(arr));
                System.out.println("pos: " + Arrays.toString(posArr));
                System.out.println();

            }
        }
        return swapsCounter;
    }
}
