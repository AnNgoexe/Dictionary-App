package com.example.dictionary;

import api.AudioPlay;
import base.Dictionary;
import base.MyDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ViewSearchController {
    private String wordTarget;

    private String wordExplain;

    private boolean isFavorite;

    @FXML
    private Button soundButton;

    @FXML
    private Tooltip pronunciationToolTip;

    @FXML
    private Button editButton;

    @FXML
    private Tooltip editToolTip;

    @FXML
    private Button removeButton;

    @FXML
    private Tooltip removeToolTip;

    @FXML
    private Button favoriteButton;

    @FXML
    private Tooltip favoriteToolTip;

    @FXML
    private WebView webView;

    @FXML
    private AnchorPane viewSearchAnchorPane;

    public void listToolTip() {
        favoriteToolTip.setShowDelay(Duration.ZERO);
        removeToolTip.setShowDelay(Duration.ZERO);
        pronunciationToolTip.setShowDelay(Duration.ZERO);
        editToolTip.setShowDelay(Duration.ZERO);
    }

    public void setWord(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    public String getWord() {
        return this.wordTarget + "\n" + this.wordExplain;
    }

    public void updateFavoriteButtonState(boolean isFavorite) {
        if(isFavorite) {
            this.isFavorite = true;
            this.favoriteButton.setStyle("-fx-background-color: rgb(209, 5, 39);");
        } else {
            this.isFavorite = false;
            favoriteButton.setStyle("-fx-background-color: transparent;");
        }
    }

    @FXML
    public void playSound(ActionEvent event) throws Exception {
        AudioPlay.generateSpeech(wordTarget.trim());
    }

    @FXML
    public void setFavoriteWord(ActionEvent event) {
        if(isFavorite) {
            MyDictionary.getDictionary().removeWord(wordTarget);
            isFavorite = false;
            favoriteButton.setStyle("-fx-background-color: transparent;");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trạng thái xóa khỏi danh sách các từ đã học:");
            alert.setContentText("Đã xóa thành công !");
            alert.showAndWait();
        } else {
            MyDictionary.getDictionary().addWord(wordTarget, wordExplain);
            isFavorite = true;
            favoriteButton.setStyle("-fx-background-color: rgb(209, 5, 39);");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trạng thái thêm vào danh sách các từ đã học:");
            alert.setContentText("Đã thêm thành công !");
            alert.showAndWait();
        }
    }

    @FXML
    public void removeWord(ActionEvent event) throws IOException {
        Dictionary.getDictionary().removeWord(wordTarget);
        viewSearchAnchorPane.getChildren().clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Trạng thái xóa từ vừa tra cứu ra khỏi từ điển:");
        alert.setContentText("Đã xóa thành công !");
        alert.showAndWait();
    }

    @FXML
    public void editWord(ActionEvent event) {
        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setHtmlText(wordExplain);
        Scene scene = new Scene(htmlEditor, 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();

        String newWordExplain = htmlEditor.getHtmlText().trim();
        webView.getEngine().loadContent(newWordExplain);

        Dictionary.getDictionary().updateWord(wordTarget, newWordExplain);
        MyDictionary.getDictionary().updateWord(wordTarget, newWordExplain);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Thông báo:");
        alert.setContentText("Bạn đã sửa thành công.");
        alert.showAndWait();
    }

    @FXML
    public void viewWordController() {
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(this.wordExplain);
    }
}
