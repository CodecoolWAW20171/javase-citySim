package com.codecool.citySim.controller;

import com.codecool.citySim.model.lights.CrossRoadLights;
import com.codecool.citySim.model.lights.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LightController implements Runnable {

    private Pane pane;
    private CrossRoadLights crossRoadLights;
    private long timeOfLight;

    public LightController(Pane pane) {
        this.pane = pane;
        crossRoadLights = new CrossRoadLights();
        setTimeOfLight(5000);
    }

    @Override
    public void run() {

        while (true) {
            for (Light light : crossRoadLights.getLights()) {
                Rectangle verticalLightRightRect = (Rectangle) pane.lookup(light.getId());
                if (light.isGreen()) {
                    verticalLightRightRect.setFill(Color.GREEN);
                    light.changeLight();
                } else {
                    verticalLightRightRect.setFill(Color.RED);
                    light.changeLight();
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
