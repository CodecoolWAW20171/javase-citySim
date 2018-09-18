package com.codecool.citySim.controller;

import com.codecool.citySim.model.Simulation;
import com.codecool.citySim.model.cars.Car;
import com.codecool.citySim.model.roads.Road;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.concurrent.TimeUnit;

public class CitySimPaneController {
    @FXML
    public Pane pane;
    private Simulation sim = new Simulation();
    private Road road1 = sim.getFirstRoads()[0];

    public void initialize() {
        Car car = new Car(road1.getStartX(), road1.getStartY());
        pane.getChildren().add(car.getImage());
        VehicleController movingCar = new VehicleController(car, road1);
        new Thread(() -> {
            while (car != null) {
                movingCar.moveTheCar(car, road1);
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
//                    movingCar.setCarsXY(car);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
