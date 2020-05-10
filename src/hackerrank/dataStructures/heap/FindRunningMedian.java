package hackerrank.dataStructures.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


public class FindRunningMedian {

    //https://www.hackerrank.com/challenges/find-the-running-median

    static double[] runningMedian(int[] numbers) {
        double[] results = new double[numbers.length];

        // biggest number first
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>(Comparator.reverseOrder());
        // smallest number first
        PriorityQueue<Integer> bigHeap = new PriorityQueue<>();

        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];
            // insert number on one heap
            if (bigHeap.isEmpty()) {
                bigHeap.add(n);
            } else {
                Integer smallestBigNumber = bigHeap.peek();
                if (n < smallestBigNumber) {
                    smallHeap.add(n);
                } else {
                    bigHeap.add(n);
                }
            }

            //matching heaps height
            int smallSize = smallHeap.size();
            int bigSize = bigHeap.size();
            int diff = bigSize - smallSize;
            if (diff == 2) {
                smallHeap.add(bigHeap.remove());
            } else if (diff == -2) {
                bigHeap.add(smallHeap.remove());
            }

            // find median
            smallSize = smallHeap.size();
            bigSize = bigHeap.size();

            Integer smallerPeek = smallHeap.isEmpty() ? 0 : smallHeap.peek();
            Integer biggerPeek = bigHeap.isEmpty() ? 0 : bigHeap.peek();

            if (smallSize == bigSize) {
                double sum = (smallerPeek + biggerPeek) / 2D;
                results[i] = sum;
            } else if (smallSize > bigSize) {
                results[i] = smallerPeek;
            } else {
                results[i] = biggerPeek;
            }
        }
        return results;
    }


    public static void main(String[] args) {
        int[] numbers = {12, 4, 5, 3, 8, 7};
        System.out.println(Arrays.toString(runningMedian(numbers)));
    }
}
