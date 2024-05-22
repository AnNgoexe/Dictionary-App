package com.example.dictionary;

import base.dictionary.Dictionary;
import base.dictionary.MyDictionary;
import base.history.FavoriteHistory;
import base.history.SearchHistory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DictionaryApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Dictionary.getDictionary().insertFromFile();
        MyDictionary.getDictionary().insertFromFile();
        SearchHistory.getHistory().insertFromFile();
        FavoriteHistory.getHistory().insertFromFile();

        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApplication.class.getResource("entrance.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("English - Vietnamese dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        FavoriteHistory.getHistory().writeToFile();
    }

}
