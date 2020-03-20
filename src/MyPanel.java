import algorithms.Dijkstra002;
import classes.*;
import interfaces.IAbstractGraph;
import interfaces.Place;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

public class MyPanel extends JPanel {
    private final int CICLE_PARAM = 10;
    private final int OFFSET = 5;
    private final int MARGIN = 20;

    private boolean dijkstraEnable = false;
    private Place source;
    private Place destination;


    private IAbstractGraph<String, Place, Path> graph;

    public void setGraph(IAbstractGraph<String, Place, Path> graph) {
        this.graph = graph;
    }

    public void setFinder(String source, String destination) {
        this.source = graph.getVortex(source);
        this.destination = graph.getVortex(destination);
        if (this.source != null && this.destination != null) {
            dijkstraEnable = true;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (graph != null) {
            // print lines
            for (Place nod : graph.getAllVortex()) {
                for (Path path : graph.getIncidentEdges(nod.getName())) {
                    Line2D line = new Line2D.Double(MARGIN + path.getStartPlace().getX() * OFFSET, MARGIN + path.getStartPlace().getY() * OFFSET,
                            MARGIN + path.getFinishPlace().getX() * OFFSET, MARGIN + path.getFinishPlace().getY() * OFFSET);
                    g2.setPaint(Color.BLACK);
                    if (!path.isUsable()) {
                        g2.setPaint(Color.RED);
                        g2.setStroke(new BasicStroke(5));
                    }
                    g2.draw(line);
                    g2.setStroke(new BasicStroke(1));
                }
            }

            if (dijkstraEnable) {
                Dijkstra002 dijkstra = new Dijkstra002();
                List<Place> path = dijkstra.dijkstraAlgorithm(graph, source, destination);
                for (int i = 0; i < path.size() - 1; i++) {
                    Place temp = path.get(i + 1);

                    Line2D line = new Line2D.Double(MARGIN + path.get(i).getX() * OFFSET, MARGIN + path.get(i).getY() * OFFSET,
                            MARGIN + temp.getX() * OFFSET, MARGIN + temp.getY() * OFFSET);

                    g2.setStroke(new BasicStroke(5));

                    g2.setPaint(Color.YELLOW);
                    g2.draw(line);
                    g2.setStroke(new BasicStroke(1));

                }
                dijkstraEnable = false;
            }

            // print points
            for (Place nod : graph.getAllVortex()) {
                Ellipse2D oval = new Ellipse2D.Double(MARGIN + nod.getX() * OFFSET - (CICLE_PARAM / 2),
                        MARGIN + nod.getY() * OFFSET - (CICLE_PARAM / 2), CICLE_PARAM, CICLE_PARAM);

                if (nod instanceof Station) {
                    g2.setPaint(Color.BLUE);
                } else if (nod instanceof Landing) {
                    g2.setPaint(Color.GREEN);
                } else {
                    g2.setPaint(Color.RED);
                }
                g2.draw(oval);
                g2.fill(oval);
                g2.setPaint(Color.BLACK);
                g2.drawString(nod.getName(), MARGIN + nod.getX() * OFFSET - (CICLE_PARAM / 2),
                        MARGIN + nod.getY() * OFFSET - (CICLE_PARAM / 2));
            }
        }
    }
}