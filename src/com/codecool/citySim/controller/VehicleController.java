package com.codecool.citySim.controller;

import com.codecool.citySim.model.Vehicle;
import com.codecool.citySim.model.roads.Road;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.LinkedList;

class VehicleController {

    private double currentSpeed;

    VehicleController(Vehicle car, Road road) {
        LinkedList<Vehicle> vehiclesList = road.getVehicles();
        if (!vehiclesList.contains(car)) {
            vehiclesList.add(car);
            road.setVehicles(vehiclesList);
        }

    }

    void moveTheCar(Vehicle car, Road road) {
        LinkedList<Vehicle> vehiclesList = road.getVehicles();
        if (vehiclesList.indexOf(car) -1 >= 0) {
            Vehicle nextVehicle = vehiclesList.get(vehiclesList.indexOf(car) - 1);
            if (car.getX() == nextVehicle.getX()) {
                car.setSpeed(Math.abs(Math.abs(car.getX()) - Math.abs(nextVehicle.getX())));
                if (car.getSpeed() > car.getMaxSpeed()) {
                    currentSpeed = car.getMaxSpeed();
                } else {
                    currentSpeed = car.getSpeed();
                }
            } else {
                car.setSpeed(Math.abs(Math.abs(car.getY() - Math.abs(nextVehicle.getY()))));
                if (car.getSpeed() > car.getMaxSpeed()) {
                    currentSpeed = car.getMaxSpeed();
                } else {
                    currentSpeed = car.getSpeed();
                }
            }
        } else {
            if (car.getX() == road.getStartX() && car.getX() == road.getEndX()) {
                if (Math.abs(Math.abs(car.getY()) - Math.abs(road.getEndY())) > car.getMaxSpeed()) {
                    car.setSpeed(Math.abs(Math.abs(car.getY()) - Math.abs(road.getEndY())));
                    if (car.getSpeed() > car.getMaxSpeed()) {
                        currentSpeed = car.getMaxSpeed();
                    } else {
                        currentSpeed = car.getSpeed();
                    }
                } else {
                    car.setSpeed(0);
                    currentSpeed = 0;
                }
            } else {
                if (Math.abs(Math.abs(car.getX()) - Math.abs(road.getEndX())) > car.getMaxSpeed()) {
                    car.setSpeed(Math.abs(Math.abs(car.getX()) - Math.abs(road.getEndX())));
                    if (car.getSpeed() > car.getMaxSpeed()) {
                        currentSpeed = car.getMaxSpeed();
                    } else {
                        currentSpeed = car.getSpeed();
                    }
                } else {
                    car.setSpeed(0);
                    currentSpeed = 0;
                }
            }
        }
        TranslateTransition moveInAStraightLine = new TranslateTransition(Duration.millis(1000), car);
        double moveOfAxis;
        String axis;
        if (Math.abs(road.getEndX()) - Math.abs(road.getStartX()) == 0) {
            if (road.getStartX() < 0) {
                moveInAStraightLine.setByY(convertSpeedToPixels((int) currentSpeed));
                moveOfAxis = convertSpeedToPixels((int) currentSpeed);
            } else {
                moveInAStraightLine.setByY(-convertSpeedToPixels((int) currentSpeed));
                moveOfAxis = -convertSpeedToPixels((int) currentSpeed);
            }
            axis = "Y";
        } else {
            if (road.getStartY() < 0) {
                moveInAStraightLine.setByX(-convertSpeedToPixels((int) currentSpeed));
                moveOfAxis = - convertSpeedToPixels((int) currentSpeed);
            } else {
                moveInAStraightLine.setByX(convertSpeedToPixels((int) currentSpeed));
                moveOfAxis = convertSpeedToPixels((int) currentSpeed);
            }
            axis = "X";
        }
        moveInAStraightLine.setInterpolator(Interpolator.LINEAR);
        moveInAStraightLine.play();
        moveInAStraightLine.setOnFinished(event -> setCarsXY(axis, moveOfAxis, car));
    }

    private double convertSpeedToPixels(int speed) {
        return Double.parseDouble(String.valueOf((speed / 0.27778) / 5));
    }

    private void setCarsXY(String axis, double value, Vehicle car) {
        if (axis.equals("X")) {
            car.setX(car.getX() + value);
        } else {
            car.setY(car.getY() + value);
        }
    }

}
