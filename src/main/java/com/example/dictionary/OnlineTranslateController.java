package com.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import api.GoogleTranslate;
import java.io.IOException;

public class OnlineTranslateController {

    private boolean enToVi = true;

    @FXML
    private Button englishToVietnamButton;

    @FXML
    private Button vietnamToEnglishButton;

    @FXML
    private Label outputLabel;

    @FXML
    private TextArea inputTextArea;

    @FXML
    public void vietnameseToEnglishTranslate() throws IOException {
        String input = inputTextArea.getText().trim();
        enToVi = false;
        String output = GoogleTranslate.translate("vi", "en", input);
        outputLabel.setText(output);
    }

    @FXML
    public void englishToVietnameseTranslate() throws IOException {
        String input = inputTextArea.getText().trim();
        enToVi = true;
        String output = GoogleTranslate.translate("en", "vi", input);
        outputLabel.setText(output);
    }
}
