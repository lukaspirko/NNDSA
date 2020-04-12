package interfaces;

import java.util.List;

public interface IAbstractRangeTree<D> {

    void build(List<D> listOfInformation);

    D simplyFind(D coordinate);

    List<D> intervalFind(D coordinateA, D coordinateB);
}
