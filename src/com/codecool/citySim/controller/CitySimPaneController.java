package com.codecool.citySim.controller;

import com.codecool.citySim.model.cars.Car;
import com.codecool.citySim.model.roads.Road;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.QuadCurve;

import java.net.URL;
import java.util.ResourceBundle;

public class CitySimPaneController implements Initializable {
    @FXML
    public Pane pane;
    public Road horizontalRightFirst;
    public Road horizontalRightSecond;
    public Road horizontalLeftFirst;
    public Road horizontalLeftSecond;
    public Road verticalDownFirst;
    public Road verticalDownSecond;
    public Road verticalUpFirst;
    public Road verticalUpSecond;
    public QuadCurve kurva;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Car car = new Car(-360, 8);
        car.setRotate(90);
        pane.getChildren().add(car);

    }
}
