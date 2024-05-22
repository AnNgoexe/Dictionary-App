package com.example.dictionary;

import base.dictionary.Dictionary;
import base.dictionary.MyDictionary;
import base.history.SearchHistory;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML
    private ListView<String> historyListView;

    @FXML
    private Button historyButton;

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
                if(!a.isEmpty()) {
                    int maxRow = 9;
                    int limit = Math.min(maxRow, a.size());
                    List<String> limitedList = a.stream().limit(limit).toList();
                    ObservableList<String> observableList = FXCollections.observableArrayList(limitedList);
                    listWord.setItems(observableList);
                    listWord.setPrefHeight(listWord.getItems().size() * 24 + 2);
                    listWord.setVisible(true);
                } else {
                    listWord.setVisible(false);
                    listWord.setItems(null);
                }
            } else {
                listWord.setVisible(false);
                listWord.setItems(null);
            }
        });

        listWord.setOnMouseClicked(event -> {
            if (listWord.getSelectionModel().getSelectedItem() != null) {
                String selectedWord = listWord.getSelectionModel().getSelectedItem();
                searchBar.setText(selectedWord);
                listWord.setVisible(false);
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
            loadViewSearch(wordTarget);
        } else {
            searchAnchorPane.getChildren().setAll(new AnchorPane());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification!");
            alert.setHeaderText("Non-existence:");
            alert.setContentText("This word does not exist in the dictionary.");
            alert.showAndWait();
        }
        listWord.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listWordView();
        listHistoryView();
        listWord.setVisible(false);

        List<String> wordHistory = SearchHistory.getHistory().getWordHistory();
        ObservableList<String> observableHistory = FXCollections.observableArrayList(wordHistory);
        historyListView.setItems(observableHistory);
    }

    @FXML
    public void resetHistory(ActionEvent actionEvent) {
        SearchHistory.getHistory().resetHistory();
        historyListView.getItems().clear();
    }

    @FXML
    public void listHistoryView() {
        historyListView.setOnMouseClicked(event -> {
            if (historyListView.getSelectionModel().getSelectedItem() != null) {
                String selectedWord = historyListView.getSelectionModel().getSelectedItem();
                searchBar.setText(selectedWord);
                try {
                    loadViewSearch(selectedWord);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void loadViewSearch(String wordTarget) throws IOException {
        String wordExplain = Dictionary.getDictionary().getWord(wordTarget);

        this.loader = new FXMLLoader(getClass().getResource("viewSearch.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        searchAnchorPane.getChildren().setAll(secondaryAnchorPane);

        ViewSearchController viewSearchController = loader.getController();
        viewSearchController.setWord(wordTarget, wordExplain);
        viewSearchController.viewWordController();

        boolean isFavorite = MyDictionary.getDictionary().searchWord(wordTarget.trim());
        viewSearchController.updateFavoriteButtonState(isFavorite);

        SearchHistory.getHistory().addWordForHistory(wordTarget);
        ObservableList<String> historyList = historyListView.getItems();
        historyList.remove(wordTarget);
        historyList.add(0, wordTarget);
        historyListView.setItems(historyList);
    }
}