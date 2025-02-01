package pl.maciek.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTreeMapTest {

    @Test
    void shouldAddTwoEntries() {
        //given
        var map = new MyBinarySearchTreeMap<Integer, String>();

        //when
        map.put(1, "a");
        map.put(2, "b");

        //then
        assertEquals("a", map.get(1));
        assertEquals("b", map.get(2));
        assertEquals(2, map.size());
    }

    @Test
    void shouldReplaceValueOfEntry_WhenEntryWithGivenKeyExist() {
        //given
        var map = new MyBinarySearchTreeMap<String, String>();
        map.put("A", "A");
        map.put("B", "B");
        map.put("C", "C");

        //when
        var result = map.put("B", "D");

        //then
        assertEquals(3, map.size());
        assertEquals("A", map.get("A"));
        assertEquals("D", map.get("B"));
        assertEquals("C", map.get("C"));
        assertFalse(map.containsValue("B"));
        assertEquals("B", result);
    }

    @Test
    void shouldThrowException_whenAddingEntryWithNullKey() {
        //given
        var map = new MyBinarySearchTreeMap<String, String>();

        //then
        assertThrows(NullPointerException.class, () -> map.put(null, "a"));
        assertEquals(0, map.size());
    }
    @Test
    void shouldAddTwoEntriesWithNullAsValues() {
        //given
        var map = new MyBinarySearchTreeMap<String, String>();

        //when
        map.put("A", null);
        map.put("B", null);

        //then
        assertNull(map.get("A"));
        assertNull(map.get("B"));
        assertEquals(2, map.size());
    }

    @Test
    void shouldReturnNull_WhenEntryWithGivenKeyIsNotPresent() {
        //given
        var map = new MyBinarySearchTreeMap<String, String>();

        //when
        map.put("A", "a");

        //then
        assertNull(map.get("B"));
    }

    @Test
    void shouldReturnKeySet() {
        // given
        var map = testBinarySearchTree();
        //when
        var keySet = map.keySet();

        //then
        assertTrue(keySet.contains("A"));
        assertTrue(keySet.contains("C"));
        assertTrue(keySet.contains("E"));
        assertTrue(keySet.contains("J"));
        assertTrue(keySet.contains("AAA"));
        assertTrue(keySet.contains("BAB"));
        assertEquals(6, keySet.size());
    }

    @Test
    void shouldReturnValues() {
        // given
        var map = testBinarySearchTree();

        //when
        var values = map.values();

        //then
        assertTrue(values.contains("valueA"));
        assertTrue(values.contains("valueC"));
        assertTrue(values.contains("valueE"));
        assertTrue(values.contains("valueJ"));
        assertTrue(values.contains("valueAAA"));
        assertTrue(values.contains("valueBAB"));
        assertEquals(6, values.size());
    }

    @Test
    void shouldReturnEntries() {
        // given
        var map = testBinarySearchTree();

        //when
        var entrySet = map.entrySet();

        //then
        assertEquals("J", entrySet.get(0).getKey());
        assertEquals("valueJ", entrySet.get(0).getValue());
        assertEquals("A", entrySet.get(1).getKey());
        assertEquals("valueA", entrySet.get(1).getValue());
        assertEquals("AAA", entrySet.get(2).getKey());
        assertEquals("valueAAA", entrySet.get(2).getValue());
        assertEquals("C", entrySet.get(3).getKey());
        assertEquals("valueC", entrySet.get(3).getValue());
        assertEquals("BAB", entrySet.get(4).getKey());
        assertEquals("valueBAB", entrySet.get(4).getValue());
        assertEquals("E", entrySet.get(5).getKey());
        assertEquals("valueE", entrySet.get(5).getValue());
        assertEquals(6, entrySet.size());
    }

    @Test
    void shouldReturnTrue_whenContainsValue() {
        // given
        var map = testBinarySearchTree();

        //then
        assertTrue(map.containsValue("valueA"));
        assertTrue(map.containsValue("valueC"));
        assertTrue(map.containsValue("valueE"));
        assertTrue(map.containsValue("valueJ"));
        assertTrue(map.containsValue("valueAAA"));
        assertTrue(map.containsValue("valueBAB"));
    }

    @Test
    void shouldReturnFalse_whenDoesNotContainValue() {
        // given
        var map = testBinarySearchTree();

        //then
        assertFalse(map.containsValue("valueZ"));
    }

    @Test
    void shouldReturnTrue_whenContainsKey() {
        // given
        var map = testBinarySearchTree();

        //then
        assertTrue(map.containsKey("A"));
        assertTrue(map.containsKey("C"));
        assertTrue(map.containsKey("E"));
        assertTrue(map.containsKey("J"));
        assertTrue(map.containsKey("AAA"));
        assertTrue(map.containsKey("BAB"));
    }

    @Test
    void shouldReturnFalse_whenDoesNotContainKey() {
        // given
        var map = testBinarySearchTree();

        //then
        assertFalse(map.containsKey("B"));
    }

    @Test
    void shouldReturnTrue_whenMapIsEmpty() {
        // given
        var map = new MyBinarySearchTreeMap<>();

        //then
        assertTrue(map.isEmpty());
    }

    @Test
    void shouldReturnFalse_whenMapIsNotEmpty() {
        // given
        var map = new MyBinarySearchTreeMap<String, String>();
        map.put("a", "A");

        //then
        assertFalse(map.isEmpty());
    }

    @Test
    void shouldClear() {
        // given
        var map = new MyBinarySearchTreeMap<String, String>();
        map.put("a", "A");
        map.put("b", "B");

        // when
        map.clear();

        //then
        assertEquals(0, map.size());
        assertFalse(map.containsKey("a"));
        assertFalse(map.containsKey("b"));
//        assertFalse(map.containsValue("A"));
//        assertFalse(map.containsValue("B"));
    }
//
//    @Test
//    void shouldRemoveEntry_WhenEntryIsInMiddleOfBucket() {
//        // given
//        var map = testHashMap();
//
//        // KEY4 collides in bucket 5
//        // giving keys structure in bucket 5: KEY4 -> AAA -> A
//        map.put("KEY4", "valueKEY4");
//
//        // when
//        var returnedValue = map.remove("AAA");
//
//        //then
//        assertEquals(6, map.size());
//        assertEquals("valueAAA", returnedValue);
//        assertFalse(map.containsKey("AAA"));
//        assertFalse(map.containsValue("valueAAA"));
//        assertTrue(map.containsKey("KEY4"));
//        assertTrue(map.containsValue("valueKEY4"));
//        assertTrue(map.containsKey("A"));
//        assertTrue(map.containsValue("valueA"));
//    }
//
//    @Test
//    void shouldRemoveEntry_WhenEntryIsHeadOfBucket() {
//        // given
//        var map = testHashMap();
//
//        // KEY4 collides in bucket 5
//        // giving keys structure in bucket 5: KEY4 -> AAA -> A
//        map.put("KEY4", "valueKEY4");
//
//        // when
//        var returnedValue = map.remove("KEY4");
//
//        //then
//        assertEquals(6, map.size());
//        assertEquals("valueKEY4", returnedValue);
//        assertFalse(map.containsKey("KEY4"));
//        assertFalse(map.containsValue("valueKEY4"));
//        assertTrue(map.containsKey("AAA"));
//        assertTrue(map.containsValue("valueAAA"));
//        assertTrue(map.containsKey("A"));
//        assertTrue(map.containsValue("valueA"));
//    }
//
//    @Test
//    void shouldRemoveEntry_WhenEntryIsAtEndOfBucket() {
//        // given
//        var map = testHashMap();
//
//        // KEY4 collides in bucket 5
//        // giving keys structure in bucket 5: KEY4 -> AAA -> A
//        map.put("KEY4", "valueKEY4");
//
//        // when
//        var returnedValue = map.remove("A");
//
//        //then
//        assertEquals(6, map.size());
//        assertEquals("valueA", returnedValue);
//        assertFalse(map.containsKey("A"));
//        assertFalse(map.containsValue("valueA"));
//        assertTrue(map.containsKey("KEY4"));
//        assertTrue(map.containsValue("valueKEY4"));
//        assertTrue(map.containsKey("AAA"));
//        assertTrue(map.containsValue("valueAAA"));
//    }
//
//    @Test
//    void shouldRemoveEntry_WhenKeyIsNull() {
//        // given
//        var map = testHashMap();
//        map.put(null, "valueNull");
//
//        // when
//        var returnedValue = map.remove(null);
//
//        //then
//        assertEquals(6, map.size());
//        assertEquals("valueNull", returnedValue);
//        assertFalse(map.containsKey(null));
//        assertFalse(map.containsValue("valueNull"));
//    }
//
//    @Test
//    void shouldPreserveEntries_afterRehashing() {
//        //given
//        var map = testHashMap();
//
//        //when
//        map.put("KEY_1", "value1");
//        map.put("KEY_2", "value2");
//        map.put("KEY_3", "value3");
//
//        //then
//        assertEquals("valueA", map.get("A"));
//        assertEquals("valueC", map.get("C"));
//        assertEquals("valueE", map.get("E"));
//        assertEquals("valueJ", map.get("J"));
//        assertEquals("valueAAA", map.get("AAA"));
//        assertEquals("valueBAB", map.get("BAB"));
//        assertEquals("value1", map.get("KEY_1"));
//        assertEquals("value2", map.get("KEY_2"));
//        assertEquals("value3", map.get("KEY_3"));
//        assertEquals(9, map.size());
//    }
//
    @Test
    void shouldReturnCorrectValue_whenKeysAreStringObjects() {
        //given
        var map = new MyBinarySearchTreeMap<String, String>();
        String key1 = new String("key");
        String key2 = new String("key");

        //when
        map.put(key1, "Value1");
        map.put(key2, "Value2");

        //then
        assertEquals("Value2", map.get("key"));
    }
//
//

    private MyBinarySearchTreeMap<String, String> testBinarySearchTree() {
        var map = new MyBinarySearchTreeMap<String, String>();
        map.put("A", "valueA");
        map.put("C", "valueC");
        map.put("E", "valueE");
        map.put("J", "valueJ");
        map.put("AAA", "valueAAA");
        map.put("BAB", "valueBAB");
        return map;
    }

}
