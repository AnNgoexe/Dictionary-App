package com.example.dictionary;

import base.Dictionary;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.web.HTMLEditor;
import javafx.util.Duration;

import javafx.event.ActionEvent;

public class AddController {
    @FXML
    private Button saveButton;

    @FXML
    private Tooltip saveToolTip;

    @FXML
    private TextField targetTextField;

    @FXML
    private HTMLEditor wordHTMLEditor;

    void listToolTip() {
        saveToolTip.setShowDelay(Duration.ZERO);
    }

    @FXML
    public void saveWord(ActionEvent event) {
        String originalColor = saveButton.getStyle();
        saveButton.setStyle("-fx-background-color: #FF0000");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> saveButton.setStyle(originalColor));
        pause.play();

        String wordTarget = targetTextField.getText().trim();
        String wordExplain = wordHTMLEditor.getHtmlText().trim();

        if (wordTarget.isEmpty() || wordExplain.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo!");
            alert.setHeaderText("Cảnh báo:");
            alert.setContentText("Bạn phải hoàn thành cả 2 mục để thêm từ thành công");
            alert.showAndWait();
        } else if (Dictionary.getDictionary().searchWord(wordTarget)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo!");
            alert.setHeaderText("Cảnh báo:");
            alert.setContentText("Từ mà bạn muốn thêm đã có trong từ điển.");
            alert.showAndWait();
        } else {
            Dictionary.getDictionary().addWordForDictionary(wordTarget, wordExplain);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Thông báo:");
            alert.setContentText("Bạn đã thêm từ thành công vào từ điển.");
            alert.showAndWait();
        }
    }
}
