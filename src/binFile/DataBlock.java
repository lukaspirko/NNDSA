package binFile;

import interfaces.IPlace;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class DataBlock implements Serializable {
    private IPlace[] arrayOfPlaces;
    private String name;
    private int position;

    public DataBlock(IPlace[] arrayOfPlaces, String name) {
        this.arrayOfPlaces = arrayOfPlaces;
        this.name = name;

        this.position = 0;
    }

    public int getPosition() {
        return position;
    }

    public void remove(IPlace place) {

        for (int i = 0; i < arrayOfPlaces.length - 1; i++) {
            if (arrayOfPlaces[i] == place && arrayOfPlaces.length - 1 != i) {
                IPlace tmp = arrayOfPlaces[i];
                arrayOfPlaces[i] = arrayOfPlaces[i + 1];
                arrayOfPlaces[i + 1] = tmp;
            }
        }
        arrayOfPlaces[arrayOfPlaces.length - 1] = null;
        position--;
    }

    public String getNumberOfBlock() {
        return name;
    }

    public List<IPlace> getList() {
        return Arrays.asList(arrayOfPlaces);
    }

    public void add(IPlace data) {
        arrayOfPlaces[position] = data;
        position++;
    }

    public boolean isAbleToInsert() {
        if (position < arrayOfPlaces.length) {
            return arrayOfPlaces[position] == null;
        }
        return false;
    }

    public IPlace getMin() {
        return arrayOfPlaces[0];
    }

    public IPlace getMax() {
        return arrayOfPlaces[arrayOfPlaces.length - 1];
    }

    public int getCountRecords() {
        return position;
    }
}
