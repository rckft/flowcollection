package pl.maciek.stream;

import org.junit.jupiter.api.Test;
import pl.maciek.collection.list.MyList;

import java.io.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MyStreamTest {


    @Test
    void shouldFilterOddNumbers() {
        // given
        MyList<Integer> numbers = MyList.of(1, 2, 3, 4, 5);
        MyStream<Integer> stream = new MyStream<>(numbers);

        //when
        MyList<Integer> evenNumbers = stream.filter(n -> n % 2 == 0).toList();

        //then
        assertTrue(evenNumbers.contains(2));
        assertTrue(evenNumbers.contains(4));
        assertFalse(evenNumbers.contains(1));
        assertFalse(evenNumbers.contains(3));
        assertFalse(evenNumbers.contains(5));
    }

    @Test
    void shouldMapNamesToUpperCase() {
        //given
        MyList<String> names = MyList.of("Anna", "Bartek", "Cecylia");
        MyStream<String> stream = new MyStream<>(names);

        //when
        MyList<String> upperNames = stream.map(String::toUpperCase).toList();

        //then
        assertEquals("ANNA", upperNames.get(0));
        assertEquals("BARTEK", upperNames.get(1));
        assertEquals("CECYLIA", upperNames.get(2));
    }

    @Test
    void systemOutStream_shouldContainCorrectData() throws IOException {
        PrintStream originalOut = System.out;
        try(var out = new ByteArrayOutputStream()) {
            //given
            System.setOut(new PrintStream(out));
            var separator = System.lineSeparator();
            var list = MyList.of("Anna", "Bartek", "Cecylia", "Dan");

            //when
            var stream = new MyStream<>(list);

            stream.filter(name -> name.length() > 4)
                    .map(String::toUpperCase)
                    .forEach(System.out::println);

            //then
            assertEquals("BARTEK" + separator + "CECYLIA" + separator, out.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldCountElements() {
        //given
        MyList<String> names = MyList.of("Anna", "Bartek", "Cecylia");
        MyStream<String> stream = new MyStream<>(names);

        //when
        long count = stream.count();

        //then
        assertEquals(3, count);
    }

    @Test
    void shouldReturnDistinctElements() {
        //given
        MyList<Integer> names = MyList.of(1, 3, 2, 2, 4, 1);
        MyStream<Integer> stream = new MyStream<>(names);

        //when
        MyList<Integer> distinct = stream.distinct().toList();

        //then
        assertEquals(4, distinct.size());
        assertEquals(1, distinct.get(0));
        assertEquals(3, distinct.get(1));
        assertEquals(2, distinct.get(2));
        assertEquals(4, distinct.get(3));
    }

    @Test
    void shouldLimitElements() {
        //given
        MyList<String> names = MyList.of("Anna", "Bartek", "Cecylia", "Daniel");
        MyStream<String> stream = new MyStream<>(names);

        //when
        MyList<String> limited = stream.limit(2).toList();

        //then
        assertEquals(2, limited.size());
        assertEquals("Anna", limited.get(0));
        assertEquals("Bartek", limited.get(1));
    }

    @Test
    void shouldCountDistinctElements_limitedToThree() {
        //given
        MyList<Integer> integers = MyList.of(1, 2, 2, 3, 3, 3, 4);
        MyStream<Integer> stream = new MyStream<>(integers);

        //when
        var count = stream.distinct().limit(3).count();

        //then
        assertEquals(3, count);
    }

    @Test
    void shouldSkipElements() {
        //given
        MyList<String> names = MyList.of("Anna", "Bartek", "Cecylia", "Daniel");
        MyStream<String> stream = new MyStream<>(names);

        //when
        MyList<String> limited = stream.skip(2).toList();

        //then
        assertEquals(2, limited.size());
        assertEquals("Cecylia", limited.get(0));
        assertEquals("Daniel", limited.get(1));
    }

    @Test
    void shouldSortElements() {
        //given
        MyList<String> names = MyList.of("Daniel", "Anna", "Bartek", "Cecylia");
        MyStream<String> stream = new MyStream<>(names);

        //when
        MyList<String> sorted = stream.sorted(String::compareTo).toList();

        //then
        assertEquals(4, sorted.size());
        assertEquals("Anna", sorted.get(0));
        assertEquals("Bartek", sorted.get(1));
        assertEquals("Cecylia", sorted.get(2));
        assertEquals("Daniel", sorted.get(3));
    }

    @Test
    void systemOutStream_shouldContainCorrectSortedData() throws IOException {
        PrintStream originalOut = System.out;
        try(var out = new ByteArrayOutputStream()) {
            //given
            System.setOut(new PrintStream(out));
            var separator = System.lineSeparator();
            var list = MyList.of("Daniel", "Anna", "Bartek", "Cecylia");

            //when
            var stream = new MyStream<>(list);

            stream.sorted(String::compareTo)
                    .skip(2)
                    .forEach(System.out::println);

            //then
            assertEquals("Cecylia" + separator + "Daniel" + separator, out.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldReturnFirstElementOfStream() {
        //given
        var integers = MyList.of(1, 2, 3, 4, 5);
        MyStream<Integer> stream = new MyStream<>(integers);

        //when
        var first = stream.findFirst();

        //then
        assertTrue(first.isPresent());
        assertEquals(1, first.get());
    }

    @Test
    void shouldReturnCorrectFirstElement_filteringGreaterThan10_andAdding5() {
        //given
        var integers = MyList.of(5, 8, 12, 15, 22);
        MyStream<Integer> stream = new MyStream<>(integers);

        //when
        Optional<Integer> first = stream.filter(i -> i > 10).map(i -> i + 5).findFirst();

        //then
        assertTrue(first.isPresent());
        assertEquals(17, first.get());
    }

    @Test
    void shouldReturnCorrectFirstElement_sortingElements_andSkipping3Elements() {
        //given
        var names = MyList.of("Daniel", "Anna", "Bartek", "Cecylia", "Ela");
        MyStream<String> stream = new MyStream<>(names);

        //when
        Optional<String> first = stream.sorted(String::compareTo).skip(3).findFirst();

        //then
        assertTrue(first.isPresent());
        assertEquals("Daniel", first.get());
    }

    @Test
    void shouldReturnCorrectCount_ofElementsGreaterThan50_afterAdding20() {
        //given
        var integers = MyList.of(10, 30, 40, 60, 80);
        MyStream<Integer> stream = new MyStream<>(integers);

        //when
        var count = stream.map(i -> i + 20).filter(i -> i > 50).count();

        //then
        assertEquals(3, count);
    }

    @Test
    void shouldReturnCorrectCount_ofUpperCaseElements_beginningWithA() {
        //given
        var names = MyList.of("Anna", "Agnieszka", "Bartek", "Cecylia", "Adam");
        MyStream<String> stream = new MyStream<>(names);

        //when
        var count = stream.filter(s -> s.startsWith("A")).map(String::toUpperCase).count();

        //then
        assertEquals(3, count);
    }

    @Test
    void shouldReturnTrue_whenAllElementsMatchPredicate() {
        //given
        var integers = MyList.of(2, 4, 6, 8);
        MyStream<Integer> stream = new MyStream<>(integers);

        //then
        assertTrue(stream.allMatch(n -> n % 2 == 0));
    }

    @Test
    void shouldReturnFalse_whenSomeElementsDoesNotMatchPredicate() {
        //given
        var integers = MyList.of(2, 4, 7, 8);
        MyStream<Integer> stream = new MyStream<>(integers);

        //then
        assertFalse(stream.allMatch(n -> n % 2 == 0));
    }

    @Test
    void shouldReturnTrue_whenAnyElementMatchPredicate() {
        //given
        var integers = MyList.of(1, 3, 4, 5);
        MyStream<Integer> stream = new MyStream<>(integers);

        //then
        assertTrue(stream.anyMatch(n -> n % 2 == 0));
    }

    @Test
    void shouldReturnFalse_whenNoneElementsMatchPredicate() {
        //given
        var integers = MyList.of(1, 3, 5, 7);
        MyStream<Integer> stream = new MyStream<>(integers);

        //then
        assertFalse(stream.anyMatch(n -> n % 2 == 0));
    }

    @Test
    void shouldReturnCorrectResults_whenAnyElementMatchPredicate() {
        //given
        var integers = MyList.of(2, 4, 6, 9, 12);
        MyStream<Integer> stream = new MyStream<>(integers);

        //then
        assertTrue(stream.anyMatch(n -> n > 10));
        assertFalse(stream.allMatch(n -> n > 10));
    }

    @Test
    void shouldReturnTrue_whenNoneElementsMatchPredicate() {
        //given
        var integers = MyList.of(1, 3, 5, 7);
        MyStream<Integer> stream = new MyStream<>(integers);

        //then
        assertTrue(stream.noneMatch(n -> n % 2 == 0));
    }

    @Test
    void shouldReturnFalse_whenAnyElementMatchPredicate() {
        //given
        var integers = MyList.of(1, 2, 5, 7);
        MyStream<Integer> stream = new MyStream<>(integers);

        //then
        assertFalse(stream.noneMatch(n -> n % 2 == 0));
    }

    @Test
    void shouldReduceIntegersToSum() {
        //given
        var integers = MyList.of(1, 2, 3, 4);
        MyStream<Integer> stream = new MyStream<>(integers);

        //then
        assertEquals(10, stream.reduce(0, Integer::sum));
    }

    @Test
    void shouldReturnSumOfElementsGreaterThan10_andAssertThatNoElementIsLessThan5() {
        //given
        var integers = MyList.of(12, 15, 8, 22);
        MyStream<Integer> stream = new MyStream<>(integers);

        //when
        var reduced = stream.reduce(0, (acc, i) -> (i > 10) ? acc + i : acc);

        //then
        assertEquals(49, reduced);
        assertTrue(stream.noneMatch(i -> i < 5));
    }

    @Test
    void shouldReturnMinElement() {
        //given
        var integers = MyList.of(4, 2, 6, 1);
        MyStream<Integer> stream = new MyStream<>(integers);

        //when
        var min = stream.min(Integer::compareTo);

        //then
        assertTrue(min.isPresent());
        assertEquals(1, min.get());
    }

    @Test
    void shouldReturnMaxElement() {
        //given
        var integers = MyList.of(4, 2, 6, 1);
        MyStream<Integer> stream = new MyStream<>(integers);

        //when
        var max = stream.max(Integer::compareTo);

        //then
        assertTrue(max.isPresent());
        assertEquals(6, max.get());
    }

    @Test
    void shouldReturnCorrectMinMaxAndSum() {
        //given
        var integers = MyList.of(3, 7, 2, 9, 1);
        MyStream<Integer> stream = new MyStream<>(integers);

        //when
        var min = stream.min(Integer::compareTo);
        var max = stream.max(Integer::compareTo);
        var sum = stream.reduce(0, Integer::sum);

        //then
        assertTrue(min.isPresent());
        assertTrue(max.isPresent());
        assertEquals(1, min.get());
        assertEquals(9, max.get());
        assertEquals(22, sum);
    }

    @Test
    void systemOutStream_shouldContainCorrectData_andResultShouldContainElements() throws IOException {
        PrintStream originalOut = System.out;
        try(var out = new ByteArrayOutputStream()) {
            //given
            System.setOut(new PrintStream(out));
            var separator = System.lineSeparator();
            var list = MyList.of("Anna", "Bartek", "Cecylia");
            var stream = new MyStream<>(list);

            //when
            var result = stream.peek(System.out::println).toList();

            //then
            assertEquals("Anna" + separator + "Bartek" + separator + "Cecylia" + separator, out.toString());
            assertEquals("Anna", result.get(0));
            assertEquals("Bartek", result.get(1));
            assertEquals("Cecylia", result.get(2));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldReturnFlattenedList() {
        //given
        var numbersLists = MyList.of(MyList.of(1, 2), MyList.of(3, 4));
        var stream = new MyStream<>(numbersLists);

        //when
        var flattened = stream.flatMap(list -> new MyStream<>(list)).toList();

        //then
        assertEquals(4, flattened.size());
        assertEquals(1, flattened.get(0));
        assertEquals(2, flattened.get(1));
        assertEquals(3, flattened.get(2));
        assertEquals(4, flattened.get(3));
    }

    @Test
    void systemOutStream_shouldContainCorrectData_andResultShouldBeFlattened() throws IOException {
        PrintStream originalOut = System.out;
        try(var out = new ByteArrayOutputStream()) {
            //given
            System.setOut(new PrintStream(out));
            var separator = System.lineSeparator();
            var listOfNumbers = MyList.of(MyList.of(1, 2), MyList.of(3, 4), MyList.of(5, 6));
            var stream = new MyStream<>(listOfNumbers);

            //when
            var result = stream.flatMap(list -> new MyStream<>(list)).peek(System.out::println).toList();

            //then
            assertEquals("1" + separator + "2" + separator + "3" + separator + "4" + separator + "5" +
                    separator + "6" + separator, out.toString());
            assertEquals(1, result.get(0));
            assertEquals(2, result.get(1));
            assertEquals(3, result.get(2));
            assertEquals(4, result.get(3));
            assertEquals(5, result.get(4));
            assertEquals(6, result.get(5));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldReturnJoinedStringsWithDelimiter() throws IOException {
        PrintStream originalOut = System.out;
        try(var out = new ByteArrayOutputStream()) {
            //given
            System.setOut(new PrintStream(out));
            var separator = System.lineSeparator();
            var names = MyList.of("Anna", "Bartek", "Cecylia");
            var stream = new MyStream<>(names);

            //when
            System.out.println(stream.join(", "));

            //then
            assertEquals("Anna, Bartek, Cecylia" + separator, out.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldContainPositiveNumbersNotGreaterThan100() {
        //given
        var integers = MyList.of(12, 50, 70, 33);
        MyStream<Integer> stream = new MyStream<>(integers);

        //then
        assertTrue(stream.allMatch(number -> number > 0));
        assertTrue(stream.noneMatch(number -> number >= 100));
    }

    @Test
    void shouldReturnJoinedUpperCaseNamesStartingWithA() throws IOException {
        PrintStream originalOut = System.out;
        try(var out = new ByteArrayOutputStream()) {
            //given
            System.setOut(new PrintStream(out));
            var separator = System.lineSeparator();
            var names = MyList.of("Anna", "Agnieszka", "Bartek", "Cecylia", "Adam");
            var stream = new MyStream<>(names);

            //when
            System.out.println(stream.filter(name -> name.startsWith("A")).map(String::toUpperCase).join(", "));

            //then
            assertEquals("ANNA, AGNIESZKA, ADAM" + separator, out.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldReturnSumOfElements_andCheckIfSumIsLessThan50() {
        //given
        var integers = MyList.of(10, 15, 20);
        MyStream<Integer> stream = new MyStream<>(integers);

        //when
        var sum = stream.reduce(0, Integer::sum);

        //then
        assertEquals(45, sum);
        assertTrue(new MyStream<>(MyList.of(sum)).noneMatch(number -> number >= 50));
    }

    @Test
    void shouldReturnMinAndMaxElements() {
        //given
        var integers = MyList.of(3, 7, 9, 2, 8);
        MyStream<Integer> stream = new MyStream<>(integers);

        //when
        var min = stream.min(Integer::compareTo);
        var max = stream.max(Integer::compareTo);

        //then
        assertTrue(min.isPresent());
        assertTrue(max.isPresent());
        assertEquals(2, min.get());
        assertEquals(9, max.get());
    }

    @Test
    void systemOutStream_shouldContainCorrectData_andReturnSumOfNumbers() throws IOException {
        PrintStream originalOut = System.out;
        try(var out = new ByteArrayOutputStream()) {
            //given
            System.setOut(new PrintStream(out));
            var separator = System.lineSeparator();
            var listOfNumbers = MyList.of(MyList.of(1, 2), MyList.of(3, 4), MyList.of(5, 6));
            var stream = new MyStream<>(listOfNumbers);

            //when
            var sum = stream.flatMap(list -> new MyStream<>(list)).peek(System.out::println).reduce(0, Integer::sum);

            //then
            assertEquals("1" + separator + "2" + separator + "3" + separator + "4" + separator + "5" +
                    separator + "6" + separator, out.toString());
            assertEquals(21, sum);
        } finally {
            System.setOut(originalOut);
        }
    }

}
