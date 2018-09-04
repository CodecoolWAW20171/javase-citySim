package com.codecool.citySim.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../com/codecool/citySim/view/sample.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root, 1920, 1020));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
