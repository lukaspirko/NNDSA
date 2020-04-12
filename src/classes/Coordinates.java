package classes;

import interfaces.ICoordinates;

public class Coordinates extends ICoordinates {

    @Override
    public int compareTo(ICoordinates o) {
        return this.getX() - o.getX() + this.getY() - o.getY();
    }
}
