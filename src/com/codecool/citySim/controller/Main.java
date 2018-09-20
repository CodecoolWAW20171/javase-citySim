package com.codecool.citySim.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/citySimPane.fxml"));
        SoundController souondController = new SoundController();
        primaryStage.setTitle("CitySim");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
        souondController.playTraffic.play();
        primaryStage.setOnCloseRequest(e -> System.exit(0));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
