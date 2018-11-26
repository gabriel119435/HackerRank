package dictionariesAndHashmaps;

import java.util.Set;
import java.util.stream.Collectors;

public class TwoStrings {
	public static void main(String[] args) {

	}

	static String twoStrings(String s1, String s2) {

		Set<Character> hs1 = s1.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
		Set<Character> hs2 = s2.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());

		hs1.retainAll(hs2);
		if (hs1.isEmpty())
			return "NO";

		return "YES";

	}

}
