package com.controller;

import base.Dictionary;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class ImportFromFileController {
    @FXML
    public AnchorPane fileChooseView;

    @FXML
    public Button dictionaryImportFromFileButton;

    @FXML
    public TextField dictionaryImportFromFileBar;

    @FXML
    public Tooltip selectFileToolTip;

    void listToolTip() {
        selectFileToolTip.setShowDelay(Duration.ZERO);
    }

    @FXML
    public void chooseFileDictionary(ActionEvent actionEvent) {
        String originalColor = dictionaryImportFromFileButton.getStyle();
        dictionaryImportFromFileButton.setStyle("-fx-background-color: #87CEEB");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> dictionaryImportFromFileButton.setStyle(originalColor));
        pause.play();

        Stage stage =(Stage) fileChooseView.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose a file.txt");
        FileChooser.ExtensionFilter txtFiler = new FileChooser.ExtensionFilter("Txt Files","*.txt");
        fc.getExtensionFilters().add(txtFiler);
        File file = fc.showOpenDialog(stage);

        Dictionary.getDictionary().dictionaryImportFromFile(file.getPath());
    }
}
