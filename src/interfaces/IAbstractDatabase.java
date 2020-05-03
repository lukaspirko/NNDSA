package interfaces;

import java.util.List;

public interface IAbstractDatabase {

    void build(List<IPlace> listOfPlaces, int numberOfBlocks, int numberOfRecords);

    IPlace delete(String key);

    IPlace interpolationSearch(String key);

    IPlace binarySearch(String key);
}
