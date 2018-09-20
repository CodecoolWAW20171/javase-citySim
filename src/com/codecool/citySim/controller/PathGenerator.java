package com.codecool.citySim.controller;

import com.codecool.citySim.model.Simulation;
import com.codecool.citySim.model.cars.Car;
import com.codecool.citySim.model.roads.Road;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;

import java.util.Random;

class PathGenerator {

    private Simulation roadsLists = new Simulation();
    private Road[] secondRoads = roadsLists.getSecondRoads();
    Road chosenRoad;
    private Random rand = new Random();
    Path newTurn = new Path();
    private double roadEndX;
    private double roadEndY;
    private double chosenStartX;
    private double chosenEndX;
    private double chosenStartY;
    private double chosenEndY;

    PathGenerator(Car car, Road road) {
        this.chosenRoad = chooseRoad();
        double roadStartX = road.getStartX();
        roadEndX = road.getEndX();
        double roadStartY = road.getStartY();
        roadEndY = road.getEndY();
        chosenStartX = chosenRoad.getStartX();
        chosenEndX = chosenRoad.getEndX();
        chosenStartY = chosenRoad.getStartY();
        chosenEndY = chosenRoad.getEndY();
        while ((Math.abs(roadStartX - chosenEndX) == 0 && Math.abs(roadStartY - chosenEndY) == 16) ||
                (Math.abs(roadStartY - chosenEndY) == 0 && Math.abs(roadStartX - chosenEndX) == 16)
        ) {
            chosenRoad = chooseRoad();
            chosenStartX = chosenRoad.getStartX();
            chosenEndX = chosenRoad.getEndX();
            chosenStartY = chosenRoad.getStartY();
            chosenEndY = chosenRoad.getEndY();
        }
        MoveTo getToStartOfTurn = new MoveTo(car.getX(), car.getY());
        LineTo getToTurn = new LineTo(roadEndX, roadEndY);
        newTurn.getElements().addAll(getToStartOfTurn, getToTurn);
        checkNextTurn();
    }

    private void checkNextTurn() {
        int diffX = (int) Math.abs(Math.abs(roadEndX) - Math.abs(chosenStartX));
        int diffY = (int) Math.abs(Math.abs(roadEndY) - Math.abs(chosenStartY));
        int controlX, controlY;
        int CONTROL_TURN_RIGHT = 7;
        int RIGHT_TURN_DIFF = 8;
        int LEFT_TURN_DIFF = 24;
        int VEHICLE_STRAIGHTENING_MOVEMENT = 8;
        if (diffX == RIGHT_TURN_DIFF && diffY == RIGHT_TURN_DIFF) {
            controlX = chosenStartX > 0 ? CONTROL_TURN_RIGHT : -CONTROL_TURN_RIGHT;
            controlY = chosenStartY > 0 ? CONTROL_TURN_RIGHT : -CONTROL_TURN_RIGHT;
            QuadCurveTo nextMove = new QuadCurveTo(controlX, controlY, chosenStartX, chosenStartY);
            newTurn.getElements().add(nextMove);
        } else if (diffX == LEFT_TURN_DIFF && diffY == LEFT_TURN_DIFF) {
            int CONTROL_TURN_LEFT = 1;
            controlY = chosenStartX > 0 ? CONTROL_TURN_LEFT : -CONTROL_TURN_LEFT;
            controlX = chosenStartY > 0 ? -CONTROL_TURN_LEFT : CONTROL_TURN_LEFT;
            QuadCurveTo nextMove = new QuadCurveTo(controlX, controlY, chosenStartX, chosenStartY);
            newTurn.getElements().add(nextMove);
        } else {
            LineTo nextMove = new LineTo(chosenStartX, chosenStartY);
            newTurn.getElements().add(nextMove);
        }
        int changeToVehicleMovement;
        if (chosenStartX != chosenEndX) {
            if (chosenStartX > chosenEndX) {
                changeToVehicleMovement = -VEHICLE_STRAIGHTENING_MOVEMENT;
            } else{
                changeToVehicleMovement = VEHICLE_STRAIGHTENING_MOVEMENT;
            }
            newTurn.getElements().add(new LineTo(chosenStartX + changeToVehicleMovement, chosenStartY));
        } else {
            if (chosenStartY > chosenEndY) {
                changeToVehicleMovement = -VEHICLE_STRAIGHTENING_MOVEMENT;
            } else {
                changeToVehicleMovement = VEHICLE_STRAIGHTENING_MOVEMENT;
            }
            newTurn.getElements().add(new LineTo(chosenStartX, chosenStartY + changeToVehicleMovement));
        }
    }

    private Road chooseRoad() {
        return secondRoads[rand.nextInt(4)];
    }

}
