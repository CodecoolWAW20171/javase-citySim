package com.codecool.citySim.model.cars;

import com.codecool.citySim.model.Vehicle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Car extends Vehicle {

    public Car(double x, double y) {
        this.setX(x);
        this.setY(y);
        this.setImage(new Image("cars/car1.png"));
    }



}
