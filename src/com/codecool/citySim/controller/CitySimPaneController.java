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
        Car car = new Car(8, 8);
        pane.getChildren().add(car);

        Path path = new Path();
        MoveTo moveFrom = new MoveTo(-8, 8);
        path.getElements().add(moveFrom);
        path.getElements().add(new QuadCurveTo(upLeftTurn.getControlX(), upLeftTurn.getControlY(),
                upLeftTurn.getEndX(), upLeftTurn.getEndY()));
        path.getElements().add(new LineTo(-100, -8));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(1000));
        pathTransition.setNode(car);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2000), car);
        translateTransition.setByX(200);
        translateTransition.play();

        pathTransition.play();
    }
}
