package pl.maciek.collection.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    void shouldAddElement() {
        //given
        var myArrayList = new MyArrayList<String>();

        //when
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        myArrayList.add("d");
        myArrayList.add("e");

        //then
        assertEquals(myArrayList.get(0), "a");
        assertEquals(myArrayList.get(1), "b");
        assertEquals(myArrayList.get(2), "c");
        assertEquals(myArrayList.get(3), "d");
        assertEquals(myArrayList.get(4), "e");

        assertEquals(myArrayList.size(), 5);
    }

    @Test
    void shouldAddElement_whenCapacityIsReached() {
        //given
        var myArrayList = new MyArrayList<String>(3);

        //when
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        myArrayList.add("d");
        myArrayList.add("e");

        //then
        assertEquals(myArrayList.get(0), "a");
        assertEquals(myArrayList.get(1), "b");
        assertEquals(myArrayList.get(2), "c");
        assertEquals(myArrayList.get(3), "d");
        assertEquals(myArrayList.get(4), "e");

        assertEquals(myArrayList.size(), 5);
    }
}