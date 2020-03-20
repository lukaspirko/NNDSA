package interfaces;

import java.util.List;

public interface IAbstractGraph<K, V, H> {

    void removeNode(K nameOfVortex) ;

    void removePath(K keyOfFirstVortex, K keyOfSecondVortex) ;

    V getVortex(K keyNode);

    void addNode(K keyNode, V vortexData);

    void addPath(K sourceKey, K destinationKey, H data) throws Exception ;

    List<V> getAllVortex();

    List<H> getIncidentEdges(K keyNode);

    H getEdge(K node1, K node2);
}
