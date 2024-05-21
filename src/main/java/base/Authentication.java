package base;

import base.databaseconnection.UserDatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {
    private Connection connection;

    public Authentication() {
        this.connection = UserDatabaseConnection.getInstance().getConnection();
    }

    /**
     * Check whether user and password exist.
     */
    public int authenticateUser(String username, String password) {
        String userQuery = "SELECT * FROM User WHERE user = ?";
        String passwordQuery = "SELECT * FROM User WHERE user = ? AND password = ?";

        try (PreparedStatement userStatement = connection.prepareStatement(userQuery)) {
            userStatement.setString(1, username);

            try (ResultSet userResultSet = userStatement.executeQuery()) {
                if (userResultSet.next()) {
                    try (PreparedStatement passwordStatement = connection.prepareStatement(passwordQuery)) {
                        passwordStatement.setString(1, username);
                        passwordStatement.setString(2, password);

                        try (ResultSet passwordResultSet = passwordStatement.executeQuery()) {
                            if (passwordResultSet.next()) {
                                return 0;
                            } else {
                                return 1;
                            }
                        }
                    }
                } else {
                    return 2;
                }
            }
        } catch (SQLException e) {
            return -1;
        }
    }

    /**
     * Register a new user.
     */
    public int registerUser(String username, String password) {
        if (!checkUsername(username)) {
            return 3;
        }
        if (!checkPassword(password)) {
            return 2;
        }

        String checkUserQuery = "SELECT * FROM User WHERE user = ?";
        String insertUserQuery = "INSERT INTO User (user, password) VALUES (?, ?)";

        try (PreparedStatement checkUserStatement = connection.prepareStatement(checkUserQuery)) {
            checkUserStatement.setString(1, username);

            try (ResultSet resultSet = checkUserStatement.executeQuery()) {
                if (resultSet.next()) {
                    return 1;
                } else {
                    try (PreparedStatement insertUserStatement = connection.prepareStatement(insertUserQuery)) {
                        insertUserStatement.setString(1, username);
                        insertUserStatement.setString(2, password);
                        insertUserStatement.executeUpdate();
                        return 0;
                    }
                }
            }
        } catch (SQLException e) {
            return -1;
        }
    }

    /**
     * Check password validity.
     */
    private boolean checkPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    /**
     * Check user name validity.
     */
    private boolean checkUsername(String username) {
        if (username.length() < 5 || username.length() > 15) {
            return false;
        }

        for (char c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
