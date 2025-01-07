package pl.maciek.collection.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    void shouldAddElement_toEmptyList() {
        //given
        var myArrayList = new MyArrayList<String>();

        //when
        myArrayList.add("a");

        //then
        assertEquals(myArrayList.get(0), "a");
    }

    @Test
    void shouldAddElement_whenCapacityIsReached() {
        //given
        var myArrayList = new MyArrayList<String>(3);
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.add("d");

        //then
        assertEquals("d", myArrayList.get(3));
        assertEquals(4, myArrayList.size());
    }

    @Test
    void shouldAddNullElement() {
        //given
        var myArrayList = new MyArrayList<String>();

        //when
        myArrayList.add(null);

        //then
        assertNull(myArrayList.get(0));
    }

    @Test
    void shouldAddDuplicatedElement(){
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");

        //when
        myArrayList.add("a");

        //then
        assertEquals("a", myArrayList.get(0));
        assertEquals("a", myArrayList.get(1));
        assertEquals(2, myArrayList.size());
    }

    @Test
    void shouldAdd1000Elements(){
        //given
        var myArrayList = new MyArrayList<String>();

        //when
        for (int i = 0; i < 1000; i++) {
            myArrayList.add("a" + i);
        }

        //then
        assertEquals(1000, myArrayList.size());
    }

    @Test
    void shouldRemoveElementFromStartOfList() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        myArrayList.add("d");

        //when
        myArrayList.remove("a");

        //then
        assertEquals(3, myArrayList.size());
        assertEquals("b", myArrayList.get(0));
        assertEquals("c", myArrayList.get(1));
        assertEquals("d", myArrayList.get(2));
    }

    @Test
    void shouldRemoveElementFromMiddle() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        myArrayList.add("d");
        myArrayList.add("e");

        //when
        myArrayList.remove("c");

        //then
        assertEquals(4, myArrayList.size());
        assertEquals("a", myArrayList.get(0));
        assertEquals("b", myArrayList.get(1));
        assertEquals("d", myArrayList.get(2));
        assertEquals("e", myArrayList.get(3));
    }

    @Test
    void shouldRemoveLastElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        myArrayList.add("d");

        //when
        myArrayList.remove("d");

        //then
        assertEquals(3, myArrayList.size());
        assertEquals("a", myArrayList.get(0));
        assertEquals("b", myArrayList.get(1));
        assertEquals("c", myArrayList.get(2));
    }

    @Test
    void shouldRemoveNullElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add(null);
        myArrayList.add("d");
        myArrayList.add("e");

        //when
        myArrayList.remove(null);

        //then
        assertEquals(4, myArrayList.size());
        assertEquals("a", myArrayList.get(0));
        assertEquals("b", myArrayList.get(1));
        assertEquals("d", myArrayList.get(2));
        assertEquals("e", myArrayList.get(3));
    }

    @Test
    void shouldRemoveElementThatDoesntExist() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        myArrayList.add("d");

        //when
        var isRemoved = myArrayList.remove("e");

        //then
        assertEquals(4, myArrayList.size());
        assertFalse(isRemoved);
        assertEquals("a", myArrayList.get(0));
        assertEquals("b", myArrayList.get(1));
        assertEquals("c", myArrayList.get(2));
        assertEquals("d", myArrayList.get(3));
    }

    @Test
    void shouldRemoveElement_whenListContainsDuplicates() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("b");
        myArrayList.add("a");

        //when
        myArrayList.remove("a");

        //then
        assertEquals(3, myArrayList.size());
        assertEquals("b", myArrayList.get(0));
        assertEquals("b", myArrayList.get(1));
        assertEquals("a", myArrayList.get(2));
    }

    @Test
    void shouldReturnCorrectLisSize_whenListIsEmpty() {
        //given
        var myArrayList = new MyArrayList<String>();

        //when
        var size = myArrayList.size();

        //then
        assertEquals(0, size);
    }

    @Test
    void shouldReturnCorrectLisSize_whenListContainsOneElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");

        //when
        var size = myArrayList.size();

        //then
        assertEquals(1, size);
    }

    @Test
    void shouldReturnCorrectLisSize_whenListContainsSomeElements() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        myArrayList.add("d");

        //when
        var size = myArrayList.size();

        //then
        assertEquals(4, size);
    }

    @Test
    void shouldReturnCorrectLisSize_whenElementIsRemoved() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        myArrayList.add("d");

        //when
        myArrayList.remove("a");
        var size = myArrayList.size();

        //then
        assertEquals(3, size);
    }

    @Test
    void shouldReturnCorrectLisSize_whenListIsCleared() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        myArrayList.add("d");

        //when
        myArrayList.clear();
        var size = myArrayList.size();

        //then
        assertEquals(0, size);
    }

    @Test
    void shouldBeEmpty_whenListIsEmpty() {
        //when
        var myArrayList = new MyArrayList<String>();

        //then
        assertTrue(myArrayList.isEmpty());
    }

    @Test
    void shouldNotBeEmpty_whenOneElementIsAdded() {
        //given
        var myArrayList = new MyArrayList<String>();

        //when
        myArrayList.add("a");

        //then
        assertFalse(myArrayList.isEmpty());
    }

    @Test
    void shouldNotBeEmpty_whenSomeElementsAreAdded() {
        //given
        var myArrayList = new MyArrayList<String>();

        //when
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertFalse(myArrayList.isEmpty());
    }

    @Test
    void shouldNotBeEmpty_whenListIsCleared() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.clear();

        //then
        assertTrue(myArrayList.isEmpty());
    }

    @Test
    void shouldAddAllElements_whenListIsEmpty() {
        //given
        var myArrayList = new MyArrayList<String>();

        //when
        myArrayList.addAll(List.of("a", "b", "c"));

        //then
        assertEquals(3, myArrayList.size());
        assertEquals("a", myArrayList.get(0));
        assertEquals("b", myArrayList.get(1));
        assertEquals("c", myArrayList.get(2));
    }

    @Test
    void shouldAddAllElements_whenListContainsElements() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.addAll(List.of("d", "e", "f"));

        //then
        assertEquals(6, myArrayList.size());
        assertEquals("a", myArrayList.get(0));
        assertEquals("b", myArrayList.get(1));
        assertEquals("c", myArrayList.get(2));
        assertEquals("d", myArrayList.get(3));
        assertEquals("e", myArrayList.get(4));
        assertEquals("f", myArrayList.get(5));
    }

    @Test
    void shouldAddNoneElements_whenAddingEmptyCollection() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.addAll(List.of());

        //then
        assertEquals(3, myArrayList.size());
        assertEquals("a", myArrayList.get(0));
        assertEquals("b", myArrayList.get(1));
        assertEquals("c", myArrayList.get(2));
    }

    @Test
    void shouldAddNullElements_whenAddingCollectionContainingNull() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        var targetList = new ArrayList<String>();
        targetList.add(null);

        //when
        myArrayList.addAll(targetList);

        //then
        assertEquals(4, myArrayList.size());
        assertEquals("a", myArrayList.get(0));
        assertEquals("b", myArrayList.get(1));
        assertEquals("c", myArrayList.get(2));
        assertNull(myArrayList.get(3));
    }

    @Test
    void shouldReturnTrue_whenListContainsElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        var listContainsElement = myArrayList.contains("b");

        //then
        assertTrue(listContainsElement);
    }

    @Test
    void shouldReturnFalse_whenListDoesNotContainElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        var listContainsElement = myArrayList.contains("e");

        //then
        assertFalse(listContainsElement);
    }

    @Test
    void shouldReturnTrue_whenListContainsNull() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add(null);
        myArrayList.add("c");

        //when
        var listContainsElement = myArrayList.contains(null);

        //then
        assertTrue(listContainsElement);
    }

    @Test
    void shouldReturnTrue_whenListContainsElementWithDuplicate() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("a");

        //when
        var listContainsElement = myArrayList.contains("a");

        //then
        assertTrue(listContainsElement);
    }

    @Test
    void shouldClearList_whenListIsEmpty() {
        //given
        var myArrayList = new MyArrayList<String>();

        //when
        myArrayList.clear();

        //then
        assertEquals(0, myArrayList.size());
    }

    @Test
    void shouldClearList_whenListContainsOneElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");

        //when
        myArrayList.clear();

        //then
        assertEquals(0, myArrayList.size());
        assertFalse(myArrayList.contains("a"));
    }

    @Test
    void shouldClearList_whenListContainsSomeElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.clear();

        //then
        assertEquals(0, myArrayList.size());
        assertFalse(myArrayList.contains("a"));
        assertFalse(myArrayList.contains("b"));
        assertFalse(myArrayList.contains("c"));
    }

    @Test
    void shouldAddElementAtFirstIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.add(0, "d");

        //then
        assertEquals(4, myArrayList.size());
        assertEquals("d", myArrayList.get(0));
        assertEquals("a", myArrayList.get(1));
        assertEquals("b", myArrayList.get(2));
        assertEquals("c", myArrayList.get(3));
    }

    @Test
    void shouldAddElementAtMiddleIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.add(1, "d");

        //then
        assertEquals(4, myArrayList.size());
        assertEquals("a", myArrayList.get(0));
        assertEquals("d", myArrayList.get(1));
        assertEquals("b", myArrayList.get(2));
        assertEquals("c", myArrayList.get(3));
    }

    @Test
    void shouldAddElementAtLastIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.add(myArrayList.size() - 1, "d");

        //then
        assertEquals(4, myArrayList.size());
        assertEquals("a", myArrayList.get(0));
        assertEquals("b", myArrayList.get(1));
        assertEquals("d", myArrayList.get(2));
        assertEquals("c", myArrayList.get(3));
    }

    @Test()
    void shouldNotAddItemAndThrowException_whenIndexIsNegative() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.add(-1, "d"));
    }

    @Test()
    void shouldNotAddItemAndThrowException_whenIndexIsMoreThanListSize() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.add(3, "d"));
    }

    @Test
    void shouldRemoveElementAtFirstIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.remove(0);

        //then
        assertEquals(2, myArrayList.size());
        assertEquals("b", myArrayList.get(0));
        assertEquals("c", myArrayList.get(1));
    }

    @Test
    void shouldRemoveElementAtMiddleIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.remove(1);

        //then
        assertEquals(2, myArrayList.size());
        assertEquals("a", myArrayList.get(0));
        assertEquals("c", myArrayList.get(1));
    }

    @Test
    void shouldRemoveElementAtLastIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.remove(2);

        //then
        assertEquals(2, myArrayList.size());
        assertEquals("a", myArrayList.get(0));
        assertEquals("b", myArrayList.get(1));
    }

    @Test
    void shouldThrowException_whenRemovingElementAtNegativeIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.remove(-1));
    }

    @Test
    void shouldThrowException_whenRemovingElementAtIndexHigherThanListSize() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.remove(3));
    }

    @Test
    void shouldGetElementAtFirstIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertEquals("a", myArrayList.get(0));
    }

    @Test
    void shouldGetElementAtMiddleIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertEquals("b", myArrayList.get(1));
    }

    @Test
    void shouldGetElementAtLastIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertEquals("c", myArrayList.get(2));
    }

    @Test
    void shouldThrowException_whenGetElementOnNegativeIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.remove(-1));
    }

    @Test
    void shouldThrowException_whenGetElementOnNullIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.remove(-1));
    }

    @Test
    void shouldSetElementAtFirstIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.set(0, "d");

        //then
        assertEquals("d", myArrayList.get(0));
    }

    @Test
    void shouldSetElementAtMiddleIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.set(1, "d");

        //then
        assertEquals("d", myArrayList.get(1));
    }

    @Test
    void shouldThrowException_whenSettingElementAtOutOfBoundsIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.set(3, "d"));
    }

    @Test
    void shouldSetNullAtGivenIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        myArrayList.set(1, null);

        //then
        assertNull(myArrayList.get(1));
    }

    @Test
    void shouldReturnElementIndex() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertEquals(1, myArrayList.indexOf("b"));
    }

    @Test
    void shouldReturnNegativeOneAsIndex_whenListDoesNotContainElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertEquals(-1, myArrayList.indexOf("d"));
    }

    @Test
    void shouldReturnIndexOfNullElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add(null);
        myArrayList.add("c");

        //then
        assertEquals(1, myArrayList.indexOf(null));
    }

    @Test
    void shouldReturnIndexOfFirstElement_whenElementIsDuplicatedInList() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("a");
        myArrayList.add("c");

        //then
        assertEquals(0, myArrayList.indexOf("a"));
    }

    @Test
    void shouldReturnLastIndexOfElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertEquals(0, myArrayList.lastIndexOf("a"));
    }

    @Test
    void shouldReturnNegativeOneAsLastIndexOfElement_whenListDoesNotContainElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //then
        assertEquals(-1, myArrayList.lastIndexOf("d"));
    }

    @Test
    void shouldReturnLastIndexOfNullElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add(null);
        myArrayList.add("b");
        myArrayList.add(null);

        //then
        assertEquals(2, myArrayList.lastIndexOf(null));
    }

    @Test
    void shouldReturnLastIndexOfElement_whenElementIsDuplicatedInList() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("a");

        //then
        assertEquals(2, myArrayList.lastIndexOf("a"));
    }

    @Test
    void shouldReturnIterator() {
        //given
        var myArrayList = new MyArrayList<String>();

        //when
        Iterator<String> iterator = myArrayList.iterator();

        //then
        assertNotNull(iterator);
    }

    @Test
    void shouldReturnTrue_whenNextElementExist() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        Iterator<String> iterator = myArrayList.iterator();

        //then
        assertTrue(iterator.hasNext());
    }

    @Test
    void shouldReturnFalse_whenNextElementNotExist() {
        //given
        var myArrayList = new MyArrayList<String>();

        //when
        Iterator<String> iterator = myArrayList.iterator();

        //then
        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldReturnNextElement() {
        //given
        var myArrayList = new MyArrayList<String>();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        Iterator<String> iterator = myArrayList.iterator();

        //then
        assertEquals("a", iterator.next());
        assertEquals("b", iterator.next());
        assertEquals("c", iterator.next());
    }

    @Test
    void shouldThrowException_whenNextElementNotExist() {
        //given
        var myArrayList = new MyArrayList<String>();
        Iterator<String> iterator = myArrayList.iterator();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");

        //when
        iterator.next();
        iterator.next();
        iterator.next();

        //then
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }


}