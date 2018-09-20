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
                    while (road.getVehicles().contains(car)) {
                        vc.moveTheCar();
                        TimeUnit.MILLISECONDS.sleep(1000);
                        vc.setCarsXY(car);
                        if (
                                Math.abs(car.getX() - road.getEndX()) < 45 &&
                                Math.abs(car.getY() - road.getEndY()) < 45 &&
                                car.equals(road.getVehicles().getFirst())
                        ) {
                            PathGenerator pathGenerator = new PathGenerator(car, road);
                            PathTransition move = new PathTransition(Duration.seconds(1), pathGenerator.newTurn, car.getImage());
                            move.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                            move.setOnFinished(e -> {
                                Road secRoad = pathGenerator.chosenRoad;
                                vc.setBasicRoad(secRoad);
                                road.getVehicles().remove(car);
                                while (secRoad.getVehicles().contains(car)) {
                                    vc.moveTheCar();
                                    try {
                                        TimeUnit.MILLISECONDS.sleep(1000);
                                    } catch (InterruptedException e1) {
                                        e1.printStackTrace();
                                    }
                                    vc.setCarsXY(car);
                                }
                            });
                            move.play();
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
