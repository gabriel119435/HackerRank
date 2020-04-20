package javaCode.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class FindRunningMedian {
    // https://www.hackerrank.com/challenges/find-the-running-median

    static void runningMedian(List<Integer> numbers) {

        PriorityQueue<Integer> smallerHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> biggerOrEqualHeap = new PriorityQueue<>();

        numbers.forEach(
                n -> {
                    // insert number on one heap
                    if (biggerOrEqualHeap.isEmpty()) {
                        biggerOrEqualHeap.add(n);
                    } else {
                        Integer smallestBigNumber = biggerOrEqualHeap.peek();
                        if (n < smallestBigNumber) {
                            smallerHeap.add(n);
                        } else {
                            biggerOrEqualHeap.add(n);
                        }
                    }

                    //match heaps
                    int smallerSize = smallerHeap.size();
                    int biggerOrEqualSize = biggerOrEqualHeap.size();
                    int sizeDiff = biggerOrEqualSize - smallerSize;
                    if (sizeDiff == 2) {
                        smallerHeap.add(biggerOrEqualHeap.remove());
                    } else if (sizeDiff == -2) {
                        biggerOrEqualHeap.add(smallerHeap.remove());
                    }

                    // find median
                    smallerSize = smallerHeap.size();
                    biggerOrEqualSize = biggerOrEqualHeap.size();

                    if (smallerSize == biggerOrEqualSize) {
                        double sum = smallerHeap.peek() + biggerOrEqualHeap.peek();
                        System.out.println(sum / 2D);
                    } else if (smallerSize > biggerOrEqualSize) {
                        System.out.println((double) smallerHeap.peek());
                    } else {
                        System.out.println((double) biggerOrEqualHeap.peek());
                    }
                }
        );
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        List<Integer> numbers = new ArrayList<>();
        while (n-- > 0) {
            int number = scan.nextInt();
            numbers.add(number);
        }
        scan.close();
        runningMedian(numbers);
    }
}
