module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.net.http;
    requires java.sql;
    requires freetts;
    requires json;
    requires mongo.java.driver;
    requires javafx.media;

    opens com.example.dictionary to javafx.fxml;
    exports com.example.dictionary;
}