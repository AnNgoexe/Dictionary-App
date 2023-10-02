package CommandLine;

import java.util.HashMap;

/**
 * The Dictionary class represents a dictionary of words.
 * Each Dictionary object has a HashMap that maps a word's spelling to its explanation.
 */
public class Dictionary {
    /**
     * Attributes of Dictionary class.
     * HashMap<String, String> words: the list of the word.
     */
    private HashMap<String, String> words;

    /**
     * Creating a Dictionary object without parameters.
     */
    public Dictionary() {
        this.words = new HashMap<String, String>();
    }

    /**
     * Creating a Dictionary object with parameter.
     * @param words The list of words.
     */
    public Dictionary(HashMap<String, String> words) {
        this.words = words;
    }

    /**
     * Add a word in the dictionary.
     * @param word The Word object to be added to the dictionary.
     */
    public void addWord(Word word) {
        if (word.getWordTarget().isEmpty() || word.getWordExplain().isEmpty()) {
            System.out.println("The word or its explanation cannot be empty.");
        } else if (!searchWord(word.getWordTarget())) {
            this.words.put(word.getWordTarget(), word.getWordExplain());
        } else {
            System.out.println("The word already exists in the dictionary.");
        }
    }    
    
    /**
     * Add a word in the dictionary.
     * @param wordTarget The spelling of the word to be added.
     * @param wordExplain The explanation of the word to be added.
     */
    public void addWord(String wordTarget, String wordExplain) {
        if (wordTarget.isEmpty() || wordExplain.isEmpty()) {
            System.out.println("The word or its explanation cannot be empty.");
        } else if (!searchWord(wordTarget)) {
            this.words.put(wordTarget, wordExplain);
        } else {
            System.out.println("The word already exists in the dictionary.");
        }
    }

    /**
     * Remove a word from the dictionary.
     * @param wordTarget The spelling of the word to be removed.
     */
    public void removeWord(String wordTarget) {
        if (searchWord(wordTarget)) {
            this.words.remove(wordTarget);
        } else {
            System.out.println("The word does not exist in the dictionary.");
        }
    }

    /**
     * Search whether a word has exist in the list.
     * @param wordTarget The spelling of the word to be searched.
     * @return true if the word exists, false otherwise.
     */
    public boolean searchWord(String wordTarget) {
        return this.words.containsKey(wordTarget);
    }

    /**
     * Get the explanation of a specific target word.
     * @param wordTarget The spelling of the word whose explanation is to be retrieved.
     * @return The explanation of the word if it exists, a message otherwise.
     */
    public String getWordExplain(String wordTarget) {
        if (searchWord(wordTarget)) {
            return this.words.get(wordTarget);
        } 
        else {
            return "The word does not exist in the dictionary.";
        }
    }

    /**
     * Update the explanation of a word in the dictionary.
     * @param wordTarget The spelling of the word whose explanation is to be updated.
     * @param newWordExplain The new explanation of the word.
     */
    public void editWordExplain(String wordTarget, String newWordExplain) {
        if (searchWord(wordTarget)) {
            this.words.put(wordTarget, newWordExplain);
        } else {
            System.out.println("The word does not exist in the dictionary.");
        }
    }

    /**
     * Update the target of a word in the dictionary.
     * @param oldWordTarget The old spelling of the word.
     * @param newWordTarget The new spelling of the word.
     */
    public void editWordTarget(String oldWordTarget, String newWordTarget) {
        if (searchWord(oldWordTarget)) {
            String wordExplain = this.words.get(oldWordTarget);
            this.words.remove(oldWordTarget);
            this.words.put(newWordTarget, wordExplain);
        } else {
            System.out.println("The word does not exist in the dictionary.");
        }  
    }

    /**
     * Return the number of words.
     * @return The number of words in the dictionary.
     */
    public int getSize() {
        return this.words.size();
    }

    /**
     * Get the list of words.
     * @return The list of words in the dictionary.
     */
    public HashMap<String, String> getMapWord() {
        return this.words;
    }

    /**
     * Set the list of words.
     * @param words The new list of words.
     */
    public void setMapWord(HashMap<String, String> words) {
        if (words != null && !words.isEmpty()) {
            this.words = words;
        } else {
            this.words = new HashMap<String, String>();
        }
    }
}