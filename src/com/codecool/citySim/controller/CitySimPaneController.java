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
        car.getImage().setX(car.getX());
        car.getImage().setY(car.getY());
        pane.getChildren().add(car.getImage());
        VehicleController movingCar = new VehicleController(car, road1);
        new Thread(() -> {
            while (car != null) {
                try {
                    movingCar.moveTheCar(car, road1);
                    TimeUnit.MILLISECONDS.sleep(1000);
                    movingCar.setCarsXY(car);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        LightController lightController = new LightController(pane);

        Thread thread = new Thread(lightController);
        thread.start();

    }
}
