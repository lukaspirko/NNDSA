package classes;

import interfaces.Place;

import java.io.Serializable;

public class Path implements Serializable {
    private Place startPlace;
    private Place finishPlace;
    private boolean usable = true;
    private double length;

    public Path(Place startPlace, Place finishPlace, double length) {
        this.startPlace = startPlace;
        this.finishPlace = finishPlace;
        this.length = length;
    }

    public Place getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(Place startPlace) {
        this.startPlace = startPlace;
    }

    public Place getFinishPlace() {
        return finishPlace;
    }

    public void setFinishPlace(Place finishPlace) {
        this.finishPlace = finishPlace;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
