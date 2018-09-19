package com.codecool.citySim.model;

import com.codecool.citySim.model.roads.Road;

public class Simulation {
    private Road[] firstRoads = {
            new Road(-640, 8, -16, 8),
            new Road(640, -8, 16, -8),
            new Road(-8, -360, -8, -16),
            new Road(8, 360, 8, 16)
    };
    private Road[] secondRoads = {
            new Road(16, 8, 640, 8),
            new Road(-16, -8, -640, -8),
            new Road(-8, 16, -8, 360),
            new Road(8, -16, 8, -360)
    };

    public Road[] getFirstRoads() {
        return firstRoads;
    }

    public Road[] getSecondRoads() {
        return secondRoads;
    }
}
