package commandline;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DictionaryTest {
    private Dictionary dictionary;

    @BeforeEach
    void setUp() {
        dictionary = new Dictionary();
    }

    @Test
    void searchWord() {
        dictionary.addWordForUser("hello", "pronoun", List.of("greeting"));
        assertTrue(dictionary.searchWord("hello"));
        assertFalse(dictionary.searchWord("apple"));
    }

    @Test
    void addWordForDictionary() {
        dictionary.addWordForUser("hello", "pronoun", List.of("greeting"));
        int initialSize = dictionary.getSize();
        dictionary.addWordForUser("hello", "pronoun", List.of("greeting"));
        assertEquals(initialSize, dictionary.getSize());
    }

    @Test
    void addWordForUser() {
        dictionary.addWordForUser("world", "noun", List.of("earth"));
        assertTrue(dictionary.searchWord("world"));
    }

    @Test
    void removeWord() {
        dictionary.addWordForUser("hello", "pronoun", List.of("greeting"));
        String explanation = dictionary.getWordExplain("hello");
        assertNotNull(explanation);
        assertTrue(explanation.contains("hello"));
    }

    @Test
    void getWordExplain() {
        String explanation = dictionary.getWordExplain("apple");
        assertNotNull(explanation);
        assertTrue(explanation.contains("does not exist"));
    }
}