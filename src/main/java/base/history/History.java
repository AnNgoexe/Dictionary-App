package base.history;

public interface History {
    public void addWordForHistory(String wordTarget);

    public void resetHistory();

    public boolean searchWord(String wordTarget);
}
