package com.example.dictionary;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import java.util.List;

import api.SynonymAntonym;

public class ShowASWordController {
    @FXML
    public Tooltip searchTooltip;

    @FXML
    private ListView<String> SynonymsListView;

    @FXML
    private ListView<String> AntonymListView;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchBar;

    @FXML
    public void searchSynonymAntonym(ActionEvent actionEvent) {
        String originalColor = searchButton.getStyle();
        searchButton.setStyle("-fx-background-color: #87CEEB");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> searchButton.setStyle(originalColor));
        pause.play();

        String wordTarget = searchBar.getText().trim();

        List<String> syn = SynonymAntonym.getSynonyms(wordTarget);
        if(syn != null) {
            ObservableList<String> observableList1 = FXCollections.observableArrayList(syn);
            SynonymsListView.setItems(observableList1);
        } else {
            SynonymsListView.setItems(null);
        }

        List<String> ant = SynonymAntonym.getAntonyms(wordTarget);
        if(ant != null) {
            ObservableList<String> observableList2 = FXCollections.observableArrayList(ant);
            AntonymListView.setItems(observableList2);
        } else {
            AntonymListView.setItems(null);
        }
    }

    public void listToolTip() {
        searchTooltip.setShowDelay(Duration.ZERO);
    }
}
