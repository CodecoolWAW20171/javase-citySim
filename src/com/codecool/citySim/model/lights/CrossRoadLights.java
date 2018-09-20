package com.codecool.citySim.model.lights;

import java.util.HashMap;
import java.util.Map;

public class CrossRoadLights {

    private Map<String, Light> lights;

    public CrossRoadLights() {
        lights = new HashMap<>();

        Light verticalLightRight = new Light();
        verticalLightRight.setGreen(true);

        Light verticalLightLeft = new Light();
        verticalLightLeft.setGreen(true);

        Light horizontalLightUp = new Light();
        horizontalLightUp.setGreen(false);

        Light horizontalLightDown = new Light();
        horizontalLightDown.setGreen(false);

        lights.put("verticalLightLeft", verticalLightLeft);
        lights.put("verticalLightRight", verticalLightRight);
        lights.put("horizontalLightUp", horizontalLightUp);
        lights.put("horizontalLightDown", horizontalLightDown);
    }

    public Map<String,Light> getLights() {
        return lights;
    }
}
