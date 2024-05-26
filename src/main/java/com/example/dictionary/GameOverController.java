package com.example.dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GameOverController {
    @FXML
    private AnchorPane viewContainer;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    public void rePlay(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }

    public void quit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameStart.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }
}
