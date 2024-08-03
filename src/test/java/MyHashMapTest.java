import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {
    MyHashMap<String, Integer> stringKeyMap;
    MyHashMap<Integer, String> integerKeyMap;

    @BeforeEach
    void setup() {
        stringKeyMap = new MyHashMap<>();
        stringKeyMap.put("one", 1);
        stringKeyMap.put("two", 2);
        integerKeyMap = new MyHashMap<>();
        integerKeyMap.put(1, "one");
        integerKeyMap.put(2, "two");
    }

    @Test
    void testSizeEmptyMap() {
        MyHashMap<Double, Boolean> myHashMap = new MyHashMap<>();
        assertEquals(0, myHashMap.size());
    }

    @Test
    void testSizeMultiElementMap() {
        assertEquals(2, stringKeyMap.size());
        assertEquals(2, integerKeyMap.size());
    }

    @Test
    void testContainsNullPointerExceptionTest() {
        NullPointerException npe = assertThrows(NullPointerException.class, () -> stringKeyMap.contains(null));
        assertEquals("Please, enter a correct key", npe.getMessage());
    }

    @Test
    void testContainsEmptyMap() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();
        assertFalse(myHashMap.contains(1));
        assertFalse(myHashMap.contains(-1));
        assertFalse(myHashMap.contains(100));
    }

    @Test
    void testContainsMultiElementMap() {
        assertFalse(stringKeyMap.contains("three"));
        assertTrue(stringKeyMap.contains("one"));
        assertTrue(stringKeyMap.contains("two"));
        assertFalse(integerKeyMap.contains(3));
        assertTrue(integerKeyMap.contains(1));
        assertTrue(integerKeyMap.contains(2));
    }

    @Test
    void testPutNullPointerException() {
        NullPointerException npe = assertThrows(NullPointerException.class, () -> stringKeyMap.put(null, 3));
        assertEquals("Please, enter a correct key", npe.getMessage());
    }

    @Test
    void testPutRebalanceTest() {
        assertEquals(2, stringKeyMap.size());
        stringKeyMap.put("three", 3);
        stringKeyMap.put("four", 4);
        stringKeyMap.put("five", 5);
        assertEquals(5, stringKeyMap.size());
    }

    @Test
    void testPutKeyExist() {
        assertTrue(stringKeyMap.contains("two"));
        assertEquals(2, stringKeyMap.get("two"));
        assertEquals(2, stringKeyMap.size());
        stringKeyMap.put("two", 4);
        assertEquals(4, stringKeyMap.get("two"));
        assertEquals(2, stringKeyMap.size());
        assertTrue(integerKeyMap.contains(2));
        assertEquals("two", integerKeyMap.get(2));
        integerKeyMap.put(2, "four");
        assertEquals("four", integerKeyMap.get(2));
        assertEquals(2, integerKeyMap.size());
    }

    @Test
    void testPutEmptyMap() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        assertEquals(0, myHashMap.size());
        myHashMap.put(1, "one");
        assertEquals(1, myHashMap.size());
        assertTrue(myHashMap.contains(1));
        assertEquals("one", myHashMap.get(1));
        myHashMap.put(2, "two");
        assertTrue(myHashMap.contains(2));
        assertEquals("two", myHashMap.get(2));
    }

    @Test
    void testPutMultiElementMap() {
        assertEquals(2, stringKeyMap.size());
        stringKeyMap.put("three", 3);
        stringKeyMap.put("four", 4);
        stringKeyMap.put("five", 5);
        assertEquals(5, stringKeyMap.size());
        assertTrue(stringKeyMap.contains("three"));
        assertTrue(stringKeyMap.contains("four"));
        assertTrue(stringKeyMap.contains("five"));
        assertEquals(3, stringKeyMap.get("three"));
        assertEquals(4, stringKeyMap.get("four"));
        assertEquals(5, stringKeyMap.get("five"));
    }

    @Test
    void testGetNullPointerException() {
        NullPointerException npe = assertThrows(NullPointerException.class, () -> stringKeyMap.get(null));
        assertEquals("Please, enter a correct key", npe.getMessage());
    }

    @Test
    void testGetEmptyList() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        assertEquals(0, myHashMap.size());
        assertNull(myHashMap.get(1));
        assertNull(myHashMap.get(-1));
        assertNull(myHashMap.get(100));
    }

    @Test
    void testGetMultiElementList() {
        assertEquals(2, integerKeyMap.size());
        assertEquals("one", integerKeyMap.get(1));
        assertEquals("two", integerKeyMap.get(2));
        integerKeyMap.put(3, "three");
        assertEquals(3, integerKeyMap.size());
        assertEquals("three", integerKeyMap.get(3));
    }

    @Test
    void testRemoveNullPointerException() {
        NullPointerException npe = assertThrows(NullPointerException.class, () -> stringKeyMap.remove(null));
        assertEquals("Please, enter a correct key", npe.getMessage());
    }

    @Test
    void testRemoveEmptyMap() {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        assertEquals(0, myHashMap.size());
        assertNull(myHashMap.remove("one"));
        assertNull(myHashMap.remove("two"));
        assertNull(myHashMap.remove("three"));
        assertEquals(0, myHashMap.size());
    }

    @Test
    void testRemoveMultiElementMap() {
        assertEquals(2, integerKeyMap.size());
        assertTrue(integerKeyMap.contains(2));
        assertEquals("two", integerKeyMap.remove(2));
        assertEquals(1, integerKeyMap.size());
        assertFalse(integerKeyMap.contains(2));
        assertTrue(integerKeyMap.contains(1));
        assertEquals("one", integerKeyMap.remove(1));
        assertEquals(0, integerKeyMap.size());
        assertFalse(integerKeyMap.contains(1));
    }

    @Test
    void testToStringEmptyMap() {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        assertEquals(0, myHashMap.size());
        assertEquals("[]", myHashMap.toString());
    }

    @Test
    void testToStringMultiElementMap() {
        assertEquals(2, stringKeyMap.size());
        assertEquals("[Pair{Key=two, Value=2}, Pair{Key=one, Value=1}]", stringKeyMap.toString());
    }

}