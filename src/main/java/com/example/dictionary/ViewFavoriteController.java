package com.example.dictionary;

import api.AudioPlay;
import base.dictionary.MyDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.io.IOException;

public class ViewFavoriteController {
    private String wordTarget;

    private String wordExplain;

    @FXML
    public Button removeButton;

    @FXML
    public Tooltip removeToolTip;

    @FXML
    private Button soundButton;

    @FXML
    private Tooltip pronunciationToolTip;

    @FXML
    public WebView webView;

    @FXML
    private AnchorPane viewFavoriteAnchorPane;

    public void listToolTip() {
        pronunciationToolTip.setShowDelay(Duration.ZERO);
        removeToolTip.setShowDelay(Duration.ZERO);
    }

    @FXML
    public void playSound(ActionEvent actionEvent) {
        AudioPlay.generateSpeech(wordTarget.trim());
    }

    @FXML
    public void removeWord(ActionEvent event) throws IOException {
        MyDictionary.getDictionary().removeWord(wordTarget);
        viewFavoriteAnchorPane.getChildren().clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification!");
        alert.setHeaderText("Removing a favorite word:");
        alert.setContentText("Successfully deleted a favorite word from the dictionary.");
        alert.showAndWait();
    }

    public void setWord(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    public String getWord() {
        return this.wordTarget + "\n" + this.wordExplain;
    }

    @FXML
    public void viewWordController() {
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(this.wordExplain);
    }
}
