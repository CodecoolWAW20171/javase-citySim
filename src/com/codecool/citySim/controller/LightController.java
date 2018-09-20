package com.codecool.citySim.controller;

import com.codecool.citySim.model.lights.CrossRoadLights;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Set;

public class LightController implements Runnable {

    private Pane pane;
    private CrossRoadLights crossRoadLights;
    private long timeOfLight;

    LightController(Pane pane, CrossRoadLights crossRoadLights) {
        this.pane = pane;
        this.crossRoadLights = crossRoadLights;
        this.timeOfLight = 5000;
    }

    @Override
    public void run() {

        Set<String> lightSet = crossRoadLights.getLights().keySet();
        while (true) {
            for (String lightKey : lightSet) {
                crossRoadLights.getLights().get(lightKey).changeLight();
                Rectangle verticalLightRightRect = (Rectangle) pane.lookup("#"+lightKey);
                boolean isGreen = crossRoadLights.getLights().get(lightKey).isGreen();
                if (isGreen) {
                    verticalLightRightRect.setFill(Color.GREEN);
                } else {
                    verticalLightRightRect.setFill(Color.RED);
                }
            }

            try {
                Thread.sleep(getTimeOfLight());
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private long getTimeOfLight() {
        return timeOfLight;
    }
}
