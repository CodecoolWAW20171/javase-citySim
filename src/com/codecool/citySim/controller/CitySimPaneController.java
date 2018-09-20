package com.codecool.citySim.controller;

import com.codecool.citySim.model.Simulation;
import com.codecool.citySim.model.cars.Car;
import com.codecool.citySim.model.lights.CrossRoadLights;
import com.codecool.citySim.model.roads.Road;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

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
                    if (sim.getVehicles() < 30)
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
            Road road = sim.getFirstRoads()[random.nextInt(4)];
            Car car = new Car(road.getStartX(), road.getStartY());
            Platform.runLater(() -> pane.getChildren().addAll(car.getImage()));

            new Thread(() -> {
                VehicleController vc = new VehicleController(car, road);
                while (road.getVehicles().indexOf(car) != -1) {
                    try {
                        vc.moveTheCar();
                        TimeUnit.MILLISECONDS.sleep(1000);
                        vc.setCarsXY(car);
//                        if (Math.abs(car.getX() - road.getEndX()) < 35 && Math.abs(car.getY() - road.getEndY()) < 35)
//                            road.getVehicles().remove(car);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
