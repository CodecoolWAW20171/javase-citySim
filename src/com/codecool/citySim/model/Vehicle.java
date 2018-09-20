package com.codecool.citySim.model;


import javafx.scene.image.ImageView;

public abstract class Vehicle {
    private double x;
    private double y;
    private ImageView image;
    private double speed;
    private double maxSpeed;

    public ImageView getImage() {
        return image;
    }

    protected void setImage(ImageView image) {
        this.image = image;
    }

    protected void setMaxSpeed(double maxSpeed) {
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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
