package algorithms;

import classes.Path;
import interfaces.IAbstractGraph;
import interfaces.Place;

import java.util.*;

public class Dijkstra002 {

//
//    function Dijkstra(E, V, s):
//                 for each vertex v in V:           // Inicializace
//                     d[v] := infinity              // Zatím neznámá vzdálenost z počátku s do vrcholu v
//                     p[v] := undefined             // Předchozí vrchol na nejkratší cestě z počátku s k cíli
//                  d[s] := 0                         // Vzdálenost z s do s
//                  N := V                            // Množina všech dosud nenavštívených vrcholů. Na začátku všech vrcholů.
//                  while N is not empty:             // Samotný algoritmus
//                      u := extract_min(N)           // Vezměme "nejlepší" vrchol
//                      for each neighbor v of u:
//                          alt = d[u] + l(u, v)      // v 1. smyčce cyklu u je s d[u] = 0
//                              if alt < d[v]
//                                  d[v] := alt
//                                  p[v] := u


    public static Queue<DijkstraNode> countDistance(IAbstractGraph<String, Place, Path> graph, Queue<DijkstraNode> queue) {
        Map<Place, DijkstraNode> close = new HashMap<>();
        Queue<DijkstraNode> openList = new PriorityQueue<>();
        while (!queue.isEmpty()) {
            DijkstraNode place = queue.poll();

            if (!close.containsKey(place.place)) {
                close.put(place.place, place);
            }
            List<Path> edges = graph.getIncidentEdges(place.place.getName());

            if (edges == null) {
                System.out.println("NOT foumd: " + place.place.getName());
                break;
            }
            for (Path h : edges) {

                DijkstraNode city;
                Place m;
                Path path = h;

                if (path.isUsable()) {
                    Place finishPalce = h.getFinishPlace();
                    if (finishPalce.getName().equals(place.place.getName())) {
                        m = h.getStartPlace();
                    } else {
                        m = h.getFinishPlace();
                    }
                    if (close.containsKey(m)) {
                        city = close.get(m);
                    } else {
                        city = new DijkstraNode(m);
                        close.put(m, city);

                    }
                    double costOfPartPath = path.getLength();
                    double costOfComplexPath = place.minPath + costOfPartPath;
                    if (costOfComplexPath < city.minPath) {
                        queue.remove(city);

                        city.minPath = costOfComplexPath;
                        city.predecessor = place;
                        close.remove(city);
                        close.put(city.place, city);
                        queue.add(city);
                    }
                }
            }
        }
        for (Map.Entry<Place, DijkstraNode> entrySet : close.entrySet()) {
            DijkstraNode value = entrySet.getValue();
            openList.add(value);
        }
        return openList;
    }

    public static List<Place> dijkstraAlgorithm(IAbstractGraph<String, Place, Path> graph, Place start, Place finish) {
        DijkstraNode startNode = new DijkstraNode(start);
        startNode.minPath = 0;
        Queue<DijkstraNode> queue = new PriorityQueue<>();
        queue.add(startNode);

        Queue<DijkstraNode> path = countDistance(graph, queue);

        List<Place> findPath = new LinkedList<>();

        if (path != null) {
            LinkedList<DijkstraNode> list = new LinkedList<>();
            Queue<DijkstraNode> pathTemp = new PriorityQueue<>();

            while (!path.isEmpty()) {
                DijkstraNode poll = path.poll();
                list.add(poll);
                pathTemp.add(poll);
            }

            findPath = writePathFrom(pathTemp, start, finish);
            successorMatrix(list);
        }

        return findPath;
    }

    private static List<Place> writePathFrom(Queue<DijkstraNode> path, Place start, Place finish) {
        int counter = 0;
        System.out.println("Starts from " + start.getName());
        List<Place> findPath = new LinkedList<>();
        while (counter != path.size()) {
            DijkstraNode node = path.poll();
            if (node.place.getName().equals(finish.getName())) {
                DijkstraNode tempWritePathList = node.predecessor;
                findPath.add(node.place);
                if (tempWritePathList != null) {
                    while (tempWritePathList.predecessor != null) {
                        findPath.add(tempWritePathList.place);
                        tempWritePathList = tempWritePathList.predecessor;
                    }
                }
                findPath.add(start);
            }
            System.out.print("Path to: " + node.place.getName() + " price of path " + node.minPath);
            if (node.predecessor != null) {
                DijkstraNode temp = node.predecessor;
                if (temp.predecessor != null) {
                    while (temp.predecessor != null) {
                        System.out.print(" From: " + temp.place.getName());
                        temp = temp.predecessor;
                    }
                    System.out.println("");
                } else {
                    System.out.println(" From: " + temp.place.getName());
                }
            } else {
                System.out.println("");
            }
        }
        return findPath;
    }

    private static void successorMatrix(LinkedList<DijkstraNode> path) {
        String[][] array = new String[path.size() + 1][path.size()];
        Map<Place, Place> close = new HashMap<>();

        // fill column from
        System.out.println("");
        array[0][0] = "NULL";
        for (int i = 1; i < path.size() + 1; i++) {
            if (!close.containsKey(path.get(i - 1).place)) {
                array[i][0] = path.get(i - 1).place.getName();
                close.put(path.get(i - 1).place, path.get(i - 1).place);
            }
        }
        // fill column to
        close.clear();
        for (int i = 1; i < path.size(); i++) {
            if (!close.containsKey(path.get(i).place)) {
                array[0][i] = path.get(i).place.getName();
                close.put(path.get(i).place, path.get(i).place);
            }
        }

        for (int i = 0; i < path.size(); i++) {
            DijkstraNode temp = path.get(i);
            if (temp.predecessor != null) {
                while (temp.predecessor != null) {
                    String defaultPlace = array[1][0];
                    int position = getCount(path, temp.predecessor.place);
                    array[position + 1][i] = "" + temp.place.getName();
                    temp = temp.predecessor;
                    if (temp.place.getName().equals(defaultPlace)) {
                        break;
                    }
                }
            }
        }
        write(array);
    }

    public static void write(String[][] pole) {
        for (String[] strings : pole) {

            if (isNull(strings)) {
                continue;
            }

            for (String string : strings) {
                try {
                    System.out.printf("%20s", "" + string);
                } catch (Exception ex) {
                    System.out.printf("%20s", "NULL");
                }
            }
            System.out.println();
        }
    }

    public static int getCount(LinkedList<DijkstraNode> paths, Place place) {
        for (int i = 0; i < paths.size(); i++) {
            if (paths.get(i).place.getName().equals(place.getName())) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isNull(String[] string) {
        int NULL = 1;
        for (int i = 1; i < string.length; i++) {
            try {
                if (string[i].equalsIgnoreCase(null)) {
                    NULL++;
                }
            } catch (Exception e) {
                NULL++;
            }
        }
        return string.length == NULL;
    }

    private static class DijkstraNode implements Comparable {
        double minPath;
        DijkstraNode predecessor;
        Place place;


        public DijkstraNode(Place place) {
            this.place = place;
            minPath = Integer.MAX_VALUE;
        }

        @Override
        public String toString() {
            if (predecessor == null) {
                return "[Distance=" + minPath + ", place=" + place.getName() + ']' + "\n";
            } else {
                return "[Distance=" + minPath + ", place=" + place.getName() + " over=" + predecessor.place.getName() + ']' + "\n";
            }
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }
}