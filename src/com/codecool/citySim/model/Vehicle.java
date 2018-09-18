package com.codecool.citySim.model;

public abstract class Vehicle {
    protected double x;
    protected double y;
    private String image;
    protected double speed;
    private double maxSpeed;
    protected int minDist;
    protected double acceleration;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
}
