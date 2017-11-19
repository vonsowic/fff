package com.dim.fff.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public class App extends Application{

    private Controller controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainview.fxml"));
        primaryStage.setTitle("Find Future Friends");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
