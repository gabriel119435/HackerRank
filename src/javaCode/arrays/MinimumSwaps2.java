package javaCode.arrays;

public class MinimumSwaps2 {

    //https://www.hackerrank.com/challenges/minimum-swaps-2/problem

    public static void main(String[] args) {
        int[] arr = {4, 3, 1, 2};
        System.out.println(minimumSwaps(arr));
    }

    static int minimumSwaps(int[] arr) {
        System.out.println("arr.length: " + arr.length);
        // creating an position array, where the position indicates value and value indicates position
        int[] posArr = new int[arr.length];
        int value, position;
        for (int i = 0; i < arr.length; i++) {
            System.out.println("i: " + i);
            value = arr[i];
            position = i;
            posArr[value - 1] = position;
        }

        System.out.println("posArr:");
        for (Integer i : posArr) {
            System.out.println(i);
        }

        int indexPosition, indexValue, aux, exchangeCounter;
        exchangeCounter = 0;
        for (int index = 0; index < arr.length; index++) {
            indexPosition = posArr[index];
            indexValue = arr[index];

            if (arr[index] != arr[indexPosition]) {

                aux = arr[index];
                arr[index] = arr[indexPosition];
                arr[indexPosition] = aux;

                aux = posArr[index];
                posArr[index] = posArr[indexValue - 1];
                posArr[indexValue - 1] = aux;

                exchangeCounter++;
            }
        }

        System.out.println("arr:");
        for (Integer i : arr) {
            System.out.println(i);
        }

        return exchangeCounter;

    }
}
