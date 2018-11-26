package arrays;

public class ArrayManipulation {

	public static void main(String[] args) {
		int n = 5;
		int[][] queries = { { 1, 2, 100 }, 
				            { 2, 5, 100 }, 
				            { 3, 4, 100 } };
		/*
		1      2      3      4      5
		1             -1
		       1
		              1             -1
        1	   1      0      0      -1
		 * */
		System.out.println(arrayManipulation(n, queries));

	}

	static long arrayManipulation(int n, int[][] queries) {

		long[] arr = new long[n];

		int beginning = 0, end, sum;

		for (int[] currentQuery : queries) {
			// if number is 2, positive slope starts at position 1 (2nd position)
			beginning = currentQuery[0] - 1;
			// if number is 5, negative slope starts at position 5 (6th position, since 5th position still has the positive slope)
			end = currentQuery[1];
			sum = currentQuery[2];
			arr[beginning] += sum;
			if (end < n)
				arr[end] -= sum;
		}
		
		
		long max =0, temp = 0;

		for (int i = 0; i < n; i++) {
			temp += arr[i];
			if(temp>max)
				max = temp;
		}

		return max;

	}
}
