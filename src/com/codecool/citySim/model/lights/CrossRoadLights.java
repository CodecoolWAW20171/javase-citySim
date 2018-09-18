package com.codecool.citySim.model.lights;

import java.util.LinkedList;
import java.util.List;

public class CrossRoadLights {

    private Light verticalLightRight;
    private Light verticalLightLeft;
    private Light horizontalLightUp;
    private Light horizontalLightDown;
    private List<Light> lights;

    public CrossRoadLights() {
        lights = new LinkedList<>();

        verticalLightRight = new Light();
        verticalLightRight.setVertical(true);
        verticalLightRight.setGreen(true);
        verticalLightRight.setId("#verticalLightRight");

        verticalLightLeft = new Light();
        verticalLightLeft.setVertical(true);
        verticalLightLeft.setGreen(true);
        verticalLightLeft.setId("#verticalLightLeft");


        horizontalLightUp = new Light();
        horizontalLightUp.setVertical(false);
        horizontalLightUp.setGreen(false);
        horizontalLightUp.setId("#horizontalLightUp");

        horizontalLightDown = new Light();
        horizontalLightDown.setVertical(false);
        horizontalLightDown.setGreen(false);
        horizontalLightDown.setId("#horizontalLightDown");

        lights.add(verticalLightLeft);
        lights.add(verticalLightRight);
        lights.add(horizontalLightUp);
        lights.add(horizontalLightDown);
    }

    public List<Light> getLights() {
        return lights;
    }
}
