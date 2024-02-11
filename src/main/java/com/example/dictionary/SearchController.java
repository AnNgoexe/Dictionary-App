package com.example.dictionary;

import base.Dictionary;
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

public class SearchController implements Initializable {
    FXMLLoader loader;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listWord;

    @FXML
    public TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private Tooltip searchToolTip;

    @FXML
    public AnchorPane searchAnchorPane;

    public void listToolTip() {
        searchToolTip.setShowDelay(Duration.ZERO);
    }

    public void setCurrentListWord(ObservableList<String> observableList) {
        this.listWord.setItems(observableList);
    }

    @FXML
    public void listWordView() {
        searchBar.addEventHandler(KeyEvent.KEY_RELEASED,event->{
            String word = searchBar.getText();
            if(!word.trim().isEmpty()){
                List<String> a = Dictionary.getDictionary().getWordsStartingWith(word);
                ObservableList<String> observableList = FXCollections.observableArrayList(a);
                listWord.setItems(observableList);
            } else {
                listWord.setItems(null);
            }
        });

        listWord.setOnMouseClicked(event -> {
            if (listWord.getSelectionModel().getSelectedItem() != null) {
                String selectedWord = listWord.getSelectionModel().getSelectedItem();
                searchBar.setText(selectedWord);
            }
        });
    }

    @FXML
    public void searchWord(ActionEvent event) throws IOException {
        String originalColor = searchButton.getStyle();
        searchButton.setStyle("-fx-background-color: #87CEEB");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> searchButton.setStyle(originalColor));
        pause.play();

        String wordTarget = searchBar.getText().trim();
        if (Dictionary.getDictionary().searchWord(wordTarget)) {
            String wordExplain = Dictionary.getDictionary().getWord(wordTarget);

            this.loader = new FXMLLoader(getClass().getResource("viewSearch.fxml"));
            AnchorPane secondaryAnchorPane = loader.load();
            searchAnchorPane.getChildren().setAll(secondaryAnchorPane);

            ViewSearchController viewSearchController = loader.getController();
            viewSearchController.setWord(wordTarget, wordExplain);
            viewSearchController.viewWordController();

            boolean isFavorite = MyDictionary.getDictionary().searchWord(wordTarget.trim());
            viewSearchController.updateFavoriteButtonState(isFavorite);

        } else {
            searchAnchorPane.getChildren().setAll(new AnchorPane());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listWordView();
    }
}