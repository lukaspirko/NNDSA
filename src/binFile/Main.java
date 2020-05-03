package binFile;

import classes.Crossroad;
import classes.Path;
import classes.RandomString;
import graph.Graph;
import interfaces.IAbstractDatabase;
import interfaces.IAbstractGraph;
import interfaces.IPlace;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "database.txt";

    public static void main(String[] args) {
        IAbstractGraph<String, IPlace, Path> graph = new Graph<>();
        RandomString session = new RandomString(5);
        List<String> listOfPlaces = new ArrayList<>();

        for (int i = 0; i < 5000; i++) {
            IPlace place = new Crossroad(session.nextString(), 1, 1);
            if (i % 100 == 0) {
                System.out.println(place.getName());
            }
            listOfPlaces.add(place.getName());
            graph.addNode(place.getName(), place);
        }

        IAbstractDatabase database = new Database(FILE_PATH);
        database.build(graph.getAllVortex(), 500, 10);


        int numberOfPlace = 66; // 0-4999

        System.out.println("\nYou choose to search and delete: " + listOfPlaces.get(numberOfPlace));
        String tmp = listOfPlaces.get(numberOfPlace);

        System.out.println("\nfinding by interpolation search:");
        database.interpolationSearch(tmp);

        System.out.println("\nfinding by binary search:");
        database.binarySearch(tmp);

        System.out.println("\nfinding place to delete:");
        IPlace data = database.delete(tmp);

        System.out.println("\nDeleted: " + data.getName());
    }
}
