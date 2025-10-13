package diceroller;

import java.util.ArrayList;
import java.util.HashMap;

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
        int n = map.values().stream().mapToInt(Integer::intValue).sum();
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

            // frequency map
            map.put(current, map.getOrDefault(current, 0) + 1);

            // consecutive streaks
            if (previous == current) {
                countOcc++;
            } else {
                consecutiveRolls.put(countOcc, consecutiveRolls.getOrDefault(countOcc, 0) + 1);
                countOcc = 1;
            }
        }
        // record final streak
        consecutiveRolls.put(countOcc, consecutiveRolls.getOrDefault(countOcc, 0) + 1);
    }

    // Percentage check per face
    private boolean percentageCheck(int total) {
        System.out.println("\n--- Percentage Check ---");
        double expectedPercent = 100.0 / 6.0;
        boolean pass = true;

        for (int i = 1; i <= 6; i++) {
            int observed = map.getOrDefault(i, 0);
            double percent = (observed * 100.0) / total;
            double diff = percent - expectedPercent;
            System.out.printf("Face %d -> %.2f%% (Diff: %.2f%%)%n", i, percent, diff);

            if (Math.abs(diff) > 2.0) {
                pass = false;
            }
        }

        if (pass) {
            System.out.println("Reasoning: All faces are within ±2% of the expected 16.67% → consistent with randomness.");
        } else {
            System.out.println("Reasoning: One or more faces deviated by more than ±2% → suggests possible bias.");
        }
        return pass;
    }

    // Z-score check per face
    private boolean zScoreCheck(int total) {
        System.out.println("\n--- Z-Score Check ---");
        double expected = (double) total / 6.0;
        double stdDev = Math.sqrt(expected);
        boolean pass = true;

        for (int i = 1; i <= 6; i++) {
            int observed = map.getOrDefault(i, 0);
            double z = (observed - expected) / stdDev;
            System.out.printf("Face %d -> Observed: %d | Expected: %.2f | Z-score: %.2f%n",
                              i, observed, expected, z);

            if (Math.abs(z) > 3.0) {
                pass = false;
            }
        }

        if (pass) {
            System.out.println("Reasoning: All Z-scores are within ±3 → variations are within normal statistical fluctuation.");
        } else {
            System.out.println("Reasoning: One or more Z-scores exceed ±3 → variation is too extreme for random rolls.");
        }
        return pass;
    }

    // Entropy check
   // Entropy check
private boolean entropyCheck(int total) {
    System.out.println("\n--- Entropy Check ---");
    double entropy = 0.0;
    for (int i = 1; i <= 6; i++) {
        int observed = map.getOrDefault(i, 0);
        if (observed > 0) {
            double p = (double) observed / total;
            entropy += -p * (Math.log(p) / Math.log(2)); // log base 2
        }
    }
    double maxEntropy = Math.log(6) / Math.log(2);
    System.out.printf("Entropy: %.4f / %.4f bits%n", entropy, maxEntropy);

    boolean pass = (maxEntropy - entropy) < 0.05; // within ~0.05 bits of ideal

    if (pass) {
        System.out.println("Reasoning: Entropy is very close to the theoretical maximum ("
                + String.format("%.2f", maxEntropy)
                + " bits) → distribution spreads evenly across faces, consistent with randomness.");
    } else {
        System.out.println("Reasoning: Entropy is noticeably below maximum → outcomes are too predictable or skewed, suggesting bias.");
    }

    return pass;
}


    // Master check
    public void validateAndPrint() {
        System.out.println("First Ten Indexs");
        for (int i = 0; i < Math.min(10, arr.size()); i++) {
            System.out.println("Index (" + i + "): Rolled " + arr.get(i));
        }
        System.out.println("Array Size: " + arr.size());

        int sumFreq = map.values().stream().mapToInt(Integer::intValue).sum();
        double expected = (double) sumFreq / 6.0;

        System.out.println("\n--- Frequency Comparison ---");
        for (int i = 1; i <= 6; i++) {
            int observed = map.getOrDefault(i, 0);
            double diff = observed - expected;
            System.out.printf("Face %d -> Observed: %d | Expected: %.2f | Difference: %.2f%n",
                              i, observed, expected, diff);
        }

        // Chi-Square check
        double chi = chiSqrTest();
        System.out.println("\n--- Chi-Square Check ---");
        System.out.println("Chi-Square Value: " + chi);
        boolean chiPass = chi <= 11.05;

        if (chiPass) {
            System.out.println("Reasoning: Chi-square ≤ 11.05 → distribution is statistically consistent with fairness.");
        } else {
            System.out.println("Reasoning: Chi-square > 11.05 → distribution unlikely to come from a fair die.");
        }

        // Extra checks
        boolean percPass = percentageCheck(sumFreq);
        boolean zPass = zScoreCheck(sumFreq);
        boolean entPass = entropyCheck(sumFreq);

        // Final conclusion
        System.out.println("\n--- Final Validation ---");
        if (chiPass && percPass && zPass && entPass) {
            System.out.println("✅ Die appears RANDOM (all tests passed).");
        } else {
            System.out.println("❌ Die appears NOT RANDOM (one or more tests failed).");
        }

        DiceBarGraph.showGraph(map);
    }
}
