package arrays;

import java.util.HashMap;
import java.util.Map;

public class NewYearChaos {

	public static void main(String[] args) {
		int[] q = new int[] { 1, 2, 5, 3, 4, 7, 8, 6 };
		NewYearChaos.minimumBribes(q);
	}

	// 1 2 3 4 5 7 6 8

	static void minimumBribes(int[] q) {
		Map<Integer, Integer> bribeCounter = new HashMap<>();
		boolean isTooChaotic = false;
		boolean itChanged = true;

		while (itChanged) {
			itChanged = false;
			for (int i = 0; i < q.length - 1 && !isTooChaotic; i++) {
				if (q[i + 1] < q[i]) {
					// i bribed i+1
					if (bribeCounter.containsKey(q[i])) {
						if (bribeCounter.get(q[i]) == 2)
							isTooChaotic = true;
						else
							bribeCounter.put(q[i], 2);
					} else
						bribeCounter.put(q[i], 1);

					int aux = q[i];
					q[i] = q[i + 1];
					q[i + 1] = aux;
					itChanged = true;

				}
			}
		}
		if (isTooChaotic)
			System.out.println("Too chaotic");
		else {
			int totalBribes = 0;
			for (int i : bribeCounter.keySet()) {
				totalBribes += bribeCounter.get(i);
			}
			System.out.println(totalBribes);
		}

	}
}

// 2 1 5 3 4 -- 1 is shorter than 2, change -- 1 2 5 3 4 -- 2 has 1 bribe
// 1 2 5 3 4 -- 5 is not shorter than 2, keep -- 1 2 5 3 4
// 1 2 5 3 4 -- 3 is shorter than 5, change -- 1 2 3 5 4 -- 5 has 1 bribe
// 1 2 3 5 4 -- 4 is shorter then 5, change -- 1 2 3 4 5 -- 5 has 2 bribes
