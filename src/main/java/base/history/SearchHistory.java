package base.history;

import base.databaseconnection.DictionaryDatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SearchHistory implements History {
    private Connection connection;

    private static SearchHistory history;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private List<String> wordList = new ArrayList<>();

    /**
     * Connect to the database.
     */
    public void connectToDatabase() {
        this.connection = DictionaryDatabaseConnection.getInstance().getConnection();
    }

    /**
     * Get the instance of History class.
     */
    public static synchronized SearchHistory getHistory() {
        if (history == null) {
            history = new SearchHistory();
        }
        history.connectToDatabase();
        return history;
    }

    /**
     * Add words to the dictionary history from the database file.
     */
    public void insertFromFile() {
        try {
            String sql = "SELECT word FROM History ORDER BY time DESC";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String wordTarget = rs.getString("word").trim();
                this.wordList.add(wordTarget);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update / insert to history.
     */
    @Override
    public void addWordForHistory(String wordTarget) {
        LocalDateTime currentTime = LocalDateTime.now();
        String time = currentTime.format(formatter);
        if (searchWord(wordTarget)) {
            try {
                String sqlUpdate = "UPDATE History SET time = ? WHERE word = ?";
                PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdate);
                statementUpdate.setString(1, time);
                statementUpdate.setString(2, wordTarget);
                statementUpdate.executeUpdate();

                this.wordList.add(0, wordTarget);
            } catch (SQLException e) {
                System.out.println("Update Error: " + e.getMessage());
            }
        } else {
            try {
                String sqlInsert = "INSERT INTO History (word, time) VALUES (?, ?)";
                PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);
                statementInsert.setString(1, wordTarget);
                statementInsert.setString(2, time);
                statementInsert.executeUpdate();

                this.wordList.add(0, wordTarget);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Delete all the history.
     */
    @Override
    public void resetHistory() {
        try {
            String sql = "DELETE FROM History";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *Search history.
     */
    @Override
    public boolean searchWord(String wordTarget) {
        return this.wordList.contains(wordTarget);
    }

    public List<String> getWordHistory() {
        return wordList;
    }
}
