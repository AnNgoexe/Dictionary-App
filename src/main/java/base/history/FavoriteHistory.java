package base.history;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteHistory implements History {
    private static FavoriteHistory history;

    private List<String> wordList = new ArrayList<>();

    private final String PATH = "src/main/resources/data/FavoriteHistory.txt";

    /**
     * Get the instance of History class.
     */
    public static synchronized FavoriteHistory getHistory() {
        if (history == null) {
            history = new FavoriteHistory();
        }
        return history;
    }
     public void deleteWordForHistory(String wordTarget) {
        if(searchWord(wordTarget)) {
            this.wordList.remove(wordTarget);
        }
     }

    @Override
    public void addWordForHistory(String wordTarget) {
        wordList.add(0, wordTarget);
    }

    /**
     * Reset history data.
     */
    @Override
    public void resetHistory() {
        this.wordList.clear();
    }

    /**
     * Search word in history.
     */
    @Override
    public boolean searchWord(String wordTarget) {
        return wordList.contains(wordTarget.trim());
    }

    /**
     * Insert from file data.
     */
    @Override
    public void insertFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                addWordForHistory(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getWordHistory() {
        return wordList;
    }

    public void writeToFile() {
        try (PrintWriter writer = new PrintWriter(PATH)) {
            for (String wordTarget : wordList) {
                writer.println(wordTarget);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
