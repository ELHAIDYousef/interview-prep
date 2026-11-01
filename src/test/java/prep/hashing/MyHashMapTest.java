package prep.hashing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Yousef ELHAID
 */
public class MyHashMapTest {

    private final MyHashMap<String, Integer> map = new MyHashMap<>();

    @Test
    public void newMapIsEmpty() {
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    public void getReturnsThePutValue() {
        map.put("ali", 25);
        assertEquals(Integer.valueOf(25), map.get("ali"));
        assertTrue(map.containsKey("ali"));
    }

    @Test
    public void getOfAMissingKeyReturnsNull() {
        assertNull(map.get("ali"));
        assertFalse(map.containsKey("ali"));
    }

    @Test
    public void putWithAnExistingKeyReplacesTheValue() {
        map.put("ali", 25);
        map.put("ali", 26);
        assertEquals(Integer.valueOf(26), map.get("ali"));
        assertEquals(1, map.size());
    }

    @Test
    public void nullKeyIsSupported() {
        map.put(null, 1);
        assertEquals(Integer.valueOf(1), map.get(null));
    }

    @Test
    public void removeDeletesTheKeyAndReturnsItsValue() {
        map.put("ali", 25);
        assertEquals(Integer.valueOf(25), map.remove("ali"));
        assertFalse(map.containsKey("ali"));
        assertTrue(map.isEmpty());
    }

    @Test
    public void removeOfAMissingKeyReturnsNull() {
        assertNull(map.remove("ali"));
    }

    @Test
    public void keysWithTheSameHashCodeAreBothKept() {
        map.put("Aa", 1);
        map.put("BB", 2);
        map.remove("Aa");
        assertNull(map.get("Aa"));
        assertEquals(Integer.valueOf(2), map.get("BB"));
    }

    @Test
    public void allEntriesSurviveSeveralResizes() {
        for (int i = 0; i < 1000; i++) {
            map.put("key" + i, i);
        }
        assertEquals(1000, map.size());
        for (int i = 0; i < 1000; i++) {
            assertEquals(Integer.valueOf(i), map.get("key" + i));
        }
    }
}
