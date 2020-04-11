package classes;

import interfaces.IPlace;

import java.io.Serializable;

public class Path implements Serializable {
    private IPlace startIPlace;
    private IPlace finishIPlace;
    private boolean usable = true;
    private double length;

    public Path(IPlace startIPlace, IPlace finishIPlace, double length) {
        this.startIPlace = startIPlace;
        this.finishIPlace = finishIPlace;
        this.length = length;
    }

    public IPlace getStartIPlace() {
        return startIPlace;
    }

    public void setStartIPlace(IPlace startIPlace) {
        this.startIPlace = startIPlace;
    }

    public IPlace getFinishIPlace() {
        return finishIPlace;
    }

    public void setFinishIPlace(IPlace finishIPlace) {
        this.finishIPlace = finishIPlace;
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
