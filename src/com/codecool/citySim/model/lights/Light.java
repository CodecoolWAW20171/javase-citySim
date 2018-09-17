package com.codecool.citySim.model.lights;

public class Light implements Runnable {

    private boolean isGreenHorizontal;
    private boolean isGreenVertical;
    private long timeOfLight;

    public Light() {
        setGreenVertical(!isGreenHorizontal());
        setTimeOfLight(5000);
    }

    @Override
    public void run() {
        while (true) {
            setGreenVertical(!isGreenVertical());
            setGreenHorizontal(!isGreenHorizontal());
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

    public boolean isGreenHorizontal() {
        return isGreenHorizontal;
    }

    public void setGreenHorizontal(boolean greenHorizontal) {
        isGreenHorizontal = greenHorizontal;
    }

    public boolean isGreenVertical() {
        return isGreenVertical;
    }

    public void setGreenVertical(boolean greenVertical) {
        isGreenVertical = greenVertical;
    }

    @Override
    public String toString() {
        return "Light{" +
                "isGreenHorizontal=" + isGreenHorizontal() +
                ", isGreenVertical=" + isGreenVertical() +
                ", timeOfLight=" + getTimeOfLight() +
                '}';
    }
}
