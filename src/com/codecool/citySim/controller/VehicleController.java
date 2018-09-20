package com.codecool.citySim.controller;

import com.codecool.citySim.model.Vehicle;
import com.codecool.citySim.model.roads.Road;
import com.codecool.citySim.view.VehicleView;

import java.util.LinkedList;

class VehicleController {

    private double moveOfAxis;
    private boolean axis;
    private boolean isSecondRoad;
    private Vehicle basicCar;
    private Road basicRoad;

    //Check if car is in the given roads List of vehicles, if not add it.
    VehicleController(Vehicle car, Road road) {
        if (road.getStartX() == 640) car.getImage().setRotate(180);
        if (road.getStartX() == 8) car.getImage().setRotate(270);
        if (road.getStartX() == -8) car.getImage().setRotate(90);
        car.getImage().setX(car.getX());
        car.getImage().setY(car.getY());

        if (!road.getVehicles().contains(car)) { road.getVehicles().add(car); }
        basicCar = car;
        basicRoad = road;
    }

    void moveTheCar() {
        //Assign vehicles on this basicRoad to a variable
        LinkedList<Vehicle> vehiclesList = basicRoad.getVehicles();
        if (isSecondRoad) {
            vehiclesList = basicRoad.getVehicles();
        }

        //Assign position X and Y of a basicCar to variables
        double carX, carY;
        carX = basicCar.getX();
        carY = basicCar.getY();

        //assign basicRoad X and Y start and end points to variables
        double roadStartX, roadEndX, roadStartY, roadEndY;
        roadStartX = basicRoad.getStartX();
        roadStartY = basicRoad.getStartY();
        roadEndX = basicRoad.getEndX();
        roadEndY = basicRoad.getEndY();

        //Check if basicRoad is going in X or Y axis, set boolean to true if X, false if Y
        axis = !(roadStartX == roadEndX);

        //Check if there is a vehicle in front of our basicCar
        if (vehiclesList.indexOf(basicCar) -1 >= 0) {
            //add name to the vehicle in front of our basicCar
            Vehicle nextVehicle = vehiclesList.get(vehiclesList.indexOf(basicCar) - 1);
            //Assign position X and Y of a vehicle in front of our basicCar to variables
            double nextVehicleX = nextVehicle.getX();
            double nextVehicleY = nextVehicle.getY();

            // check if vehicles are moving on X or Y axis
            if (axis) {
                //set basicCar speed so that it keeps distance = 2*speed from vehicle in front
                basicCar.setSpeed(getSpeedByAxisDifference(carX, nextVehicleX));
            } else {
                basicCar.setSpeed(getSpeedByAxisDifference(carY, nextVehicleY));
            }
        } else {
            //check if basicCar is moving in X or Y axis
            if (axis) {
                //set basicCar speed so that it keeps distance = 2*speed from the crossroad
                if (isSecondRoad) {
                    basicCar.setSpeed(getSpeedByAxisDifference(carX + 500, roadEndX));
                }
                basicCar.setSpeed(getSpeedByAxisDifference(carX, roadEndX));
            } else {
                basicCar.setSpeed(getSpeedByAxisDifference(carY, roadEndY));
                if (isSecondRoad) {
                    basicCar.setSpeed(getSpeedByAxisDifference(carY + 500, roadEndY));
                }
            }
        }

        //check if basicCar is driving within maximum speed
        double currentSpeed;
        if (basicCar.getSpeed() > basicCar.getMaxSpeed()) {
            currentSpeed = basicCar.getMaxSpeed();
        } else {
            currentSpeed = basicCar.getSpeed();
        }

        //check if basicRoad is moving in X or Y axis
        if (axis) {
            //check if basicCar is moving left or right on its axis
            if (roadStartY > 0) {
                moveOfAxis = convertSpeedToPixels(currentSpeed);
            } else {
                moveOfAxis = -convertSpeedToPixels(currentSpeed);
            }
        } else {
            if (roadStartX < 0) {
                moveOfAxis = convertSpeedToPixels(currentSpeed);
            } else {
                moveOfAxis = -convertSpeedToPixels(currentSpeed);
            }
        }
        //ser Image for the moving car
        VehicleView carImage = new VehicleView(basicCar);
        //set by how far basicCar is supposed to move in TranslateTransition
        moveOfAxis = carImage.setCarMovement(axis, moveOfAxis, isSecondRoad);
        //run the animation
        carImage.startCarImage();
    }

    //speed of a car is converted from km/h to m/s and then divided by 5, because 1m in app is 5px
    private double convertSpeedToPixels(double speed) {
        double KMHtoMS = 0.27778;
        int oneMeterInPX = 5;
        return Double.parseDouble(String.valueOf((speed / KMHtoMS) / oneMeterInPX));
    }

    //calculate distance between objects by their positions in one axis
    private double getSpeedByAxisDifference(double pos1, double pos2) {
        return Math.abs(pos1 - pos2) / 2;
    }

    //TranslateTransition doesn't save the cars position it has to be saved manually from the game loop
    void setCarsXY(Vehicle car) {
        if (axis) {
            car.setX(car.getX() + moveOfAxis);
        } else {
            car.setY(car.getY() + moveOfAxis);
        }
    }

    Road getBasicRoad() {
        return basicRoad;
    }

    void setBasicRoad(Road basicRoad) {
        this.basicRoad.getVehicles().remove(basicCar);
        this.basicRoad = basicRoad;
        basicRoad.getVehicles().add(basicCar);
        this.isSecondRoad = true;
    }
}
