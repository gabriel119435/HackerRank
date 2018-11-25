package arrays;

public class TwoDArrayDS {

	public static void main(String[] args) {
		int[][] arr = new int[][]{
			{1, 1, 1, 0, 0, 0},
			{0, 1, 0, 0, 0, 0},
			{1, 1, 1, 0, 0, 0},
			{0, 0, 2, 4, 4, 0},
			{0, 0, 0, 2, 0, 0},
			{0, 0, 1, 2, 4, 0}
		};
		System.out.println(hourglassSum(arr));
	}


	static int hourglassSum(int[][] arr) {

		int biggestSum = 0;
		int currentSum = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				currentSum = arr[i  ][j] + arr[i  ][j+1] + arr[i  ][j+2]
						                 + arr[i+1][j+1]
						   + arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2];
				
				System.out.println("hourglassSum: " + currentSum);
				System.out.println(arr[i][j] + " " + arr[i][j + 1] + " " + arr[i][j + 2]);
				System.out.println("  " + arr[i + 1][j + 1]);
				System.out.println(arr[i + 2][j] + " " + arr[i + 2][j+1] +" "+ arr[i+2][j+2]);
					
				if(currentSum > biggestSum || i==0 && j==0)
					biggestSum = currentSum;
			}
		}

		return biggestSum;
	}

}
