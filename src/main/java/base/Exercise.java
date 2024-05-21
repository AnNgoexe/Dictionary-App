package base;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class Exercise {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public Exercise() {
        // Connect to the MongoDB server. Adjust the connection string as necessary.
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Select the database. Replace "EnglishExercise" with your actual database name.
        database = mongoClient.getDatabase("EnglishExercise");
        // Select the collection. Replace "exercise" with your actual collection name.
        collection = database.getCollection("exercise");
    }

    public MongoCollection<Document> getConnection() {
        return this.collection;
    }

    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    // Method to fetch all documents from the "exercise" collection
    public List<Document> getAllExercises() {
        List<Document> exercises = new ArrayList<>();
        collection.find().into(exercises);
        return exercises;
    }

    // Method to add a new exercise
    public void addExercise(Document newExercise) {
        collection.insertOne(newExercise);
    }

    public static void main(String[] args) {
        Exercise exerciseDB = new Exercise();



        List<Document> exercises = exerciseDB.getAllExercises();
        for(int i = 0; i < exercises.size(); i++){
            System.out.println(i);
        }
    }
}
