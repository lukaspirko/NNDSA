package algorithms;

import classes.Path;
import interfaces.IAbstractGraph;
import interfaces.IPlace;

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


    public static Queue<DijkstraNode> countDistance(IAbstractGraph<String, IPlace, Path> graph, Queue<DijkstraNode> queue) {
        Map<IPlace, DijkstraNode> close = new HashMap<>();
        Queue<DijkstraNode> openList = new PriorityQueue<>();
        while (!queue.isEmpty()) {
            DijkstraNode place = queue.poll();

            if (!close.containsKey(place.IPlace)) {
                close.put(place.IPlace, place);
            }
            List<Path> edges = graph.getIncidentEdges(place.IPlace.getName());

            if (edges == null) {
                System.out.println("NOT foumd: " + place.IPlace.getName());
                break;
            }
            for (Path h : edges) {

                DijkstraNode city;
                IPlace m;
                Path path = h;

                if (path.isUsable()) {
                    IPlace finishPalce = h.getFinishIPlace();
                    if (finishPalce.getName().equals(place.IPlace.getName())) {
                        m = h.getStartIPlace();
                    } else {
                        m = h.getFinishIPlace();
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
                        close.put(city.IPlace, city);
                        queue.add(city);
                    }
                }
            }
        }
        for (Map.Entry<IPlace, DijkstraNode> entrySet : close.entrySet()) {
            DijkstraNode value = entrySet.getValue();
            openList.add(value);
        }
        return openList;
    }

    public static List<IPlace> dijkstraAlgorithm(IAbstractGraph<String, IPlace, Path> graph, IPlace start, IPlace finish) {
        DijkstraNode startNode = new DijkstraNode(start);
        startNode.minPath = 0;
        Queue<DijkstraNode> queue = new PriorityQueue<>();
        queue.add(startNode);

        Queue<DijkstraNode> path = countDistance(graph, queue);

        List<IPlace> findPath = new LinkedList<>();

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

    private static List<IPlace> writePathFrom(Queue<DijkstraNode> path, IPlace start, IPlace finish) {
        int counter = 0;
        System.out.println("Starts from " + start.getName());
        List<IPlace> findPath = new LinkedList<>();
        while (counter != path.size()) {
            DijkstraNode node = path.poll();
            if (node.IPlace.getName().equals(finish.getName())) {
                DijkstraNode tempWritePathList = node.predecessor;
                findPath.add(node.IPlace);
                if (tempWritePathList != null) {
                    while (tempWritePathList.predecessor != null) {
                        findPath.add(tempWritePathList.IPlace);
                        tempWritePathList = tempWritePathList.predecessor;
                    }
                }
                findPath.add(start);
            }
            System.out.print("Path to: " + node.IPlace.getName() + " price of path " + node.minPath);
            if (node.predecessor != null) {
                DijkstraNode temp = node.predecessor;
                if (temp.predecessor != null) {
                    while (temp.predecessor != null) {
                        System.out.print(" From: " + temp.IPlace.getName());
                        temp = temp.predecessor;
                    }
                    System.out.println("");
                } else {
                    System.out.println(" From: " + temp.IPlace.getName());
                }
            } else {
                System.out.println("");
            }
        }
        return findPath;
    }

    private static void successorMatrix(LinkedList<DijkstraNode> path) {
        String[][] array = new String[path.size() + 1][path.size()];
        Map<IPlace, IPlace> close = new HashMap<>();

        // fill column from
        System.out.println("");
        array[0][0] = "NULL";
        for (int i = 1; i < path.size() + 1; i++) {
            if (!close.containsKey(path.get(i - 1).IPlace)) {
                array[i][0] = path.get(i - 1).IPlace.getName();
                close.put(path.get(i - 1).IPlace, path.get(i - 1).IPlace);
            }
        }
        // fill column to
        close.clear();
        for (int i = 1; i < path.size(); i++) {
            if (!close.containsKey(path.get(i).IPlace)) {
                array[0][i] = path.get(i).IPlace.getName();
                close.put(path.get(i).IPlace, path.get(i).IPlace);
            }
        }

        for (int i = 0; i < path.size(); i++) {
            DijkstraNode temp = path.get(i);
            if (temp.predecessor != null) {
                while (temp.predecessor != null) {
                    String defaultPlace = array[1][0];
                    int position = getCount(path, temp.predecessor.IPlace);
                    array[position + 1][i] = "" + temp.IPlace.getName();
                    temp = temp.predecessor;
                    if (temp.IPlace.getName().equals(defaultPlace)) {
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

    public static int getCount(LinkedList<DijkstraNode> paths, IPlace IPlace) {
        for (int i = 0; i < paths.size(); i++) {
            if (paths.get(i).IPlace.getName().equals(IPlace.getName())) {
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
        IPlace IPlace;


        public DijkstraNode(IPlace IPlace) {
            this.IPlace = IPlace;
            minPath = Integer.MAX_VALUE;
        }

        @Override
        public String toString() {
            if (predecessor == null) {
                return "[Distance=" + minPath + ", IPlace=" + IPlace.getName() + ']' + "\n";
            } else {
                return "[Distance=" + minPath + ", IPlace=" + IPlace.getName() + " over=" + predecessor.IPlace.getName() + ']' + "\n";
            }
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }
}