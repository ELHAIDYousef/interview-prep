package prep.stackqueue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Yousef ELHAID
 */
public class BracketsTest {

    @Test
    public void emptyTextIsBalanced() {
        assertTrue(Brackets.isBalanced(""));
    }

    @Test
    public void nestedPairsAreBalanced() {
        assertTrue(Brackets.isBalanced("{[()]}"));
    }

    @Test
    public void charactersOtherThanBracketsAreIgnored() {
        assertTrue(Brackets.isBalanced("a(b)c[d]"));
    }

    @Test
    public void crossedPairsAreNotBalanced() {
        assertFalse(Brackets.isBalanced("([)]"));
    }

    @Test
    public void unclosedOpeningIsNotBalanced() {
        assertFalse(Brackets.isBalanced("(("));
    }

    @Test
    public void closingBeforeOpeningIsNotBalanced() {
        assertFalse(Brackets.isBalanced(")("));
    }
}