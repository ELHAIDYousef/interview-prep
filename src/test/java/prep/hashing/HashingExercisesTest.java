package prep.hashing;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Yousef ELHAID
 */
public class HashingExercisesTest {

    @Test
    public void twoSumReturnsTheIndexesOfTheTwoNumbers() {
        assertArrayEquals(new int[] {0, 1}, HashingExercises.twoSum(new int[] {2, 7, 11, 15}, 9));
    }

    @Test
    public void twoSumDoesNotUseTheSameElementTwice() {
        assertArrayEquals(new int[] {1, 2}, HashingExercises.twoSum(new int[] {3, 2, 4}, 6));
    }

    @Test
    public void twoSumWithoutASolutionThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> HashingExercises.twoSum(new int[] {1, 2}, 10));
    }

    @Test
    public void hasDuplicateDetectsARepeatedValue() {
        assertTrue(HashingExercises.hasDuplicate(new int[] {1, 2, 3, 1}));
    }

    @Test
    public void hasDuplicateIsFalseForDistinctOrNoValues() {
        assertFalse(HashingExercises.hasDuplicate(new int[] {1, 2, 3}));
        assertFalse(HashingExercises.hasDuplicate(new int[] {}));
    }

    @Test
    public void anagramsHaveTheSameLetterCounts() {
        assertTrue(HashingExercises.areAnagrams("listen", "silent"));
    }

    @Test
    public void sameLettersWithDifferentCountsAreNotAnagrams() {
        assertFalse(HashingExercises.areAnagrams("aab", "abb"));
    }

    @Test
    public void wordsOfDifferentLengthsAreNotAnagrams() {
        assertFalse(HashingExercises.areAnagrams("abc", "abcd"));
    }
}