package prep.hashing;

import java.util.*;

/**
 *
 * @author Yousef ELHAID
 */
public class HashingExercises {

    public static int[] twoSum(int[] numbers, int target) { // T O(n) M O(n)
        Map<Integer, Integer> indexByValue = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];
            Integer complementIndex = indexByValue.get(complement);
            if (complementIndex != null) {
                return new int[] {complementIndex, i};
            }
            indexByValue.put(numbers[i], i);
        }
        throw new IllegalArgumentException("No two numbers add up to " + target);
    }

    public static boolean hasDuplicate(int[] numbers) { // T O(n) M O(n)
        Set<Integer> seen = new HashSet<>();
        for (int number : numbers) {
            if (!seen.add(number)) {
                return true;
            }
        }
        return false;
    }

    public static boolean areAnagrams(String first, String second) { // T O(n) M O(k)
        if (first.length() != second.length()) {
            return false;
        }
        Map<Character, Integer> letterCounts = new HashMap<>();
        for (char letter : first.toCharArray()) {
            letterCounts.merge(letter, 1, Integer::sum);
        }
        for (char letter : second.toCharArray()) {
            int remaining = letterCounts.getOrDefault(letter, 0) - 1;
            if (remaining < 0) {
                return false;
            }
            letterCounts.put(letter, remaining);
        }
        return true;
    }
}
