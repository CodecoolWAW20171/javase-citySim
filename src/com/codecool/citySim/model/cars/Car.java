package com.codecool.citySim.model.cars;

import com.codecool.citySim.model.Vehicle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Car extends Vehicle {

    public Car(double x, double y) {
        Random rand = new Random();
        maxSpeed = rand.nextInt(20) + 40;
        minDist = 10;
        this.setX(x);
        this.setY(y);
        this.setImage(new Image("cars/car1.png"));
    }



}
