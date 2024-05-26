package com.example.dictionary;

import game.Exercise;
import game.GameLogic;
import game.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private AnchorPane viewContainer;

    @FXML
    private Button quitButton;

    @FXML
    private HBox heartHBox;

    @FXML
    private TextArea exerciseTextArea;

    @FXML
    private Label scoreLabel;

    @FXML
    private ProgressBar timeProgressBar;

    @FXML
    private Button nextButton;

    @FXML
    private Button consultHelpButton;

    @FXML
    private Button optionCButton;

    @FXML
    private Button optionAButton;

    @FXML
    private Button optionBButton;

    @FXML
    private Button optionDButton;

    @FXML
    private Button muteButton;

    @FXML
    private Button unMuteButton;

    @FXML
    private Button moreHeartHelpButton;

    @FXML
    private Button fiftyFiftyHelpButton;

    @FXML
    private Button moreTimeHelpButton;

    private MediaPlayer backgroundMusicPlayer;

    private User user;

    private GameLogic gameLogic;

    private Exercise exercise;

    private boolean isChosen = false;

    private Timeline timeline;

    private final Duration duration = Duration.seconds(30);

    private double timeElapsed = 0;

    private AudioClip correctSound;

    private AudioClip incorrectSound;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String musicFile = Paths.get("src/main/resources/sound/music.wav").toUri().toString();
        Media media = new Media(musicFile);
        backgroundMusicPlayer = new MediaPlayer(media);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusicPlayer.play();
        user = User.getInstance("Player");
        gameLogic = new GameLogic(user);
        user.setHeart(user.MAX_HEART);
        user.setScore(user.MIN_SCORE);

        unMuteButton.setVisible(false);
        scoreLabel.setText(Integer.toString(user.getScore()).concat(" / 15"));
        exercise = gameLogic.getNextExercise();
        if (exercise != null) {
            exerciseTextArea.setText(exercise.getQuestion());
            optionAButton.setText(exercise.getOptionA());
            optionBButton.setText(exercise.getOptionB());
            optionCButton.setText(exercise.getOptionC());
            optionDButton.setText(exercise.getOptionD());
        }

        correctSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/sound/correct.mp3")).toString());
        incorrectSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/sound/incorrect.wav")).toString());

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), this::editTimeProgress));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void playSound(boolean correct) {
        if (correct) {
            correctSound.play();
        } else {
            incorrectSound.play();
        }
    }

    @FXML
    public void chooseAOption(ActionEvent actionEvent) throws IOException {
        if (!isChosen) {
            String selectedOption = optionAButton.getText();
            if(gameLogic.checkAnswer(exercise, selectedOption)) {
                gameLogic.increaseScore();
                scoreLabel.setText(Integer.toString(user.getScore()).concat(" / 15"));
                optionAButton.getStyleClass().add("correct");
            } else {
                optionAButton.getStyleClass().add("incorrect");
                gameLogic.decreaseHeart();
                int pos = user.getHeart();
                heartHBox.getChildren().get(pos).setVisible(false);
                if (pos == 0) {
                    gameOver();
                }
            }
            playSound(gameLogic.checkAnswer(exercise, selectedOption));
            isChosen = true;
        }
    }

    @FXML
    public void chooseBOption(ActionEvent actionEvent) throws IOException {
        if (!isChosen) {
            String selectedOption = optionBButton.getText();
            if(gameLogic.checkAnswer(exercise, selectedOption)) {
                gameLogic.increaseScore();
                scoreLabel.setText(Integer.toString(user.getScore()).concat(" / 15"));
                optionBButton.getStyleClass().add("correct");
            } else {
                optionBButton.getStyleClass().add("incorrect");
                gameLogic.decreaseHeart();
                int pos = user.getHeart();
                heartHBox.getChildren().get(pos).setVisible(false);
                if (pos == 0) {
                    gameOver();
                }
            }
            playSound(gameLogic.checkAnswer(exercise, selectedOption));
            isChosen = true;
        }
    }

    @FXML
    public void chooseCOption(ActionEvent actionEvent) throws IOException {
        if (!isChosen) {
            String selectedOption = optionCButton.getText();
            if(gameLogic.checkAnswer(exercise, selectedOption)) {
                optionCButton.getStyleClass().add("correct");
                gameLogic.increaseScore();
                scoreLabel.setText(Integer.toString(user.getScore()).concat(" / 15"));
            } else {
                optionCButton.getStyleClass().add("incorrect");
                gameLogic.decreaseHeart();
                int pos = user.getHeart();
                heartHBox.getChildren().get(pos).setVisible(false);
                if (pos == 0) {
                    gameOver();
                }
            }
            playSound(gameLogic.checkAnswer(exercise, selectedOption));
            isChosen = true;
        }
    }

    @FXML
    public void chooseDOption(ActionEvent actionEvent) throws IOException {
        if (!isChosen) {
            String selectedOption = optionDButton.getText();
            if(gameLogic.checkAnswer(exercise, selectedOption)) {
                gameLogic.increaseScore();
                scoreLabel.setText(Integer.toString(user.getScore()).concat(" / 15"));
                optionDButton.getStyleClass().add("correct");
            } else {
                optionDButton.getStyleClass().add("incorrect");
                gameLogic.decreaseHeart();
                int pos = user.getHeart();
                heartHBox.getChildren().get(pos).setVisible(false);
                if (pos == 0) {
                    gameOver();
                }
            }
            playSound(gameLogic.checkAnswer(exercise, selectedOption));
            isChosen = true;
        }
    }

    @FXML
    public void moreTimeHelp(ActionEvent actionEvent) {
        if(!isChosen) {
            timeElapsed = -1;
            timeline.play();
            moreTimeHelpButton.setDisable(true);
        }
    }

    private void removeAnswerFromButtons(String removedAnswer) {
        if (optionAButton.getText().equals(removedAnswer)) {
            optionAButton.setText("");
        }
        if (optionBButton.getText().equals(removedAnswer)) {
            optionBButton.setText("");
        }
        if (optionCButton.getText().equals(removedAnswer)) {
            optionCButton.setText("");
        }
        if (optionDButton.getText().equals(removedAnswer)) {
            optionDButton.setText("");
        }
    }

    @FXML
    public void fiftyFiftyHelp(ActionEvent actionEvent) {
        if (!isChosen) {
            List<String> wrongAnswers = gameLogic.getTwoRandomWrongAnswers(exercise);
            String removedAnswer1 = wrongAnswers.get(0);
            String removedAnswer2 = wrongAnswers.get(1);
            removeAnswerFromButtons(removedAnswer1);
            removeAnswerFromButtons(removedAnswer2);
            fiftyFiftyHelpButton.setDisable(true);
        }
    }

    @FXML
    public void consultHelp(ActionEvent actionEvent) {
        if (!isChosen) {
            List<String> recommendedAnswers = gameLogic.getThreeRandomAnswersWithAtLeastOneCorrect(exercise);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Consult Help");
            alert.setHeaderText("Recommended Answers");
            alert.setContentText("1. " + recommendedAnswers.get(0) + "\n"
                    + "2. " + recommendedAnswers.get(1) + "\n"
                    + "3. " + recommendedAnswers.get(2));
            alert.showAndWait();
            consultHelpButton.setDisable(true);
        }
    }

    @FXML
    public void moreHeartHelp(ActionEvent actionEvent) {
        if (user.getHeart() < user.MAX_HEART) {
            user.increaseHeart();
            int pos = user.getHeart();
            heartHBox.getChildren().get(pos - 1).setVisible(true);
            moreHeartHelpButton.setDisable(true);
        }
    }

    @FXML
    public void unMute(ActionEvent actionEvent) {
        muteButton.setVisible(true);
        unMuteButton.setVisible(false);

        Duration pausedTime = backgroundMusicPlayer.getCurrentTime();
        backgroundMusicPlayer.seek(pausedTime);
        backgroundMusicPlayer.play();
    }

    @FXML
    public void mute(ActionEvent actionEvent) {
        muteButton.setVisible(false);
        unMuteButton.setVisible(true);
        backgroundMusicPlayer.pause();
    }

    @FXML
    public void editTimeProgress(ActionEvent mouseEvent) {
        if (!isChosen) {
            timeElapsed += 1;
            double progress = timeElapsed / duration.toSeconds();
            if (progress >= 1.0) {
                progress = 1.0;
                if (!isChosen) {
                    gameLogic.decreaseHeart();
                    int pos = user.getHeart();
                    heartHBox.getChildren().get(pos).setVisible(false);
                    playSound(false);
                    isChosen = true;
                    timeline.stop();
                    if (pos == 0) {
                        try {
                            gameOver();
                        } catch(IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            timeProgressBar.setProgress(progress);
        } else {
            timeline.stop();
        }
    }

    @FXML
    public void nextExercise(ActionEvent actionEvent) {
        timeElapsed = -1;
        timeline.stop();
        timeline.play();

        exercise = gameLogic.getNextExercise();
        if (exercise != null) {

            exerciseTextArea.setText(exercise.getQuestion());
            optionAButton.getStyleClass().removeAll("correct", "incorrect");
            optionBButton.getStyleClass().removeAll("correct", "incorrect");
            optionCButton.getStyleClass().removeAll("correct", "incorrect");
            optionDButton.getStyleClass().removeAll("correct", "incorrect");

            optionAButton.setText(exercise.getOptionA());
            optionBButton.setText(exercise.getOptionB());
            optionCButton.setText(exercise.getOptionC());
            optionDButton.setText(exercise.getOptionD());
        }
        isChosen = false;
    }

    @FXML
    public void quitGame(ActionEvent actionEvent) throws IOException {
        backgroundMusicPlayer.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameStart.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }

    private void gameOver() throws IOException {
        backgroundMusicPlayer.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameOver.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }
}
