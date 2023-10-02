package CommandLine;

import java.util.Scanner;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The DictionaryManagement class represents a management tool for a dictionary.
 * Each DictionaryManagement object has a property: Dictionary.
 * The Dictionary property is a system of words.
 * The DictionaryManagement class provides methods for managing the dictionary.
 */
public class DictionaryManagement {
    /**
     * Attributes of DictionaryManagement class.
     * Dictionary dictionary: the dictionary to be managed.
     */
    private Dictionary dictionary;

    /**
     * Creating a DictionaryManagement object without parameters.
     */
    public DictionaryManagement() {
        this.dictionary = new Dictionary();
    }

    /**
     * Creating a DictionaryManagement object with parameter.
     * @param dictionary The dictionary to be managed.
     */
    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Add words to the dictionary from the command line.
     */
    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of words you want to add: ");
        int numWords = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < numWords; i++) {
            //The ith word you add.
            System.out.println("Enter the word " + (i+1) + ":");

            //The spelling of the word.
            System.out.print("English: ");
            String wordTarget = scanner.nextLine().trim().toLowerCase();

            //The pronunciation of the word.
            System.out.print("Pronunciation (Have to type in this form: /.../): ");
            String pronunciation = scanner.nextLine().trim().toLowerCase();

            //The number of word classes.
            System.out.print("Enter the number of word classes: ");
            int numClasses = scanner.nextInt();
            scanner.nextLine(); 

            StringBuilder wordExplain = new StringBuilder("<h1>" + wordTarget + "</h1><h3><i>" + pronunciation + "</i></h3>");

            for (int j = 0; j < numClasses; j++) {
                //The ith word class you add.
                System.out.println("Enter word class " + (j+1) + ":");
                String wordClassKey = scanner.nextLine().trim().toLowerCase();

                if (!wordClassKey.isEmpty()) {
                    //The number of word meaning according to this word class.
                    System.out.print("Enter the number of meanings: ");
                    int numMeanings = scanner.nextInt();
                    scanner.nextLine(); 

                    StringBuilder meaningsBuilder = new StringBuilder();

                    for (int k = 0; k < numMeanings; k++) {
                        //The ith meaning you add according to this word class.
                        System.out.println("Enter meaning " + (k+1) + ":");
                        String meaning = scanner.nextLine().trim().toLowerCase();
                        if (!meaning.isEmpty()) {
                            meaningsBuilder.append("<li>" + meaning + "</li>");
                        }
                    }

                    if (meaningsBuilder.length() > 0) {
                        wordExplain.append("<h2>" + wordClassKey + "</h2><ul>");
                        wordExplain.append(meaningsBuilder.toString());
                        wordExplain.append("</ul>");
                    }
                }
            }
            Word newWord = new Word(wordTarget, wordExplain.toString());
            this.dictionary.addWord(newWord);
        }
        scanner.close();
    }

    /**
     * Add words to the dictionary from the database file.
     * @param dbPath The database path.
     */
    public void insertFromFile(String dbPath) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT word, html FROM av");

            while(rs.next()) {
                // read the result set
                String wordTarget = rs.getString("word").trim().toLowerCase();
                String wordExplain = rs.getString("html").trim().toLowerCase();

                Word newWord = new Word(wordTarget, wordExplain);
                this.dictionary.addWord(newWord);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Remove a word from the dictionary from command line.
     */
    public void removeFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word to remove: ");
        String wordTarget = scanner.nextLine();
        this.dictionary.removeWord(wordTarget);
        scanner.close();
    }

    /**
     * Export data to database file.
     * @param dbPath The database path.
     */
    public void dictionaryExportToFile(String dbPath) {
        try {
            FileWriter writer = new FileWriter(dbPath);
    
            // Write SQL statements for each word in the dictionary
            for (Map.Entry<String, String> entry : this.dictionary.getMapWord().entrySet()) {
                String wordTarget = entry.getKey();
                String wordExplain = entry.getValue();
    
                // Create an SQL statement to insert the word into a table
                String sql = "INSERT INTO Vocabulary (word, html) VALUES ('" + wordTarget + "', '" + wordExplain + "');\n";
    
                // Write the SQL statement to the file
                writer.write(sql);
            }
    
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Look up a word in the dictionary from the command line.
     */
    public void dictionaryLookup() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you want to look up: ");
        String wordTarget = scanner.nextLine().trim().toLowerCase();
        System.out.println(this.dictionary.getWordExplain(wordTarget));
        scanner.close();
    }

    /**
     * Get the dictionary.
     * @return The dictionary to be managed.
     */
    public Dictionary getDictionary() {
        return this.dictionary;
    }

    /**
     * Set the dictionary.
     * @param dictionary The new dictionary to be managed.
     */
    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}
