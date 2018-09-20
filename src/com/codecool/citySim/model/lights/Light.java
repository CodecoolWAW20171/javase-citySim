package com.codecool.citySim.model.lights;

public class Light {

    private boolean isGreen;

    public void changeLight() {
        boolean result = !isGreen();
        setGreen(result);
    }

    public boolean isGreen() {
        return isGreen;
    }

    void setGreen(boolean green) {
        isGreen = green;
    }
}
