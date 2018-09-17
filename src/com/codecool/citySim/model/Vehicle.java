package com.codecool.citySim.model;

import javafx.scene.image.ImageView;

public abstract class Vehicle extends ImageView {
    protected double speed;
    protected int maxSpeed;
    protected int minDist;
    protected double acceleration;
    protected ImageView image;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMinDist() {
        return minDist;
    }

    public void setMinDist(int minDist) {
        this.minDist = minDist;
    }
}
