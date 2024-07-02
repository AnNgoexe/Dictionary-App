package trie;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    private static Trie trie;

    @BeforeAll
    static void setUp() {
        trie = new Trie();
    }

    @Test
    void insert() {
        trie.insert("hello");
        assertTrue(trie.search("hello"));
        assertFalse(trie.search("hell"));
        assertFalse(trie.search("world"));
    }

    @Test
    void getWordsStartingWith() {
        trie.insert("hello");
        trie.insert("hell");
        trie.insert("heaven");
        trie.insert("heavy");

        List<String> words = trie.getWordsStartingWith("he");
        assertEquals(4, words.size());
        assertTrue(words.contains("hello"));
        assertTrue(words.contains("hell"));
        assertTrue(words.contains("heaven"));
        assertTrue(words.contains("heavy"));

        words = trie.getWordsStartingWith("hel");
        assertEquals(2, words.size());
        assertTrue(words.contains("hello"));
        assertTrue(words.contains("hell"));

        words = trie.getWordsStartingWith("hea");
        assertEquals(2, words.size());
        assertTrue(words.contains("heaven"));
        assertTrue(words.contains("heavy"));

        words = trie.getWordsStartingWith("xyz");
        assertTrue(words.isEmpty());
    }

    @Test
    void search() {
        trie.insert("hello");
        assertTrue(trie.search("hello"));
        assertFalse(trie.search("hell"));
        assertFalse(trie.search("world"));
    }

    @Test
    void delete() {
        trie.insert("hello");
        trie.insert("hell");
        assertTrue(trie.search("hello"));
        assertTrue(trie.search("hell"));

        trie.delete("hello");
        assertFalse(trie.search("hello"));
        assertTrue(trie.search("hell"));

        trie.delete("hell");
        assertFalse(trie.search("hell"));
    }

    @Test
    void edit() {
        trie.insert("hello");
        assertTrue(trie.search("hello"));
        assertFalse(trie.search("world"));

        trie.edit("hello", "world");
        assertFalse(trie.search("hello"));
        assertTrue(trie.search("world"));
    }
}
