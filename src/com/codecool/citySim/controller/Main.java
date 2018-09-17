package com.codecool.citySim.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Globals.pane = FXMLLoader.load(getClass().getResource("../view/citySimPane.fxml"));
        LightController lightController = new LightController();

        Thread thread = new Thread(lightController);
        thread.start();
        Scene scene = new Scene(Globals.pane, 1280, 720);
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Koniec");
            lightController.stop();
        });
        primaryStage.setTitle("CitySim");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
