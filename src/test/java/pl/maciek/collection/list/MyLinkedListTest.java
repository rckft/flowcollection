package pl.maciek.collection.list;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    @Test
    void shouldAddElement_toEmptyList() {
        //given
        var myLinkedList = new MyLinkedList<String>();

        //when
        myLinkedList.add("a");

        //then
        assertEquals(myLinkedList.get(0), "a");
    }

    @Test
    void shouldAddNullElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();

        //when
        myLinkedList.add(null);

        //then
        assertNull(myLinkedList.get(0));
    }

    @Test
    void shouldAddDuplicatedElement(){
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");

        //when
        myLinkedList.add("a");

        //then
        assertEquals("a", myLinkedList.get(0));
        assertEquals("a", myLinkedList.get(1));
        assertEquals(2, myLinkedList.size());
    }

    @Test
    void shouldAdd1000Elements(){
        //given
        var myLinkedList = new MyLinkedList<String>();

        //when
        for (int i = 0; i < 1000; i++) {
            myLinkedList.add("a" + i);
        }

        //then
        assertEquals(1000, myLinkedList.size());
    }

    @Test
    void shouldRemoveElementFromStartOfList() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");
        myLinkedList.add("d");

        //when
        myLinkedList.remove("a");

        //then
        assertEquals(3, myLinkedList.size());
        assertEquals("b", myLinkedList.get(0));
        assertEquals("c", myLinkedList.get(1));
        assertEquals("d", myLinkedList.get(2));
    }

    @Test
    void shouldRemoveElementFromMiddle() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");
        myLinkedList.add("d");
        myLinkedList.add("e");

        //when
        myLinkedList.remove("c");

        //then
        assertEquals(4, myLinkedList.size());
        assertEquals("a", myLinkedList.get(0));
        assertEquals("b", myLinkedList.get(1));
        assertEquals("d", myLinkedList.get(2));
        assertEquals("e", myLinkedList.get(3));
    }

    @Test
    void shouldRemoveLastElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");
        myLinkedList.add("d");

        //when
        myLinkedList.remove("d");

        //then
        assertEquals(3, myLinkedList.size());
        assertEquals("a", myLinkedList.get(0));
        assertEquals("b", myLinkedList.get(1));
        assertEquals("c", myLinkedList.get(2));
    }

    @Test
    void shouldRemoveNullElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add(null);
        myLinkedList.add("d");
        myLinkedList.add("e");

        //when
        myLinkedList.remove(null);

        //then
        assertEquals(4, myLinkedList.size());
        assertEquals("a", myLinkedList.get(0));
        assertEquals("b", myLinkedList.get(1));
        assertEquals("d", myLinkedList.get(2));
        assertEquals("e", myLinkedList.get(3));
    }

    @Test
    void shouldRemoveElementThatDoesntExist() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");
        myLinkedList.add("d");

        //when
        var isRemoved = myLinkedList.remove("e");

        //then
        assertEquals(4, myLinkedList.size());
        assertFalse(isRemoved);
        assertEquals("a", myLinkedList.get(0));
        assertEquals("b", myLinkedList.get(1));
        assertEquals("c", myLinkedList.get(2));
        assertEquals("d", myLinkedList.get(3));
    }

    @Test
    void shouldRemoveElement_whenListContainsDuplicates() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("b");
        myLinkedList.add("a");

        //when
        myLinkedList.remove("a");

        //then
        assertEquals(3, myLinkedList.size());
        assertEquals("b", myLinkedList.get(0));
        assertEquals("b", myLinkedList.get(1));
        assertEquals("a", myLinkedList.get(2));
    }

    @Test
    void shouldReturnCorrectLisSize_whenListIsEmpty() {
        //given
        var myLinkedList = new MyLinkedList<String>();

        //when
        var size = myLinkedList.size();

        //then
        assertEquals(0, size);
    }

    @Test
    void shouldReturnCorrectLisSize_whenListContainsOneElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");

        //when
        var size = myLinkedList.size();

        //then
        assertEquals(1, size);
    }

    @Test
    void shouldReturnCorrectLisSize_whenListContainsSomeElements() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");
        myLinkedList.add("d");

        //when
        var size = myLinkedList.size();

        //then
        assertEquals(4, size);
    }

    @Test
    void shouldReturnCorrectLisSize_whenElementIsRemoved() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");
        myLinkedList.add("d");

        //when
        myLinkedList.remove("a");
        var size = myLinkedList.size();

        //then
        assertEquals(3, size);
    }

    @Test
    void shouldReturnCorrectLisSize_whenListIsCleared() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");
        myLinkedList.add("d");

        //when
        myLinkedList.clear();
        var size = myLinkedList.size();

        //then
        assertEquals(0, size);
    }

    @Test
    void shouldBeEmpty_whenListIsEmpty() {
        //when
        var myLinkedList = new MyLinkedList<String>();

        //then
        assertTrue(myLinkedList.isEmpty());
    }

    @Test
    void shouldNotBeEmpty_whenOneElementIsAdded() {
        //given
        var myLinkedList = new MyLinkedList<String>();

        //when
        myLinkedList.add("a");

        //then
        assertFalse(myLinkedList.isEmpty());
    }

    @Test
    void shouldNotBeEmpty_whenSomeElementsAreAdded() {
        //given
        var myLinkedList = new MyLinkedList<String>();

        //when
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertFalse(myLinkedList.isEmpty());
    }

    @Test
    void shouldBeEmpty_whenListIsCleared() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.clear();

        //then
        assertTrue(myLinkedList.isEmpty());
    }

    @Test
    void shouldAddAllElements_whenListIsEmpty() {
        //given
        var myLinkedList = new MyLinkedList<String>();

        //when
        myLinkedList.addAll(List.of("a", "b", "c"));

        //then
        assertEquals(3, myLinkedList.size());
        assertEquals("a", myLinkedList.get(0));
        assertEquals("b", myLinkedList.get(1));
        assertEquals("c", myLinkedList.get(2));
    }

    @Test
    void shouldAddAllElements_whenListContainsElements() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.addAll(List.of("d", "e", "f"));

        //then
        assertEquals(6, myLinkedList.size());
        assertEquals("a", myLinkedList.get(0));
        assertEquals("b", myLinkedList.get(1));
        assertEquals("c", myLinkedList.get(2));
        assertEquals("d", myLinkedList.get(3));
        assertEquals("e", myLinkedList.get(4));
        assertEquals("f", myLinkedList.get(5));
    }

    @Test
    void shouldAddNoneElements_whenAddingEmptyCollection() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.addAll(List.of());

        //then
        assertEquals(3, myLinkedList.size());
        assertEquals("a", myLinkedList.get(0));
        assertEquals("b", myLinkedList.get(1));
        assertEquals("c", myLinkedList.get(2));
    }

    @Test
    void shouldAddNullElements_whenAddingCollectionContainingNull() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");
        var targetList = new ArrayList<String>();
        targetList.add(null);

        //when
        myLinkedList.addAll(targetList);

        //then
        assertEquals(4, myLinkedList.size());
        assertEquals("a", myLinkedList.get(0));
        assertEquals("b", myLinkedList.get(1));
        assertEquals("c", myLinkedList.get(2));
        assertNull(myLinkedList.get(3));
    }

    @Test
    void shouldReturnTrue_whenListContainsElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        var listContainsElement = myLinkedList.contains("b");

        //then
        assertTrue(listContainsElement);
    }

    @Test
    void shouldReturnFalse_whenListDoesNotContainElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        var listContainsElement = myLinkedList.contains("e");

        //then
        assertFalse(listContainsElement);
    }

    @Test
    void shouldReturnTrue_whenListContainsNull() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add(null);
        myLinkedList.add("c");

        //when
        var listContainsElement = myLinkedList.contains(null);

        //then
        assertTrue(listContainsElement);
    }

    @Test
    void shouldReturnTrue_whenListContainsElementWithDuplicate() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("a");

        //when
        var listContainsElement = myLinkedList.contains("a");

        //then
        assertTrue(listContainsElement);
    }

    @Test
    void shouldClearList_whenListIsEmpty() {
        //given
        var myLinkedList = new MyLinkedList<String>();

        //when
        myLinkedList.clear();

        //then
        assertEquals(0, myLinkedList.size());
    }

    @Test
    void shouldClearList_whenListContainsOneElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");

        //when
        myLinkedList.clear();

        //then
        assertEquals(0, myLinkedList.size());
        assertFalse(myLinkedList.contains("a"));
    }

    @Test
    void shouldClearList_whenListContainsSomeElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.clear();

        //then
        assertEquals(0, myLinkedList.size());
        assertFalse(myLinkedList.contains("a"));
        assertFalse(myLinkedList.contains("b"));
        assertFalse(myLinkedList.contains("c"));
    }

    @Test
    void shouldAddElementAtFirstIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.add(0, "d");

        //then
        assertEquals(4, myLinkedList.size());
        assertEquals("d", myLinkedList.get(0));
        assertEquals("a", myLinkedList.get(1));
        assertEquals("b", myLinkedList.get(2));
        assertEquals("c", myLinkedList.get(3));
    }

    @Test
    void shouldAddElementAtMiddleIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.add(1, "d");

        //then
        assertEquals(4, myLinkedList.size());
        assertEquals("a", myLinkedList.get(0));
        assertEquals("d", myLinkedList.get(1));
        assertEquals("b", myLinkedList.get(2));
        assertEquals("c", myLinkedList.get(3));
    }

    @Test
    void shouldAddElementAtLastIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.add(myLinkedList.size() - 1, "d");

        //then
        assertEquals(4, myLinkedList.size());
        assertEquals("a", myLinkedList.get(0));
        assertEquals("b", myLinkedList.get(1));
        assertEquals("d", myLinkedList.get(2));
        assertEquals("c", myLinkedList.get(3));
    }

    @Test()
    void shouldNotAddItemAndThrowException_whenIndexIsNegative() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myLinkedList.add(-1, "d"));
    }

    @Test()
    void shouldNotAddItemAndThrowException_whenIndexIsMoreThanListSize() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myLinkedList.add(3, "d"));
    }

    @Test
    void shouldRemoveElementAtFirstIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.remove(0);

        //then
        assertEquals(2, myLinkedList.size());
        assertEquals("b", myLinkedList.get(0));
        assertEquals("c", myLinkedList.get(1));
    }

    @Test
    void shouldRemoveElementAtMiddleIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.remove(1);

        //then
        assertEquals(2, myLinkedList.size());
        assertEquals("a", myLinkedList.get(0));
        assertEquals("c", myLinkedList.get(1));
    }

    @Test
    void shouldRemoveElementAtLastIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.remove(2);

        //then
        assertEquals(2, myLinkedList.size());
        assertEquals("a", myLinkedList.get(0));
        assertEquals("b", myLinkedList.get(1));
    }

    @Test
    void shouldThrowException_whenRemovingElementAtNegativeIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myLinkedList.remove(-1));
    }

    @Test
    void shouldThrowException_whenRemovingElementAtIndexHigherThanListSize() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myLinkedList.remove(3));
    }

    @Test
    void shouldGetElementAtFirstIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertEquals("a", myLinkedList.get(0));
    }

    @Test
    void shouldGetElementAtMiddleIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertEquals("b", myLinkedList.get(1));
    }

    @Test
    void shouldGetElementAtLastIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertEquals("c", myLinkedList.get(2));
    }

    @Test
    void shouldThrowException_whenGetElementOnNegativeIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myLinkedList.remove(-1));
    }

    @Test
    void shouldThrowException_whenGetElementOnNullIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myLinkedList.remove(-1));
    }

    @Test
    void shouldSetElementAtFirstIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.set(0, "d");

        //then
        assertEquals("d", myLinkedList.get(0));
    }

    @Test
    void shouldSetElementAtMiddleIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.set(1, "d");

        //then
        assertEquals("d", myLinkedList.get(1));
    }

    @Test
    void shouldThrowException_whenSettingElementAtOutOfBoundsIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> myLinkedList.set(3, "d"));
    }

    @Test
    void shouldSetNullAtGivenIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        myLinkedList.set(1, null);

        //then
        assertNull(myLinkedList.get(1));
    }

    @Test
    void shouldReturnElementIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertEquals(1, myLinkedList.indexOf("b"));
    }

    @Test
    void shouldReturnNegativeOneAsIndex_whenListDoesNotContainElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertEquals(-1, myLinkedList.indexOf("d"));
    }

    @Test
    void shouldReturnIndexOfNullElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add(null);
        myLinkedList.add("c");

        //then
        assertEquals(1, myLinkedList.indexOf(null));
    }

    @Test
    void shouldReturnIndexOfFirstElement_whenElementIsDuplicatedInList() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("a");
        myLinkedList.add("c");

        //then
        assertEquals(0, myLinkedList.indexOf("a"));
    }

    @Test
    void shouldReturnLastIndexOfElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertEquals(0, myLinkedList.lastIndexOf("a"));
    }

    @Test
    void shouldReturnNegativeOneAsLastIndexOfElement_whenListDoesNotContainElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //then
        assertEquals(-1, myLinkedList.lastIndexOf("d"));
    }

    @Test
    void shouldReturnLastIndexOfNullElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add(null);
        myLinkedList.add("b");
        myLinkedList.add(null);

        //then
        assertEquals(2, myLinkedList.lastIndexOf(null));
    }

    @Test
    void shouldReturnLastIndexOfElement_whenElementIsDuplicatedInList() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("a");

        //then
        assertEquals(2, myLinkedList.lastIndexOf("a"));
    }

    @Test
    void shouldReturnIterator() {
        //given
        var myLinkedList = new MyLinkedList<String>();

        //when
        Iterator<String> iterator = myLinkedList.iterator();

        //then
        assertNotNull(iterator);
    }

    @Test
    void shouldReturnTrue_whenNextElementExist() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        Iterator<String> iterator = myLinkedList.iterator();

        //then
        assertTrue(iterator.hasNext());
    }

    @Test
    void shouldReturnFalse_whenNextElementNotExist() {
        //given
        var myLinkedList = new MyLinkedList<String>();

        //when
        Iterator<String> iterator = myLinkedList.iterator();

        //then
        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldReturnNextElement() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        Iterator<String> iterator = myLinkedList.iterator();

        //then
        assertEquals("a", iterator.next());
        assertEquals("b", iterator.next());
        assertEquals("c", iterator.next());
    }

    @Test
    void shouldThrowException_whenNextElementNotExist() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        Iterator<String> iterator = myLinkedList.iterator();
        iterator.next();
        iterator.next();
        iterator.next();

        //then
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    void iteratorShouldNotThrowException_whenListIsNotModified() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        var iterator = myLinkedList.iterator();

        //then
        assertDoesNotThrow(() -> iterator.next());
    }

    @Test
    void iteratorShouldThrowException_whenElementIsAdded() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        var iterator = myLinkedList.iterator();
        iterator.next();
        myLinkedList.add("d");

        //then
        assertThrows(ConcurrentModificationException.class, () -> iterator.next());
    }

    @Test
    void iteratorShouldThrowException_whenElementIsRemoved() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        var iterator = myLinkedList.iterator();
        iterator.next();
        myLinkedList.remove("c");

        //then
        assertThrows(ConcurrentModificationException.class, () -> iterator.next());
    }

    @Test
    void iteratorShouldThrowException_whenElementIsAddedAtIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        var iterator = myLinkedList.iterator();
        iterator.next();
        myLinkedList.add(1, "d");

        //then
        assertThrows(ConcurrentModificationException.class, () -> iterator.next());
    }

    @Test
    void iteratorShouldThrowException_whenElementIsRemovedAtIndex() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        var iterator = myLinkedList.iterator();
        iterator.next();
        myLinkedList.remove(1);

        //then
        assertThrows(ConcurrentModificationException.class, () -> iterator.next());
    }

    @Test
    void iteratorShouldThrowException_whenElementIsSet() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        var iterator = myLinkedList.iterator();
        iterator.next();
        myLinkedList.set(1, "d");

        //then
        assertThrows(ConcurrentModificationException.class, () -> iterator.next());
    }

    @Test
    void iteratorShouldThrowException_whenElementsAreAdded() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        var iterator = myLinkedList.iterator();
        iterator.next();
        myLinkedList.addAll(List.of("d"));

        //then
        assertThrows(ConcurrentModificationException.class, () -> iterator.next());
    }

    @Test
    void iteratorShouldThrowException_whenListIsCleared() {
        //given
        var myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        //when
        var iterator = myLinkedList.iterator();
        iterator.next();
        myLinkedList.clear();

        //then
        assertThrows(ConcurrentModificationException.class, () -> iterator.next());
    }

}