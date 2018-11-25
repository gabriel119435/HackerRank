package warmUpChallenges;

public class RepeatedString {

	public static void main(String[] args) {
		long n = 10;
		String s = "aba";
		System.out.println(repeatedString(s, n));
	}

	static long repeatedString(String s, long n) {
		char[] sequence = s.toCharArray();
		int seqLenght = s.length();

		int aInSequence = 0;
		for (char c : sequence)
			if (c == 'a')
				aInSequence++;

		long wholeTimesAAppeared = (n / seqLenght) * aInSequence;

		long lonelyLetters = n % seqLenght;

		if (lonelyLetters == 0)
			return wholeTimesAAppeared;

		s = s.substring(0, (int) lonelyLetters);

		aInSequence = 0;
		for (char c : s.toCharArray())
			if (c == 'a')
				aInSequence++;
		
		return aInSequence + wholeTimesAAppeared;
	}
}
