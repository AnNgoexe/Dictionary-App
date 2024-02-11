package trie;

import java.util.Map;
import java.util.TreeMap;

public class TrieNode {
    /**
     * Attribute of TrieNode class.
     */
    private boolean isEndOfWord;
    private Map<Character, TrieNode> children;

    /**
     * Initialize TrieNode class without parameters.
     */
    public TrieNode() {
        this.children = new TreeMap<>();
        this.isEndOfWord = false;
    }

    /**
     * Get the child of the trie node.
     */
    public TrieNode getChild(char ch) {
        return this.children.get(ch);
    }

    /**
     * Get the system of children of the trie node.
     */
    public Map<Character, TrieNode> getChildren() {
        return this.children;
    }

    /**
     * Set the child of the trie node.
     */
    public void setChild(char ch, TrieNode node) {
        this.children.put(ch, node);
    }

    /**
     * Get the status of the end of word.
     */
    public boolean isEndOfWord() {
        return this.isEndOfWord;
    }

    /**
     * Set the status of the end of word.
     */
    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }
}