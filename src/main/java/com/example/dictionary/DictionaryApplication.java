package com.controller;

import base.Dictionary;
import base.MyDictionary;
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
        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApplication.class.getResource("scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("English - Vietnamese dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
