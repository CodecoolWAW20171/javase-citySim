package com.codecool.citySim.model.cars;

import com.codecool.citySim.model.Vehicle;

import javafx.scene.image.ImageView;

public class Car extends Vehicle {

    public Car(double speed, double maxSpeed, int minDist, double acceleration, ImageView image) {
        this.speed = speed;
        this.maxSpeed = maxSpeed;
        this.minDist = minDist;
        this.acceleration = acceleration;
        this.image = image;
    }

}
