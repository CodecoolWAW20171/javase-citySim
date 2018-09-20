package com.codecool.citySim.controller;

import com.codecool.citySim.model.lights.CrossRoadLights;
import com.codecool.citySim.model.lights.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Set;

public class LightController implements Runnable {

    private Pane pane;
    private CrossRoadLights crossRoadLights;
    private long timeOfLight;

    public LightController(Pane pane, CrossRoadLights crossRoadLights) {
        this.pane = pane;
        this.crossRoadLights = crossRoadLights;
        setTimeOfLight(5000);
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
                    verticalLightRightRect.setFill(Color.ORANGE);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        return;
                    }
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

    public long getTimeOfLight() {
        return timeOfLight;
    }

    public void setTimeOfLight(long timeOfLight) {
        this.timeOfLight = timeOfLight;
    }
}
