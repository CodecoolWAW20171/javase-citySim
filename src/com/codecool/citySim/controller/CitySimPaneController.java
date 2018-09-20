package com.codecool.citySim.controller;

import com.codecool.citySim.model.Simulation;
import com.codecool.citySim.model.cars.Car;
import com.codecool.citySim.model.lights.CrossRoadLights;
import com.codecool.citySim.model.roads.Road;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class CitySimPaneController {
    @FXML
    public Pane pane;
    private Simulation sim = new Simulation();
    private CrossRoadLights crossRoadLights;
    private LightController lightController;
    private Road road1 = sim.getFirstRoads()[0];
    private PathGenerator pathy = new PathGenerator(road1);

    public void initialize() {
        crossRoadLights = new CrossRoadLights();
        lightController = new LightController(pane, crossRoadLights);
        Car car = new Car(road1.getStartX(), road1.getStartY());
        VehicleController movingCar = new VehicleController(car, road1);
        pane.getChildren().addAll(car.getImage());

        PathTransition pathTransition = new PathTransition(Duration.seconds(3), pathy.newTurn, car.getImage());
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        pathTransition.setOnFinished(event -> new Thread(() -> {
            while(true) {
                System.out.println("Car X Y: " + car.getX() + " " + car.getY() + "\n" +
                        "CarImg X Y: " + car.getImage().getTranslateX() + " " + car.getImage().getTranslateY() + "\n" +
                        "CarImg X Y: " + car.getImage().getX() + " " + car.getImage().getY());

                try {
                    movingCar.moveTheCar();
                    TimeUnit.MILLISECONDS.sleep(1000);
                    movingCar.setCarsXY(car);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start());

        pathTransition.play();

    }
}
