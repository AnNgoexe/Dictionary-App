package com.controller;

import base.MyDictionary;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FavoriteController implements Initializable {
    FXMLLoader loader;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField searchFavoriteBar;

    @FXML
    private ListView<String> listFavoriteWord;

    @FXML
    private Tooltip searchFavoriteToolTip;

    @FXML
    private AnchorPane searchFavoriteAnchorPane;

    @FXML
    private Button searchFavoriteButton;

    public void listToolTip() {
        searchFavoriteToolTip.setShowDelay(Duration.ZERO);
    }

    public void setCurrentListWord(ObservableList<String> observableList) {
        this.listFavoriteWord.setItems(observableList);
    }

    @FXML
    public void listFavoriteWordView() {
        searchFavoriteBar.addEventHandler(KeyEvent.KEY_RELEASED, event->{
            String word = searchFavoriteBar.getText();
            if(!word.trim().isEmpty()){
                List<String> a = MyDictionary.getDictionary().getWordsStartingWith(word);
                ObservableList<String> observableList = FXCollections.observableArrayList(a);
                listFavoriteWord.setItems(observableList);
            } else {
                listFavoriteWord.setItems(null);
            }
        });

        listFavoriteWord.setOnMouseClicked(event -> {
            if (listFavoriteWord.getSelectionModel().getSelectedItem() != null) {
                String selectedWord = listFavoriteWord.getSelectionModel().getSelectedItem();
                searchFavoriteBar.setText(selectedWord);
            }
        });
    }

    @FXML
    public void searchFavoriteWord(ActionEvent event) throws IOException {
        String originalColor = searchFavoriteButton.getStyle();
        searchFavoriteButton.setStyle("-fx-background-color: #87CEEB");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> searchFavoriteButton.setStyle(originalColor));
        pause.play();

        String wordTarget = searchFavoriteBar.getText().trim();
        if (MyDictionary.getDictionary().searchWord(wordTarget)) {
            String wordExplain = MyDictionary.getDictionary().getWord(wordTarget);

            this.loader = new FXMLLoader(getClass().getResource("viewFavor.fxml"));
            AnchorPane secondaryAnchorPane = loader.load();
            searchFavoriteAnchorPane.getChildren().setAll(secondaryAnchorPane);

            ViewFavoriteController viewFavoriteController = loader.getController();
            viewFavoriteController.setWord(wordTarget, wordExplain);
            viewFavoriteController.viewWordController();
        } else {
            searchFavoriteAnchorPane.getChildren().setAll(new AnchorPane());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listFavoriteWordView();
    }
}
