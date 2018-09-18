package com.codecool.citySim.model.cars;

import com.codecool.citySim.model.Vehicle;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Car extends Vehicle {

    public Car(double x, double y) {
        String[] images = {"car1", "car2", "car3", "fast", "taxi"};
        this.setImage(new ImageView("cars/" + images[new Random().nextInt(images.length)] + ".png"));
        this.setX(x);
        this.setY(y);
        this.setMaxSpeed((new Random().nextInt(20)) + 40);
        this.setMinDist(10);
    }

}
