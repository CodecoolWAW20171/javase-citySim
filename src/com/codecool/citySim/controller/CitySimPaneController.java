package com.codecool.citySim.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;


//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        Car car = new Car(-640, 8);
//        pane.getChildren().add(car);
//        VehicleController movingCar = new VehicleController(car, horizontalRightFirst);
//        new Thread(() -> {
//            while(true) {
//                movingCar.moveTheCar(car, horizontalRightFirst);
//                try {
//                    TimeUnit.MILLISECONDS.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

public class CitySimPaneController {
    @FXML
    public Pane pane;
}
