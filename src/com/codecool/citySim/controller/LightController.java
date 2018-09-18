package com.codecool.citySim.controller;

import com.codecool.citySim.model.lights.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LightController implements Runnable {


    private Light verticalLightRight;
    private Light verticalLightLeft;
    private Light horizontalLightUp;
    private Light horizontalLightDown;
    private Pane pane;
    private long timeOfLight;

    public LightController(Pane pane) {
        this.pane = pane;
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

        setTimeOfLight(5000);
    }

    @Override
    public void run() {

        while (true) {

            Rectangle verticalLightRightRect = (Rectangle) pane.lookup(verticalLightRight.getId());
            Rectangle verticalLightLeftRect = (Rectangle) pane.lookup(verticalLightLeft.getId());
            Rectangle horizontalLightUpRect = (Rectangle) pane.lookup(horizontalLightUp.getId());
            Rectangle horizontalLightDownRect = (Rectangle) pane.lookup(horizontalLightDown.getId());

            if ((verticalLightRight.isGreen() && verticalLightLeft.isGreen()) || (!horizontalLightUp.isGreen() && !horizontalLightDown.isGreen())) {

                verticalLightRight.setGreen(!verticalLightRight.isGreen());
                verticalLightRightRect.setFill(Color.GREEN);
                verticalLightLeft.setGreen(!verticalLightLeft.isGreen());
                verticalLightLeftRect.setFill(Color.GREEN);

                horizontalLightUp.setGreen(!horizontalLightUp.isGreen());
                horizontalLightUpRect.setFill(Color.RED);

                horizontalLightDown.setGreen(!horizontalLightDown.isGreen());
                horizontalLightDownRect.setFill(Color.RED);

            } else {
                verticalLightRight.setGreen(!verticalLightRight.isGreen());
                verticalLightRightRect.setFill(Color.RED);
                verticalLightLeft.setGreen(!verticalLightLeft.isGreen());
                verticalLightLeftRect.setFill(Color.RED);

                horizontalLightUp.setGreen(!horizontalLightUp.isGreen());
                horizontalLightUpRect.setFill(Color.GREEN);

                horizontalLightDown.setGreen(!horizontalLightDown.isGreen());
                horizontalLightDownRect.setFill(Color.GREEN);
            }

            try {
                Thread.sleep(getTimeOfLight());
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void stop() {
        System.exit(0);
    }

    public long getTimeOfLight() {
        return timeOfLight;
    }

    public void setTimeOfLight(long timeOfLight) {
        this.timeOfLight = timeOfLight;
    }
}
