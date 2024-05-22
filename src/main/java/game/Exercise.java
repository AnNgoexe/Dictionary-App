package game;

public class Exercise {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;

    /**
     * Exercise constructor.
     */
    public Exercise(String question, String optionA, String optionB, String optionC, String optionD, String answer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }

    /**
     * Getters for question attribute.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Getters for option attribute.
     */
    public String getOptionA() {
        return optionA;
    }

    /**
     * Getters for option attribute.
     */
    public String getOptionB() {
        return optionB;
    }

    /**
     * Getters for option attribute.
     */
    public String getOptionC() {
        return optionC;
    }

    /**
     * Getters for option attribute.
     */
    public String getOptionD() {
        return optionD;
    }

    /**
     * Getters for answer attribute.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Setter for question attribute.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Setter for option attribute.
     */
    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    /**
     * Setter for option attribute.
     */
    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    /**
     * Setter for option attribute.
     */
    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    /**
     * Setter for option attribute.
     */
    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    /**
     * Setter for answer attribute.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}



