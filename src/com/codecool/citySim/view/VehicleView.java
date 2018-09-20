package com.codecool.citySim.view;

import com.codecool.citySim.model.Vehicle;
import com.codecool.citySim.model.roads.Road;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class VehicleView {

    private TranslateTransition moveInAStraightLine;

    public VehicleView(Vehicle car) {
        //create animation for basicCar movement, assign cars Image View to it
        moveInAStraightLine = new TranslateTransition(Duration.millis(1000), car.getImage());
    }

    public static void setCarImageRotation(Vehicle car, Road road) {
        if (road.getStartX() == 640) car.getImage().setRotate(180);
        if (road.getStartX() == 8) car.getImage().setRotate(270);
        if (road.getStartX() == -8) car.getImage().setRotate(90);
        car.getImage().setX(car.getX());
        car.getImage().setY(car.getY());
    }

    //set on which axis car is moving, also how far
    public double setCarMovement(boolean axis, double value, boolean isSecondRoad) {
        if (!isSecondRoad) {
            int minDist = 15;
            int stop = 0;
            if (Math.abs(value) < minDist) { value = stop; }
        }
        if (axis) {
            moveInAStraightLine.setByX(value);
        } else {
            moveInAStraightLine.setByY(value);
        }
        return value;
    }

    public void startCarImage() {
        //remove acceleration and braking in one step
        moveInAStraightLine.setInterpolator(Interpolator.LINEAR);
        //move the basicCar
        moveInAStraightLine.play();
    }

}
