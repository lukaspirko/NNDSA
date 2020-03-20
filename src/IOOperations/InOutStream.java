package IOOperations;

import classes.Path;
import interfaces.IAbstractGraph;
import interfaces.Place;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InOutStream {

    private static final String FILE_PATH = "f.txt";

    public static void storeGraph(IAbstractGraph<String, Place, Path> graph) {
        try {
            // Creating stream and writing the object
            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(graph);
            objectOut.flush();
            objectOut.close();
            System.out.println("Success saved graph!"); //TODO remove
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static IAbstractGraph<String, Place, Path> loadGraphFromFile() {
        IAbstractGraph graph = null;
        try {
            // Creating stream to read the object
            ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(FILE_PATH));
            graph = (IAbstractGraph<String, Place, Path>) objectIn.readObject();
            objectIn.close();
            System.out.println("Success load up graph!"); //TODO remoce
        } catch (Exception e) {
            System.out.println(e);
        }
        return graph;
    }
}
