package graph;

import interfaces.IAbstractGraph;

import java.io.Serializable;
import java.util.*;

public class Graph<K, V, H> implements Serializable, IAbstractGraph<K, V, H> {

    private Map<K, Node> nodes = new HashMap<>();

    private class Node implements Serializable {

        private V data;
        private LinkedList<Edge> listOfEdge = new LinkedList<>();

        private Node(V data) {
            this.data = data;
        }
    }

    private class Edge implements Serializable {

        private K sourceKey;
        private K destinationKey;
        private H data;

        private Edge(H data) {
            this.data = data;
        }
    }

    @Override
    public void removeNode(K nameOfVortex) {
        for (Edge edge : new LinkedList<>(nodes.get(nameOfVortex).listOfEdge)) {
            removePath(edge.sourceKey, edge.destinationKey);
        }
        nodes.remove(nameOfVortex);
    }

    @Override
    public void removePath(K keyOfFirstVortex, K keyOfSecondVortex) {
        Node firstNode = nodes.get(keyOfFirstVortex);
        Node secondNode = nodes.get(keyOfSecondVortex);

        if (firstNode != null && secondNode != null) {
            Edge edgeToDelete = null;
            for (Edge i : firstNode.listOfEdge) {
                if (i.sourceKey.equals(keyOfFirstVortex) && i.destinationKey.equals(keyOfSecondVortex) ||
                        i.sourceKey.equals(keyOfSecondVortex) && i.destinationKey.equals(keyOfFirstVortex)) {
                    edgeToDelete = i;
                    break;
                }
            }
            firstNode.listOfEdge.remove(edgeToDelete);
            secondNode.listOfEdge.remove(edgeToDelete);
        }
    }

    @Override
    public V getVortex(K keyNode) {
        return nodes.get(keyNode).data;
    }

    @Override
    public void addNode(K keyNode, V vortexData) {
        if(nodes.get(keyNode) == null){
            nodes.put(keyNode, new Node(vortexData));
        }
    }

    @Override
    public void addPath(K sourceKey, K destinationKey, H data) throws Exception {
        Node source = nodes.get(sourceKey);
        Node destination = nodes.get(destinationKey);
        Edge edge = new Edge(data);

        if (source != null && destination != null) {
            edge.destinationKey = destinationKey;
            edge.sourceKey = sourceKey;
            source.listOfEdge.add(edge);
            destination.listOfEdge.add(edge);
        } else {
            throw new Exception("Vortex does not exist!");
        }
    }

    @Override
    public List<V> getAllVortex() {
        List<V> list = new LinkedList<>();
        for (Map.Entry<K, Node> entry : nodes.entrySet()) {
            list.add(entry.getValue().data);
        }
        return list;
    }

    @Override
    public List<H> getIncidentEdges(K keyNode) {
        Node temp = nodes.get(keyNode);
        if (temp != null) {
            List<H> list = new LinkedList<>();
            for (Edge i : temp.listOfEdge) {
                list.add(i.data);
            }
            return list;
        }
        return new LinkedList<>();
    }

    @Override
    public H getEdge(K node1, K node2) {
        for (Edge edge : nodes.get(node1).listOfEdge) {
            if (edge.destinationKey.equals(node1) && edge.sourceKey.equals(node2) ||
                    edge.sourceKey.equals(node1) && edge.destinationKey.equals(node2)) {
                return edge.data;
            }
        }
        return null;
    }

}