package base;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import trie.Trie;

public class Dictionary {
    /**
     * Attributes of Dictionary class.
     */
    private static Dictionary dictionary;
    private HashMap<String, String> wordMap = new HashMap<>();
    private Trie trie = new Trie();
    private Connection connection;
    private boolean isConnected = false;

    /**
     * Constructor method of Dictionary class.
     */
    private Dictionary() {

    }

    /**
     * Connect to the database.
     */
    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:E:\\demo\\src\\main\\resources\\data\\Vocabulary.db");
            isConnected = true;
        } catch (SQLException e) {
            System.out.println("Error Dictionary connecting to the database: " + e.getMessage());
        }
    }

    /**
     * Get the instance of Dictionary class.
     */
    public static synchronized Dictionary getDictionary() {
        if (dictionary == null) {
            dictionary = new Dictionary();
        }
        dictionary.connectToDatabase();
        return dictionary;
    }

    /**
     * Get the map of words.
     */
    public HashMap<String, String> getWordMap() {
        return this.wordMap;
    }

    /**
     * Search for a word in the dictionary by its spelling.
     */
    public boolean searchWord(String wordTarget) {
        return this.wordMap.containsKey(wordTarget);
    }

    /**
     * Get a word from the dictionary.
     */
    public String getWord(String wordTarget) {
        return this.wordMap.get(wordTarget);
    }

    /**
     * Add a word to the dictionary.
     */
    public boolean addWordForDictionary(String wordTarget, String wordExplain) {
        if (!searchWord(wordTarget)) {
            try {
                String sql = "INSERT INTO av (word, html) VALUES (?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, wordTarget);
                statement.setString(2, wordExplain);
                statement.executeUpdate();

                Word word = new Word(wordTarget, wordExplain);
                this.wordMap.put(word.getWordTarget(), word.getWordExplain());
                this.trie.insert(word.getWordTarget());
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Add words to the dictionary from the database file.
     */
    public void insertFromFile() {
        try {
            String sql = "SELECT word, html FROM av";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String wordTarget = rs.getString("word").trim();
                String wordExplain = rs.getString("html").trim();
                this.wordMap.put(wordTarget, wordExplain);
                this.trie.insert(wordTarget);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Remove a word from the dictionary.
     */
    public boolean removeWord(String wordTarget) {
        if (searchWord(wordTarget)) {
            try {
                String sql = "DELETE FROM av WHERE word = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, wordTarget);
                statement.executeUpdate();

                this.wordMap.remove(wordTarget);
                this.trie.delete(wordTarget);
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Update a word in the dictionary.
     */
    public boolean updateWord(String oldWord, String wordExplain) {
        if (searchWord(oldWord) && !oldWord.trim().isEmpty()) {
            if (!wordExplain.trim().isEmpty()) {
                try {
                    String sql = "UPDATE av SET html = ? WHERE word = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, wordExplain);
                    statement.setString(2, oldWord);
                    statement.executeUpdate();

                    Word word = new Word(oldWord, wordExplain);
                    this.wordMap.put(word.getWordTarget(), word.getWordExplain());
                    return true;
                } catch (SQLException e) {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Export the dictionary to a file.
     */
    public boolean dictionaryExportToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (HashMap.Entry<String, String> entry : wordMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Word word = new Word(key, value);
                writer.write(word.toString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Get a list of words that start with a given string.
     */
    public List<String> getWordsStartingWith(String prefix) {
        return this.trie.getWordsStartingWith(prefix);
    }

    /**
     * Close connection to database.
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                isConnected = false;
            }
        } catch (SQLException e) {
            System.out.println("Error closing the database connection: " + e.getMessage());
        }
    }

    /**
     * Insert data to current database from file.
     */
    public void dictionaryImportFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String wordTarget;
            String wordExplain;

            while ((wordTarget = reader.readLine()) != null && (wordExplain = reader.readLine()) != null) {
                wordTarget = wordTarget.trim();
                wordExplain = wordExplain.trim();

                addWordForDictionary(wordTarget, wordExplain);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}
