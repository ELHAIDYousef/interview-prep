package prep.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 *
 * @author Yousef ELHAID
 */
public final class Brackets {

    private static final Map<Character, Character> OPENING_FOR_CLOSING =
            Map.of(')', '(', ']', '[', '}', '{');

    private Brackets() {
    }

    public static boolean isBalanced(String text) { // T O(n) M O(n)
        Deque<Character> openings = new ArrayDeque<>();
        for (char character : text.toCharArray()) {
            if (OPENING_FOR_CLOSING.containsValue(character)) {
                openings.push(character);
            } else if (OPENING_FOR_CLOSING.containsKey(character)) {
                char expectedOpening = OPENING_FOR_CLOSING.get(character);
                if (openings.isEmpty() || openings.pop() != expectedOpening) {
                    return false;
                }
            }
        }
        return openings.isEmpty();
    }
}