package game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogic {
    private ExerciseManagement exerciseManagement;
    private List<Exercise> exercises;
    private int currentExerciseIndex;
    private User user;
    private boolean isGameOver;

    private MediaPlayer correctMusicPlayer;

    private MediaPlayer incorrectMusicPlayer;

    public GameLogic(User user) {
        this.isGameOver = false;
        this.exerciseManagement = new ExerciseManagement();
        List<Exercise> allExercises = exerciseManagement.getAllExercises();
        Collections.shuffle(allExercises);
        exercises = allExercises.subList(0, Math.min(15, allExercises.size()));
        this.currentExerciseIndex = 0;
        this.user = user;

        String correctMusicFile = Paths.get("src/main/resources/sound/correct.mp3").toUri().toString();
        Media correctMusicMedia = new Media(correctMusicFile);
        correctMusicPlayer = new MediaPlayer(correctMusicMedia);
        correctMusicPlayer.setCycleCount(1);

        String incorrectMusicFile = Paths.get("src/main/resources/sound/incorrect.wav").toUri().toString();
        Media incorrectMusicMedia = new Media(incorrectMusicFile);
        incorrectMusicPlayer = new MediaPlayer(incorrectMusicMedia);
        incorrectMusicPlayer.setCycleCount(1);
    }

    /**
     * Get the exercise at the current index and increment the index for the next call.
     */
    public Exercise getNextExercise() {
        if (currentExerciseIndex >= exercises.size()) {
            return null;
        }
        return exercises.get(currentExerciseIndex++);
    }

    /**
     * Check answer.
     */
    public boolean checkAnswer(Exercise exercise, String userAnswer) {
        return userAnswer.equalsIgnoreCase(exercise.getAnswer());
    }

    /**
     * Increase score for the user.
     */
    public void increaseScore() {
        user.increaseScore();
    }

    /**
     * Get two random wrong answers.
     */
    public List<String> getTwoRandomWrongAnswers(Exercise exercise) {
        List<String> options = new ArrayList<>();
        if (!exercise.getOptionA().equalsIgnoreCase(exercise.getAnswer())) {
            options.add(exercise.getOptionA());
        }
        if (!exercise.getOptionB().equalsIgnoreCase(exercise.getAnswer())) {
            options.add(exercise.getOptionB());
        }
        if (!exercise.getOptionC().equalsIgnoreCase(exercise.getAnswer())) {
            options.add(exercise.getOptionC());
        }
        if (!exercise.getOptionD().equalsIgnoreCase(exercise.getAnswer())) {
            options.add(exercise.getOptionD());
        }
        Collections.shuffle(options);
        return options.subList(0, Math.min(2, options.size()));
    }

    /**
     * Get three random answers with at least one correct.
     */
    public List<String> getThreeRandomAnswersWithAtLeastOneCorrect(Exercise exercise) {
        List<String> wrongAnswers = new ArrayList<>();
        if (!exercise.getOptionA().equalsIgnoreCase(exercise.getAnswer())) {
            wrongAnswers.add(exercise.getOptionA());
        }
        if (!exercise.getOptionB().equalsIgnoreCase(exercise.getAnswer())) {
            wrongAnswers.add(exercise.getOptionB());
        }
        if (!exercise.getOptionC().equalsIgnoreCase(exercise.getAnswer())) {
            wrongAnswers.add(exercise.getOptionC());
        }
        if (!exercise.getOptionD().equalsIgnoreCase(exercise.getAnswer())) {
            wrongAnswers.add(exercise.getOptionD());
        }

        Collections.shuffle(wrongAnswers);
        List<String> selectedAnswers = new ArrayList<>(wrongAnswers.subList(0, Math.min(2, wrongAnswers.size())));
        selectedAnswers.add(exercise.getAnswer());
        Collections.shuffle(selectedAnswers);
        return selectedAnswers;
    }

    /**
     * Decrease the user's heart count.
     */
    public void decreaseHeart() {
        user.decreaseHeart();
    }

    /**
     * Increase the user's heart count.
     */
    public void increaseHeart() {
        user.increaseHeart();
    }

    /**
     * Get the user's current heart count.
     */
    public int getHeart() {
        return user.getHeart();
    }

    /**
     * Get the user's current score.
     */
    public int getScore() {
        return user.getScore();
    }

    /**
     * Get the user's name.
     */
    public String getUserName() {
        return user.getName();
    }

    /**
     * Check if the game is over.
     */
    public boolean isGameOver() {
        return isGameOver;
    }
}
