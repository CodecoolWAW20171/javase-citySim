package com.codecool.citySim.model.lights;

public class Light {

    private String id;
    private boolean isGreen;
    private boolean isVertical;

    public boolean changeLight() {
        boolean result = !isGreen();
        setGreen(result);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isGreen() {
        return isGreen;
    }

    public void setGreen(boolean green) {
        isGreen = green;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }
}
