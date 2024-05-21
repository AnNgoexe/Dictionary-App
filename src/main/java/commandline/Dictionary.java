package commandline;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;

/**
 * The Dictionary class represents a dictionary with a list of words.
 * Each Dictionary object has one property: dictionary.
 * The dictionary property is a list of Word objects.
 * The Dictionary class provides getter and setter methods for the dictionary
 * property.
 * It also provides methods to process words to the dictionary.
 */
public class Dictionary {
    /**
     * Attributes of Dictionary class.
     * Map<String, Word> dictionary: the map from word targets to words in the
     * dictionary.
     */
    private TreeMap<String, Word> dictionary;

    /**
     * Creating a Dictionary object without parameters.
     */
    public Dictionary() {
        this.dictionary = new TreeMap<>();
    }

    /**
     * Creating a Dictionary object with parameter.
     */
    public Dictionary(Map<String, Word> words) {
        this.dictionary = new TreeMap<>(words);
    }

    /**
     * Return the map of words in the dictionary.
     */
    public TreeMap<String, Word> getDictionary() {
        return this.dictionary;
    }

    /**
     * Set the dictionary with a new map of words.
     */
    public void setDictionary(Map<String, Word> words) {
        this.dictionary = new TreeMap<>(words);
    }

    /**
     * Return the size of the dictionary.
     */
    public int getSize() {
        return this.dictionary.size();
    }

    /**
     * Search for a word in the dictionary by its spelling.
     */
    boolean searchWord(String wordTarget) {
        return this.dictionary.containsKey(wordTarget.toLowerCase());
    }

    public void addWordForDictionary(String word, String pronoun, List<String> meaning) {
        if (!searchWord(word)) {
            this.dictionary.put(word, new Word(word, pronoun, meaning));
        }
    }

    /**
     * Add a word to the dictionary with its spelling, pronunciation and meanings.
     */
    public void addWordForUser(String word, String pronoun, List<String> meaning) {
        if (!searchWord(word)) {
            this.dictionary.put(word, new Word(word, pronoun, meaning));
            System.out.println("The word " + word + " has been added to the dictionary.");
        } else {
            System.out.println("The word " + word + " already exists in the dictionary.");
        }
    }

    /**
     * Remove a word from the dictionary by its spelling.
     * The spelling of the word to remove.
     */
    public void removeWord(String wordTarget) {
        if (searchWord(wordTarget)) {
            this.dictionary.remove(wordTarget.toLowerCase());
            System.out.println("The word " + wordTarget + " has been removed from the dictionary.");
        } else {
            System.out.println("The word " + wordTarget + " is not in the dictionary.");
        }
    }


    /**
     * Return the explanation of a word in the dictionary by its spelling.
     */
    public String getWordExplain(String wordTarget) {
        if (searchWord(wordTarget)) {
            Word word = this.dictionary.get(wordTarget);
            return word.toString();
        } else {
            return "The word " + wordTarget + " does not exist in the dictionary.";
        }
    }
}