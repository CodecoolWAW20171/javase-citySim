package com.codecool.citySim.model;


import javafx.scene.image.ImageView;

public abstract class Vehicle {
    protected double x;
    protected double y;
    private ImageView image;
    protected double speed;
    private double maxSpeed;
    protected int minDist;
    protected double acceleration;

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getMinDist() {
        return minDist;
    }

    public void setMinDist(int minDist) {
        this.minDist = minDist;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.image.setX(x);
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.image.setY(y);
        this.y = y;
    }
}
