package com.codecool.citySim.controller;

import com.codecool.citySim.model.cars.Car;
import com.codecool.citySim.model.roads.Road;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CitySimPaneController implements Initializable {
    @FXML
    public Pane pane;
    public Road horizontalLeft;
    public Road horizontalRight;
    public Road verticalUp;
    public Road verticalDown;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Car car = new Car(20, -360);
        Car car1 = new Car(20, -300);
        Car car2 = new Car(20, -240);
        Car car3 = new Car(20, -180);
        pane.getChildren().add(car);
        pane.getChildren().add(car1);
        pane.getChildren().add(car2);
        pane.getChildren().add(car3);
    }
}
