package hackerrank.algorithms.implementation;

public class CountingValleys {

    //https://www.hackerrank.com/challenges/counting-valleys/problem

    public static void main(String[] args) {
        int n = 8;
        String steps = "UDDDUDUU";
        System.out.println(countingValleys(n, steps));
    }

    static int countingValleys(int n, String s) {
        int height = 0;
        boolean previousLocation, atValley = false;
        int valleys = 0;

        for (char step : s.toCharArray()) {

            previousLocation = atValley;

            if (step == 'D' && height == 0)
                atValley = true;

            if (step == 'U' && height == -1)
                atValley = false;

            if (previousLocation && !atValley)
                valleys++;

            if (step == 'U')
                height++;
            else
                height--;
        }

        return valleys;

    }
}
