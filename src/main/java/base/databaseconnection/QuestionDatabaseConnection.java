package base.databaseconnection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class QuestionDatabaseConnection {
    private static QuestionDatabaseConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private QuestionDatabaseConnection(String databaseName) {
        try {
            this.mongoClient = MongoClients.create("mongodb://localhost:27017");
            this.database = mongoClient.getDatabase(databaseName);
        } catch (Exception e) {
            System.out.println("Error connecting to MongoDB: " + e.getMessage());
            throw new RuntimeException("Error connecting to MongoDB", e);
        }
    }

    public static synchronized QuestionDatabaseConnection getInstance(String databaseName) {
        if (instance == null) {
            instance = new QuestionDatabaseConnection(databaseName);
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }

    public void closeConnection() {
        try {
            if (this.mongoClient != null) {
                this.mongoClient.close();
            }
        } catch (Exception e) {
            System.out.println("Error closing MongoDB connection: " + e.getMessage());
        }
    }

    public MongoCollection<Document> getExercisesCollection() {
        return database.getCollection("exercise");
    }

    /**
     * Method to fetch all documents from the "exercise" collection
     */
    public List<Document> getAllExercises() {
        List<Document> exercises = new ArrayList<>();
        getExercisesCollection().find().into(exercises);
        return exercises;
    }

    /**
     * Method to add a new exercise
     */
    public void addExercise(Document newExercise) {
        getExercisesCollection().insertOne(newExercise);
    }
}
