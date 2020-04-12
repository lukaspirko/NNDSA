import graph.Graph;
import IOOperations.InOutStream;
import classes.*;
import enums.TypeOfNode;
import interfaces.IAbstractGraph;
import interfaces.IPlace;

import javax.swing.*;


public class Main {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static IAbstractGraph<String, IPlace, Path> graph = new Graph<>();

    public static void main(String[] args) {


        JFrame f = new JFrame("My frame");
        MyPanel s = new MyPanel();

        // add node
        JButton addButton = new JButton("Add");
        JTextField xTextField = new JTextField("X", 20);
        JTextField yTextField = new JTextField("Y", 20);
        JTextField nameTextField = new JTextField("Name", 20);
        JComboBox<TypeOfNode> typeNodeList = new JComboBox<>();
        xTextField.setBounds(WIDTH - 120, 50, 40, 30);
        yTextField.setBounds(WIDTH - 60, 50, 40, 30);
        nameTextField.setBounds(WIDTH - 120, 20, 110, 30);
        typeNodeList.setBounds(WIDTH - 120, 80, 110, 30);
        addButton.setBounds(WIDTH - 120, 110, 110, 30);
        typeNodeList.addItem(TypeOfNode.CROSSROAD);
        typeNodeList.addItem(TypeOfNode.LANDING);
        typeNodeList.addItem(TypeOfNode.STATION);
        f.add(xTextField);
        f.add(yTextField);
        f.add(nameTextField);
        f.add(typeNodeList);
        addButton.addActionListener(e -> {
            int x = Integer.parseInt(xTextField.getText());
            int y = Integer.parseInt(yTextField.getText());
            TypeOfNode type = (TypeOfNode) typeNodeList.getSelectedItem();
            if (TypeOfNode.CROSSROAD == type) {
                Crossroad crossroad = new Crossroad(nameTextField.getText(), x, y);
                graph.addNode(crossroad.getName(), crossroad);
            } else if (TypeOfNode.STATION == type) {
                Station station = new Station(nameTextField.getText(), x, y);
                graph.addNode(station.getName(), station);
            } else {
                Landing landing = new Landing(nameTextField.getText(), x, y);
                graph.addNode(landing.getName(), landing);
            }
            s.setGraph(graph);
            f.repaint();
        });
        f.add(addButton);

        // add edge
        JButton addEdgeButton = new JButton("Add Edge");
        JTextField firstNode = new JTextField("node1", 20);
        JTextField secondNode = new JTextField("node2", 20);
        firstNode.setBounds(WIDTH - 60, 140, 40, 30);
        secondNode.setBounds(WIDTH - 120, 140, 40, 30);
        addEdgeButton.setBounds(WIDTH - 120, 170, 110, 30);
        f.add(firstNode);
        f.add(secondNode);
        addEdgeButton.addActionListener(e -> {
            IPlace IPlaceStart = graph.getVortex(firstNode.getText());
            IPlace IPlaceFinish = graph.getVortex(secondNode.getText());
            Path path = new Path(IPlaceStart, IPlaceFinish, 1);
            try {
                graph.addPath(path.getStartIPlace().getName(), path.getFinishIPlace().getName(), path);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            s.setGraph(graph);
            f.repaint();
        });
        f.add(addEdgeButton);

        // delete/activate/deactivate edge
        JButton deleteEdgeButton = new JButton("Delete Edge");
        JButton activateEdge = new JButton("Activate Edge");
        JButton deactivateEdge = new JButton("deActivate Edge");
        deleteEdgeButton.setBounds(WIDTH - 120, 200, 110, 30);
        activateEdge.setBounds(WIDTH - 120, 230, 110, 30);
        deactivateEdge.setBounds(WIDTH - 120, 260, 110, 30);
        activateEdge.addActionListener(e -> {
            Path path = graph.getEdge(firstNode.getText(), secondNode.getText());
            if (path != null && !path.isUsable()) {
                path.setUsable(true);
            }
            s.setGraph(graph);
            f.repaint();
        });
        f.add(activateEdge);
        deactivateEdge.addActionListener(e -> {
            Path path = graph.getEdge(firstNode.getText(), secondNode.getText());
            if (path != null && path.isUsable()) {
                path.setUsable(false);
            }
            s.setGraph(graph);
            f.repaint();
        });
        f.add(deactivateEdge);
        deleteEdgeButton.addActionListener(e -> {
            graph.removePath(firstNode.getText(), secondNode.getText());
            s.setGraph(graph);
            f.repaint();
        });
        f.add(deleteEdgeButton);

        // delete node
        JTextField deleteNode = new JTextField("name_node", 20);
        JButton deleteNodeButton = new JButton("Delete Node");
        deleteNode.setBounds(WIDTH - 120, 290, 110, 30);
        deleteNodeButton.setBounds(WIDTH - 120, 320, 110, 30);
        f.add(deleteNode);
        deleteNodeButton.addActionListener(e -> {
            graph.removeNode(deleteNode.getText());
            s.setGraph(graph);
            f.repaint();
        });
        f.add(deleteNodeButton);

        // save/load
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        saveButton.setBounds(WIDTH - 120, 350, 110, 30);
        loadButton.setBounds(WIDTH - 120, 380, 110, 30);
        saveButton.addActionListener(e -> InOutStream.storeGraph(graph));
        f.add(saveButton);
        loadButton.addActionListener(e -> {
            graph = InOutStream.loadGraphFromFile();
            s.setGraph(graph);
            f.repaint();
        });
        f.add(loadButton);

        // calculate trip
        JTextField findPathNode1 = new JTextField("nodeF", 20);
        JTextField findPathNode2 = new JTextField("nodeS", 20);
        JButton findButton = new JButton("Find Way");
        findPathNode1.setBounds(WIDTH - 60, 410, 40, 30);
        findPathNode2.setBounds(WIDTH - 120, 410, 40, 30);
        findButton.setBounds(WIDTH - 120, 440, 110, 30);
        findButton.addActionListener(e -> {
            s.setFinder(findPathNode1.getText(), findPathNode2.getText());
            s.setGraph(graph);
            f.repaint();
        });
        f.add(findButton);
        f.add(findPathNode1);
        f.add(findPathNode2);


        f.setResizable(false);
        f.add(s);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WIDTH, HEIGHT);

    }
}