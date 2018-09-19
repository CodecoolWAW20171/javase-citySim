package com.codecool.citySim.controller;

import com.codecool.citySim.model.Simulation;
import com.codecool.citySim.model.cars.Car;
import com.codecool.citySim.model.roads.Road;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class CitySimPaneController {
    @FXML
    public Pane pane;
    public Path path2;
    public Path righToUpTurn;
    private Simulation sim = new Simulation();
    private Road road1 = sim.getFirstRoads()[0];
    private Road road2 = sim.getSecondRoads()[3];

    public void initialize() {
        Car car = new Car(road1.getEndX(), road1.getEndY());
        VehicleController movingCar = new VehicleController(car, road2);
        car.getImage().setRotate(90);
        car.getImage().setX(car.getX());
        car.getImage().setY(car.getY());
        pane.getChildren().addAll(car.getImage());

        PathTransition pathTransition = new PathTransition(Duration.seconds(3), righToUpTurn, car.getImage());
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        pathTransition.setOnFinished(event -> new Thread(() -> {
            car.getImage().setX(8);
            car.getImage().setY(-16);
            car.getImage().setTranslateX(0);
            car.getImage().setTranslateY(0);
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


        LightController lightController = new LightController(pane);

        Thread thread = new Thread(lightController);
        thread.start();

    }
}
