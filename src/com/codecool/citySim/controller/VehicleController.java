package com.codecool.citySim.controller;

import com.codecool.citySim.model.Vehicle;
import com.codecool.citySim.model.roads.Road;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.LinkedList;

class VehicleController {

    private double moveOfAxis;
    private boolean axis;
    private TranslateTransition moveInAStraightLine;

    //Check if car is in the given roads List of vehicles, if not add it.
    VehicleController(Vehicle car, Road road) {
        LinkedList<Vehicle> vehiclesList = road.getVehicles();
        if (!vehiclesList.contains(car)) {
            vehiclesList.add(car);
            road.setVehicles(vehiclesList);
        }

    }

    void moveTheCar(Vehicle car, Road road) {
        //Assign vehicles on this road to a variable
        LinkedList<Vehicle> vehiclesList = road.getVehicles();
        //Assign position X and Y of a car to variables
        double carX, carY;
        carX = car.getX();
        carY = car.getY();
        //assign road X and Y start and end points to variables
        double roadStartX, roadEndX, roadStartY, roadEndY;
        roadStartX = road.getStartX();
        roadStartY = road.getStartY();
        roadEndX = road.getEndX();
        roadEndY = road.getEndY();
        //Check if road is going in X or Y axis
        if (roadStartX == roadEndX) {
            axis = false;
        } else {
            axis = true;
        }
        //Check if there is a vehicle in front of our car
        if (vehiclesList.indexOf(car) -1 >= 0) {
            //add name to the vehicle in front of our car
            Vehicle nextVehicle = vehiclesList.get(vehiclesList.indexOf(car) - 1);
            //Assign position X and Y of a vehicle in front of our car to variables
            double nextVehicleX = nextVehicle.getX();
            double nextVehicleY = nextVehicle.getY();
            // check if vehicles are moving on X or Y axis
            if (axis) {
                //set car speed so that it keeps distance = 2*speed from vehicle in front
                car.setSpeed(getSpeedByAxisDifference(carX, nextVehicleX));
            } else {
                car.setSpeed(getSpeedByAxisDifference(carY, nextVehicleY));
            }
        } else {
            //check if car is moving in X or Y axis
            if (axis) {
                //set car speed so that it keeps distance = 2*speed from the crossroad
                car.setSpeed(getSpeedByAxisDifference(carX, roadEndX));
            } else {
                car.setSpeed(getSpeedByAxisDifference(carY, roadEndY));
            }
        }
        //check if car is driving within maximum speed
        double currentSpeed;
        if (car.getSpeed() > car.getMaxSpeed()) {
            currentSpeed = car.getMaxSpeed();
        } else {
            currentSpeed = car.getSpeed();
        }
        //create animation for car movement, assign cars Image View to it
        moveInAStraightLine = new TranslateTransition(Duration.millis(1000), car.getImage());
        //check if road is moving in X or Y axis
        if (axis) {
            //check if car is moving left or right on its axis
            if (roadStartX < 0) {
                moveOfAxis = convertSpeedToPixels(currentSpeed);
            } else {
                System.out.println(car.getX() + ":::" + car.getY());
                System.out.println(currentSpeed + ":::" + convertSpeedToPixels(currentSpeed));
                moveOfAxis = -convertSpeedToPixels(currentSpeed);
            }
        } else {
            if (roadStartY < 0) {
                moveOfAxis = - convertSpeedToPixels(currentSpeed);
            } else {
                moveOfAxis = convertSpeedToPixels(currentSpeed);
            }
        }
        //set by how far car is supposed to move in TranslateTransition
        setCarMovement(axis, moveOfAxis);
        //remove acceleration and braking in one step
        moveInAStraightLine.setInterpolator(Interpolator.LINEAR);
        //move the car
        moveInAStraightLine.play();
    }

    //speed of a car is converted from km/h to m/s and then divided by 5, because 1m in app is 5px
    private double convertSpeedToPixels(double speed) {
        double KMHtoMS = 0.27778;
        int oneMeterInPX = 5;
        return Double.parseDouble(String.valueOf((speed / KMHtoMS) / oneMeterInPX));
    }

    //calculate distance between objects by their positions in one axis
    private double getSpeedByAxisDifference(double pos1, double pos2) {
        return Math.abs(Math.abs(pos1) - Math.abs(pos2)) / 2;
    }

    //set on which axis car is moving, also how far and which way
    private void setCarMovement(boolean axis, double value) {
        if (axis) {
            moveInAStraightLine.setByX(value);
        } else {
            moveInAStraightLine.setByY(value);
        }
    }

    //TranslateTransition doesn't save the cars position it has to be saved manually from the game loop
    void setCarsXY(Vehicle car) {
        if (axis) {
            car.setX(car.getX() + moveOfAxis);
        } else {
            car.setY(car.getY() + moveOfAxis);
        }
    }

}
