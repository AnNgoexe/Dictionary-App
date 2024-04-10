package base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyDictionary extends Dictionary {
    /**
     * Attributes of MyDictionary class.
     */
    private static MyDictionary myDictionary;

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
     * Add words to the dictionary from the database file where favorite = '1'.
     */
    @Override
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
    @Override
    public void addWordForDictionary(String wordTarget, String wordExplain) {
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
    @Override
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
}
