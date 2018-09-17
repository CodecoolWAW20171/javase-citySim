package com.codecool.citySim.model.roads;

import com.codecool.citySim.model.Vehicle;
import javafx.scene.shape.Line;

import java.util.LinkedList;

public class Road {
    private Double[] startPos;
    private Double[] endPos;
    private LinkedList<Vehicle> vehicles;

    public Road(double startX, double startY, double endX, double endY) {
        startPos = new Double[]{startX, startY};
        endPos = new Double[]{endX, endY};
    }

    public double getStartX() {
        return startPos[0];
    }

    public double getStartY() {
        return startPos[1];
    }

    public double getEndX() {
        return endPos[0];
    }

    public double getEndY() {
        return endPos[1];
    }
}
