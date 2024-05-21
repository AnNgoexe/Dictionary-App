package base.databaseconnection;

public abstract class DatabaseConnection {
    public abstract Object getConnection();

    public abstract void closeConnection();
}
