package base.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DictionaryDatabaseConnection extends DatabaseConnection {
    private static DictionaryDatabaseConnection instance;
    private Connection connection;

    private DictionaryDatabaseConnection() {
        try {
            String url = "jdbc:sqlite:E:\\Dictionary-App\\src\\main\\resources\\data\\Vocabulary.db";
            this.connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static synchronized DictionaryDatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DictionaryDatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing the database connection: " + e.getMessage());
        }
    }
}