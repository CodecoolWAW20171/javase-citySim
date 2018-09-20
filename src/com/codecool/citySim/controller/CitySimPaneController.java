package com.codecool.citySim.controller;

import com.codecool.citySim.model.Simulation;
import com.codecool.citySim.model.cars.Car;
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

    public void initialize() {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    if (sim.getVehicles() < 10)
                        carGenerator();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    System.out.println("Generowanie");
                }
            }
        }).start();
    }

    private void carGenerator() {
        Random random = new Random();

        if (random.nextInt(100) < 50) {
            new Thread(() -> {
                sim.setVehicles(sim.getVehicles() + 1);
                Road road = sim.getFirstRoads()[random.nextInt(4)];
                Car car = new Car(road.getStartX(), road.getStartY());
                Platform.runLater(() -> pane.getChildren().add(car.getImage()));
                VehicleController vc = new VehicleController(car, road);
                boolean end = false;

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
                            if (end) {
                                sim.setVehicles(sim.getVehicles() - 1);
                                vc.getBasicRoad().getVehicles().remove(car);
                                Platform.runLater(() -> pane.getChildren().remove(car.getImage()));
                                break;
                            }
                            end = true;
                            PathGenerator pathGenerator = new PathGenerator(car, road);
                            PathTransition move = new PathTransition(Duration.seconds(3), pathGenerator.newTurn, car.getImage());
                            move.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                            road = pathGenerator.chosenRoad;
                            vc.setBasicRoad(road);
                            car.getImage().setLayoutX(0);
                            car.getImage().setLayoutY(0);
                            move.play();
                            TimeUnit.MILLISECONDS.sleep(1500);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}