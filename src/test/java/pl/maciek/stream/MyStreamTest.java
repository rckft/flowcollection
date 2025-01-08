package pl.maciek.stream;

import org.junit.jupiter.api.Test;
import pl.maciek.collection.list.MyList;

import java.io.*;

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




}
