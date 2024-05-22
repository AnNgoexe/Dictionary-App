package game;

import base.databaseconnection.QuestionDatabaseConnection;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

public class ExerciseManagement {
    private final String databaseName = "EnglishExercise";

    public ExerciseManagement() {

    }

    /**
     * Get exercise list.
     */
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<>();
        MongoCollection<Document> collection = QuestionDatabaseConnection.getInstance(databaseName).getExercisesCollection();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                exercises.add(convertDocumentToExercise(doc));
            }
        }
        return exercises;
    }

    /**
     * Convert document in database to exercise object.
     */
    private Exercise convertDocumentToExercise(Document doc) {
        String question = doc.getString("question");
        String answerA = doc.getString("answerA");
        String answerB = doc.getString("answerB");
        String answerC = doc.getString("answerC");
        String answerD = doc.getString("answerD");
        String answer = doc.getString("answer");
        return new Exercise(question, answerA, answerB, answerC, answerD, answer);
    }
}
