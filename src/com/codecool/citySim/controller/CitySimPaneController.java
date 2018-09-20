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

    private Thread thread;


    public void initialize() {

        crossRoadLights = new CrossRoadLights();
        lightController = new LightController(pane, crossRoadLights);
        thread = new Thread(lightController);
        thread.start();

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
                VehicleController vc = new VehicleController(car, road, crossRoadLights);
                boolean end = false;

                try {
                    while (car.getImage() != null) {
                        vc.moveTheCar();
                        vc.setCarsXY(car);
                        TimeUnit.MILLISECONDS.sleep(1000);

                        int distanceToNextTurn = 45;
                        if (!end &&
                                Math.abs(car.getX() - road.getEndX()) < distanceToNextTurn &&
                                Math.abs(car.getY() - road.getEndY()) < distanceToNextTurn &&
                                car.equals(road.getVehicles().getFirst())
                        ) {
                            end = true;
                            PathGenerator pathGenerator = new PathGenerator(car, road);
                            PathTransition move = new PathTransition(Duration.seconds(2), pathGenerator.newTurn, car.getImage());
                            move.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                            road = pathGenerator.chosenRoad;
                            vc.setBasicRoad(road);
                            car.getImage().setLayoutX(0);
                            car.getImage().setLayoutY(0);
                            move.play();
                            TimeUnit.MILLISECONDS.sleep(1500);
                        }

                        int distanceToTheEdgeOfPane = 20;
                        if (end && Math.abs(car.getX() - vc.getBasicRoad().getEndX()) < distanceToTheEdgeOfPane &&
                                Math.abs(car.getY() - vc.getBasicRoad().getEndY()) < distanceToTheEdgeOfPane &&
                                car.equals(vc.getBasicRoad().getVehicles().getFirst())) {
                            vc.getBasicRoad().getVehicles().remove(car);
                            Platform.runLater(() -> pane.getChildren().remove(car.getImage()));
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (NullPointerException nul) {
                    nul.printStackTrace();
                }
            }).start();
        }
    }
}