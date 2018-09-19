package com.codecool.citySim.model.lights;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CrossRoadLights {

    private Light verticalLightRight;
    private Light verticalLightLeft;
    private Light horizontalLightUp;
    private Light horizontalLightDown;
    private Map<String, Light> lights;

    public CrossRoadLights() {
        lights = new HashMap<>();

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

        lights.put("verticalLightLeft", verticalLightLeft);
        lights.put("verticalLightRight", verticalLightRight);
        lights.put("horizontalLightUp", horizontalLightUp);
        lights.put("horizontalLightDown", horizontalLightDown);
    }

    public Map<String,Light> getLights() {
        return lights;
    }
}
