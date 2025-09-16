package diceroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiceRandomnessChecker {
    private final ArrayList<Integer> arr;
    private final HashMap<Integer, Integer> map = new HashMap<>();
    private final HashMap<Integer, Integer> consecutiveRolls = new HashMap<>();

    public DiceRandomnessChecker(ArrayList<Integer> arr) {
        this.arr = arr;
        counter();
    }

    // Chi-Square Test
    public double chiSqrTest() {
        int n = arr.size();
        if (n == 0) return 0.0;

        double expected = (double) n / 6.0;
        double chiSquare = 0.0;

        for (int i = 1; i <= 6; i++) {
            int observed = map.getOrDefault(i, 0);
            chiSquare += Math.pow(observed - expected, 2) / expected;
        }
        return chiSquare;
    }

    // Count frequency & consecutive streaks
    private void counter() {
        map.clear();
        consecutiveRolls.clear();

        if (arr == null || arr.isEmpty()) return;

        // count the first element
        int first = arr.get(0);
        map.put(first, 1);

        int countOcc = 1;
        for (int i = 1; i < arr.size(); i++) {
            int previous = arr.get(i - 1);
            int current = arr.get(i);

            // frequency map: count current
            map.put(current, map.getOrDefault(current, 0) + 1);

            // consecutive streaks
            if (previous == current) {
                countOcc++;
            } else {
                // record the streak of length countOcc
                consecutiveRolls.put(countOcc, consecutiveRolls.getOrDefault(countOcc, 0) + 1);
                countOcc = 1;
            }
        }

        // record final streak
        consecutiveRolls.put(countOcc, consecutiveRolls.getOrDefault(countOcc, 0) + 1);
    }

    // Debug / validation helper
    public void validateAndPrint() {
        System.out.println("Array Size: " + arr.size());
        int sumFreq = map.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("Frequency map (Distribution): " + map);
        System.out.println("Sum of frequency map values = " + sumFreq);
        System.out.println("Consecutive rolls (streakLength -> count): " + consecutiveRolls);
        System.out.println("Chi-Square Test : " + chiSqrTest());
        
    }
}
