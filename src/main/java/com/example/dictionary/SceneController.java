package com.example.dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    @FXML
    private Button gameButton;

    @FXML
    private Tooltip gameToolTip;

    @FXML
    private Button ASButton;

    @FXML
    private Tooltip aSToolTip;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button importButton;

    @FXML
    private Tooltip editWordToolTip;

    @FXML
    private AnchorPane viewContainer;

    @FXML
    private Tooltip exportToFileToolTip;

    @FXML
    private Button exportToFIleButton;

    @FXML
    private Tooltip onlineTranslateToolTip;

    @FXML
    private Button addButton;

    @FXML
    private Button onlineTranslateButton;

    @FXML
    private Tooltip addWordToolTip;

    @FXML
    private Button searchButton;

    @FXML
    private Tooltip searchWordToolTip;

    @FXML
    private Button likeButton;

    @FXML
    private Tooltip favoriteToolTip;

    public void listToolTip() {
        addWordToolTip.setShowDelay(Duration.ZERO);
        searchWordToolTip.setShowDelay(Duration.ZERO);
        exportToFileToolTip.setShowDelay(Duration.ZERO);
        editWordToolTip.setShowDelay(Duration.ZERO);
        favoriteToolTip.setShowDelay(Duration.ZERO);
        onlineTranslateToolTip.setShowDelay(Duration.ZERO);
        aSToolTip.setShowDelay(Duration.ZERO);
        gameToolTip.setShowDelay(Duration.ZERO);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listToolTip();
    }

    @FXML
    public void addView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addWord.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().clear();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }

    @FXML
    public void searchView(ActionEvent actionEvent) throws IOException {
        viewContainer.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchWord.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }

    @FXML
    public void onlineTranslateView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("online.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().clear();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }

    @FXML
    public void exportToFileView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("exportToFile.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().clear();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }

    @FXML
    public void importFromFileView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("importFromFile.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().clear();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }

    @FXML
    public void favoriteView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("favorWord.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().clear();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }

    @FXML
    public void synonymsAntonymsView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("showASWord.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().clear();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }

    @FXML
    public void gameView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameStart.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().clear();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }
}
