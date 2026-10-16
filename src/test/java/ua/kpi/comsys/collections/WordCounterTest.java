package ua.kpi.comsys.collections;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterTest {

    private final WordCounter wordCounter = new WordCounter();

    @Test
    void testCountWordsSuccess() {
        List<String> lines = List.of(
                "Hello world! Hello Java 25;!! Hello someone",
                "World of programming, hello-world 25!"
        );

        Map<String, Integer> counts = wordCounter.countWords(lines);

        assertEquals(4, counts.get("hello"));
        assertEquals(3, counts.get("world"));
        assertEquals(1, counts.get("java"));
        assertEquals(2, counts.get("25"));
        assertEquals(1, counts.get("someone"));
        assertEquals(1, counts.get("of"));
        assertEquals(1, counts.get("programming"));
    }

    @Test
    void testGetUniqueWordsSuccess() {
        List<String> lines = List.of(
                "Apple, Banana, APPLE!",
                "banana; cherry 123"
        );

        Set<String> uniqueWords = wordCounter.getUniqueWords(lines);

        assertEquals(Set.of("apple", "banana", "cherry", "123"), uniqueWords);
    }

    @Test
    void testCountWordsWithNullLinesAndEmptyStrings() {
        List<String> lines = java.util.Arrays.asList(
                "Valid text...",
                null,
                "",
                "   ",
                "!#!! #@#$% ^&*()"
        );

        Map<String, Integer> counts = wordCounter.countWords(lines);

        assertEquals(2, counts.size());
        assertEquals(1, counts.get("valid"));
        assertEquals(1, counts.get("text"));
    }

    @Test
    void testGetUniqueWordsWithNullLinesAndEmptyStrings() {
        List<String> lines = java.util.Arrays.asList("Valid text...", null, "", "   ");
        Set<String> uniqueWords = wordCounter.getUniqueWords(lines);
        assertEquals(Set.of("valid", "text"), uniqueWords);
    }

    @Test
    void testCountWordsEmptyList() {
        Map<String, Integer> result = wordCounter.countWords(Collections.emptyList());
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetUniqueWordsEmptyList() {
        Set<String> result = wordCounter.getUniqueWords(Collections.emptyList());
        assertTrue(result.isEmpty());
    }

    @Test
    void testCountWordsNullInputThrowsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> wordCounter.countWords(null)
        );
        assertEquals("textLines cannot be null", ex.getMessage());
    }

    @Test
    void testGetUniqueWordsNullInputThrowsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> wordCounter.getUniqueWords(null)
        );
        assertEquals("textLines cannot be null", ex.getMessage());
    }
}