package warmUpChallenges;
public class CountingValleys {

	public static void main(String[] args) {
		int n = 8;
		String steps = "UDDDUDUU";
		System.out.println(countingValleys(n, steps));
	}

	static int countingValleys(int n, String s) {
		int height = 0;
		boolean previvousLocation, atValley = false;
		int valleys = 0;

		for (char step : s.toCharArray()) {

			previvousLocation = atValley;

			if (step == 'D' && height == 0)
				atValley = true;

			if (step == 'U' && height == -1)
				atValley = false;

			if (previvousLocation && !atValley)
				valleys++;

			if (step == 'U')
				height++;
			else
				height--;
		}

		return valleys;

	}
}
