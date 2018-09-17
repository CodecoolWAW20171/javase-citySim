package com.codecool.citySim.model.roads;

import com.codecool.citySim.model.Vehicle;
import javafx.scene.shape.Line;

import java.util.LinkedList;

public class Road extends Line {
    private LinkedList<Vehicle> vehicles = new LinkedList<>();

    public LinkedList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(LinkedList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
