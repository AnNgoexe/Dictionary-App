package CommandLine;

/**
 * The Word class represents a word with its spelling and explanation.
 * Each Word object has two properties: wordTarget and wordExplain.
 * The wordTarget property is a string that holds the spelling of the word.
 * The wordExplain property is a string that holds the explanation of the word.
 * The Word class provides getter and setter methods for both wordTarget and wordExplain.
 * It also provides a method to normalize input strings.
 */
public class Word {
    /**
     * Attributes of Word class.
     * String wordTarget: the spelling of the word.
     * String wordExplain: the information of the word.
     */
    private String wordTarget;
    private String wordExplain;

    /**
     * Normalize input before initializing.
     * @param input The string to be normalized.
     * @return The normalized string.
     */
    private String normalize(String input) {
        if (input == null) {
            return "";
        }
        return input.trim().toLowerCase();
    }

    /**
     * Creating a Word object without parameters.
     */
    public Word() {
        this.wordTarget = "";
        this.wordExplain = "";
    }

    /**
     * Creating a Word object with 2 parameters and standardize.
     * @param wordTarget The spelling of the word.
     * @param wordExplain The explanation of the word.
     */
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = normalize(wordTarget);
        this.wordExplain = normalize(wordExplain);
    }

    /**
     * Return the spelling of the word.
     * @return The spelling of the word.
     */
    public String getWordTarget() {
        return this.wordTarget;
    }

    /**
     * Return the information of the word.
     * @return The explanation of the word.
     */
    public String getWordExplain() {
        return this.wordExplain;
    }

    /**
     * Set the spelling of the word.
     * @param wordTarget The new spelling of the word.
     */
    public void setWordTarget(String wordTarget) {
        this.wordTarget = normalize(wordTarget);
    }
    
    /**
     * Set the information of the word.
     * @param wordExplain The new explanation of the word.
     */
    public void setWordExplain(String wordExplain) {
        this.wordExplain = normalize(wordExplain);
    }
}