package com.controller;

import base.Dictionary;
import base.MyDictionary;
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

public class ExportToFileController {
    @FXML
    private TextField dictionaryExportToFileBar;

    @FXML
    private TextField myDictionaryExportToFileBar;

    @FXML
    private Button dictionaryExportToFileButton;

    @FXML
    private Button myDictionaryExportToFileButton;

    @FXML
    private AnchorPane fileChooseView;

    @FXML
    private Tooltip selectDictionaryToolTip;

    @FXML
    private Tooltip selectMyDictionaryToolTip;

    void listToolTip() {
        selectDictionaryToolTip.setShowDelay(Duration.ZERO);
        selectMyDictionaryToolTip.setShowDelay(Duration.ZERO);
    }

    @FXML
    void chooseFileDictionary(ActionEvent event){
        String originalColor = dictionaryExportToFileButton.getStyle();
        dictionaryExportToFileButton.setStyle("-fx-background-color: #87CEEB");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> dictionaryExportToFileButton.setStyle(originalColor));
        pause.play();

        Stage stage =(Stage) fileChooseView.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose a file.txt");
        FileChooser.ExtensionFilter txtFiler = new FileChooser.ExtensionFilter("Txt Files","*.txt");
        fc.getExtensionFilters().add(txtFiler);
        File file = fc.showOpenDialog(stage);

        Dictionary.getDictionary().dictionaryExportToFile(file.getPath());
    }

    @FXML
    void chooseFileMyDictionary(ActionEvent event){
        String originalColor = selectMyDictionaryToolTip.getStyle();
        selectMyDictionaryToolTip.setStyle("-fx-background-color: #87CEEB");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> selectMyDictionaryToolTip.setStyle(originalColor));
        pause.play();

        Stage stage =(Stage) fileChooseView.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose a file.txt");
        FileChooser.ExtensionFilter txtFiler = new FileChooser.ExtensionFilter("Txt Files","*.txt");
        fc.getExtensionFilters().add(txtFiler);
        File file = fc.showOpenDialog(stage);

        MyDictionary.getDictionary().dictionaryExportToFile(file.getPath());
    }
}
