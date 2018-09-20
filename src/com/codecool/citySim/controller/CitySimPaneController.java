package com.codecool.citySim.controller;

import com.codecool.citySim.model.Simulation;
import com.codecool.citySim.model.cars.Car;
import com.codecool.citySim.model.lights.CrossRoadLights;
import com.codecool.citySim.model.roads.Road;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CitySimPaneController {
    @FXML
    public Pane pane;
    private Simulation sim = new Simulation();
    private CrossRoadLights crossRoadLights;
    private LightController lightController;

    public void initialize() {
        crossRoadLights = new CrossRoadLights();
        lightController = new LightController(pane, crossRoadLights);

        Thread thread = new Thread(lightController);
        thread.start();

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    if (sim.getVehicles() < 2)
                        carGenerator();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void carGenerator() {
        Random random = new Random();

        if (random.nextInt(100) < 50) {
            sim.setVehicles(sim.getVehicles() + 1);
            Road road = sim.getFirstRoads()[random.nextInt(1)];
            Car car = new Car(road.getStartX(), road.getStartY());
            Platform.runLater(() -> pane.getChildren().addAll(car.getImage()));

            new Thread(() -> {
                VehicleController vc = new VehicleController(car, road, crossRoadLights);
                try {
                    while (car.getImage() != null) {
                        vc.moveTheCar();
                        vc.setCarsXY(car);
                        TimeUnit.MILLISECONDS.sleep(1000);

                        if (
                                Math.abs(car.getX() - vc.getBasicRoad().getEndX()) < 45 &&
                                Math.abs(car.getY() - vc.getBasicRoad().getEndY()) < 45 &&
                                car.equals(vc.getBasicRoad().getVehicles().getFirst())
                        ) {
                            PathGenerator pathGenerator = new PathGenerator(car, road);
                            PathTransition move = new PathTransition(Duration.seconds(2), pathGenerator.newTurn, car.getImage());
                            System.out.println(pathGenerator.newTurn);
                            move.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                            vc.setBasicRoad(pathGenerator.chosenRoad);
                            car.getImage().setLayoutX(0);
                            car.getImage().setLayoutY(0);
                            move.play();
                            TimeUnit.MILLISECONDS.sleep(2100);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}