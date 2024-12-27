package pl.maciek.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyHashMapTest {

    @Test
    void shouldAddTwoElements() {
        //given
        var map = new MyHashMap<String, String>();

        //when
        map.put("key1", "a");
        map.put("key2", "b");

        //then
        assertEquals("a", map.get("key1"));
        assertEquals("b", map.get("key2"));
        assertEquals(2, map.size());
    }

    // key9 i key10 mają ten sam bucket
    @Test
    void shouldAddTwoElements_whichHashcodeAreSame() {
        //given
        var map = new MyHashMap<String, String>();

        //when
        map.put("key9", "a");
        map.put("key10", "b");

        //then
        assertEquals("a", map.get("key9"));
        assertEquals("b", map.get("key10"));
        assertEquals(2, map.size());
    }
}
