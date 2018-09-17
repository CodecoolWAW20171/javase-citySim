package com.codecool.citySim.controller;

import com.codecool.citySim.model.cars.Car;
import com.codecool.citySim.model.roads.Road;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class CitySimPaneController implements Initializable {
    @FXML
    public Pane pane;

    public Road horizontalRightFirst;
    public Road horizontalRightSecond;
    public QuadCurve leftRightTurn;
    public QuadCurve leftLeftTurn;
    public Road leftStraight;

    public Road horizontalLeftFirst;
    public Road horizontalLeftSecond;
    public QuadCurve rightRightTurn;
    public QuadCurve rightLeftTurn;
    public Road rightStraight;

    public Road verticalDownFirst;
    public Road verticalDownSecond;
    public QuadCurve downRightTurn;
    public QuadCurve downLeftTurn;
    public Road downStraight;

    public Road verticalUpFirst;
    public Road verticalUpSecond;
    public QuadCurve upRightTurn;
    public QuadCurve upLeftTurn;
    public Road upStraight;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Car car = new Car(-640, 8);
        pane.getChildren().add(car);
        VehicleController movingCar = new VehicleController(car, horizontalRightFirst);
        movingCar.moveTheCar(car, horizontalRightFirst);
    }
}
