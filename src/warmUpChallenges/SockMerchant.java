package warmUpChallenges;
import java.util.HashSet;
import java.util.Set;

public class SockMerchant {

	public static void main(String[] args) {
		int n = 9;
		int[] socks = { 10, 20, 20, 10, 10, 30, 50, 10, 20 };
		System.out.println(sockMerchant(n, socks));
	}

	static int sockMerchant(int n, int[] ar) {

		Set<Integer> socksAlreadyFound = new HashSet<>();
		Integer pairs = 0;
		for (Integer someSock : ar) {
			if (socksAlreadyFound.contains(someSock)) {
				socksAlreadyFound.remove(someSock);
				pairs++;
			} else
				socksAlreadyFound.add(someSock);
		}
		return pairs;
	}

}
