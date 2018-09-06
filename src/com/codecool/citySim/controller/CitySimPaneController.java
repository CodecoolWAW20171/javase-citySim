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
    public QuadCurve leftRightTurn;
    public QuadCurve leftLeftTurn;
    public Road leftStraight;

    public QuadCurve rightRightTurn;
    public QuadCurve rightLeftTurn;
    public Road rightStraight;

    public QuadCurve upRightTurn;
    public QuadCurve upLeftTurn;
    public Road upStraight;

    public QuadCurve downRightTurn;
    public QuadCurve downLeftTurn;
    public Road downStraight;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Car car = new Car(-360, 8);
        car.setRotate(90);
        pane.getChildren().add(car);

    }
}
