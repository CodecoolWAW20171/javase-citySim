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
        Car car = new Car();
        pane.getChildren().add(car);
        System.out.println(verticalUp.getEndX() + " " + verticalUp.getStartY());
    }
}
