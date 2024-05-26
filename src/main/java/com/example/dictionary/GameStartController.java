package com.example.dictionary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class GameStartController implements Initializable {
    @FXML
    private AnchorPane viewContainer;

    @FXML
    private Button playButton;

    private MediaPlayer mediaPlayer;

    @FXML
    public void startGame(ActionEvent actionEvent) throws IOException {
        mediaPlayer.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String musicFile = Paths.get("src/main/resources/sound/intro.mp3").toUri().toString();
        Media media = new Media(musicFile);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}
