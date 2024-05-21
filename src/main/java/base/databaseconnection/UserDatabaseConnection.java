package base.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDatabaseConnection {
    private static UserDatabaseConnection instance;
    private Connection connection;

    private UserDatabaseConnection() {
        try {
            String url = "jdbc:sqlite:E:\\Dictionary-App\\src\\main\\resources\\data\\User.db";
            this.connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static synchronized UserDatabaseConnection getInstance() {
        if (instance == null) {
            instance = new UserDatabaseConnection();
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
