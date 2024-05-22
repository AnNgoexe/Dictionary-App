package base.history;

public interface History {
    /**
     * Add word for history.
     */
    public void addWordForHistory(String wordTarget);

    /**
     * Reset history data.
     */
    public void resetHistory();

    /**
     * Search word in history.
     */
    public boolean searchWord(String wordTarget);

    /**
     * Insert from file data.
     */
    public void insertFromFile();
}
