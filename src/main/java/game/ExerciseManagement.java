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
        List<String> options = (List<String>) doc.get("options");
        String optionA = options.get(0);
        String optionB = options.get(1);
        String optionC = options.get(2);
        String optionD = options.get(3);
        String answer = doc.getString("answer");
        return new Exercise(question, optionA, optionB, optionC, optionD, answer);
    }
}
