package com.codecool.citySim.model.cars;

import com.codecool.citySim.model.Vehicle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Car extends Vehicle {

    public Car() {
        this.setImage(new Image("cars/emergencyServices/police.png"));
        this.setY(700);
        this.setX(652);
    }



}
