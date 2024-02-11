package base;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import trie.Trie;

public class MyDictionary {
    /**
     * Attributes of MyDictionary class.
     */
    private static MyDictionary myDictionary;
    private HashMap<String, String> wordMap = new HashMap<>();
    private Trie trie = new Trie();
    private Connection connection;
    private boolean isConnected = false;

    /**
     * Constructor method of Dictionary class.
     */
    private MyDictionary() {

    }

    /**
     * Connect to the database.
     */
    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:E:\\demo\\src\\main\\resources\\data\\Vocabulary.db");
            isConnected = true;
        } catch (SQLException e) {
            System.out.println("Error MyDictionary connecting to the database: " + e.getMessage());
        }
    }

    /**
     * Get the instance of Dictionary class.
     */
    public static synchronized MyDictionary getDictionary() {
        if (myDictionary == null) {
            myDictionary = new MyDictionary();
        }
        myDictionary.connectToDatabase();
        return myDictionary;
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
     * Add words to the dictionary from the database file where favorite = '1'.
     */
    public void insertFromFile() {
        try {
            String sql = "SELECT word, html FROM av WHERE favorite = 1";
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
     * Set the "favorite" column value for a word in the dictionary to the default value (1).
     */
    public void addWord(String wordTarget, String wordExplain) {
        if (!searchWord(wordTarget)) {
            try {
                String updateSql = "UPDATE av SET favorite = '1' WHERE word = ?";
                PreparedStatement updateStatement = this.connection.prepareStatement(updateSql);
                updateStatement.setString(1, wordTarget);
                updateStatement.executeUpdate();

                this.wordMap.put(wordTarget, wordExplain);
                this.trie.insert(wordTarget);
            } catch (SQLException e) {
                System.out.println("Error SQL");
            }
        }
    }

    /**
     * Remove a word from the dictionary and set the "favorite" column to null.
     */
    public void removeWord(String wordTarget) {
        if (searchWord(wordTarget)) {
            try {
                String updateSql = "UPDATE av SET favorite = null WHERE word = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, wordTarget);
                updateStatement.executeUpdate();

                this.wordMap.remove(wordTarget);
                this.trie.delete(wordTarget);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Update a word in the dictionary.
     */
    public void updateWord(String oldWord, String wordExplain) {
        if (searchWord(oldWord) && !oldWord.trim().isEmpty()) {
            if (!wordExplain.trim().isEmpty()) {
                Word word = new Word(oldWord, wordExplain);
                this.wordMap.put(word.getWordTarget(), word.getWordExplain());
            }
        }
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
}
