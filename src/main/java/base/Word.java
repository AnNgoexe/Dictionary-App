package base;

public class Word {
    /**
     * Attributes of Word class.
     */
    private String wordTarget;
    private String wordExplain;

    /**
     * Normalize input before initializing.
     */
    private String normalize(String input) {
        if (input == null) {
            return "";
        }
        return input.trim();
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
     */
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = normalize(wordTarget);
        this.wordExplain = normalize(wordExplain);
    }

    /**
     * Return the spelling of the word.
     */
    public String getWordTarget() {
        return this.wordTarget;
    }

    /**
     * Return the information of the word.
     */
    public String getWordExplain() {
        return this.wordExplain;
    }

    /**
     * Set the spelling of the word.
     */
    public void setWordTarget(String wordTarget) {
        this.wordTarget = normalize(wordTarget);
    }

    /**
     * Set the information of the word.
     */
    public void setWordExplain(String wordExplain) {
        this.wordExplain = normalize(wordExplain);
    }

    /**
     * Get the information of the word.
     */
    @Override
    public String toString() {
        return wordTarget + "\n" + wordExplain + "\n";
    }
}
