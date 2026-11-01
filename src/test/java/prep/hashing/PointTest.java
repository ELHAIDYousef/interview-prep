package prep.hashing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 *
 * @author Yousef ELHAID
 */
public class PointTest {

    @Test
    public void equalPointsHaveTheSameHashCode() {
        assertEquals(new Point(1, 2), new Point(1, 2));
        assertEquals(new Point(1, 2).hashCode(), new Point(1, 2).hashCode());
    }

    @Test
    public void differentPointsAreNotEqual() {
        assertNotEquals(new Point(1, 2), new Point(2, 1));
    }

    @Test
    public void pointIsNeitherEqualToNullNorToAnotherType() {
        assertFalse(new Point(1, 2).equals(null));
        assertFalse(new Point(1, 2).equals("(1, 2)"));
    }

    @Test
    public void anEqualPointIsFoundInAHashSet() {
        Set<Point> points = new HashSet<>();
        points.add(new Point(1, 2));
        assertTrue(points.contains(new Point(1, 2)));
    }
}