package classes;

import IOOperations.InOutStream;
import interfaces.IAbstractGraph;
import interfaces.Place;

import java.util.Random;

public class MyMain {
    public static void main(String[] args) throws Exception {

        Random rn = new Random();
        //rn.nextInt(130) + 1;


        Place placeB = new Crossroad("B", rn.nextInt(120) + 1, rn.nextInt(120) + 1);

        Place placeD = new Crossroad("D", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeE = new Station("E", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place placeF = new Crossroad("F", rn.nextInt(120) + 1, rn.nextInt(120) + 1);

        Place placeA = new Landing("A", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        Place placeC = new Landing("C", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        Place placeG = new Landing("G", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        Place placeH = new Landing("H", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        Place placeI = new Landing("I", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        Place placeJ = new Landing("J", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        Place placeK = new Landing("K", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        Place placeL = new Landing("L", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        Place placeM = new Landing("M", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        Place placeN = new Landing("N", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN

        Place place1 = new Station("1", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place2 = new Station("2", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place3 = new Station("3", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place4 = new Station("4", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place5 = new Station("5", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place6 = new Station("6", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place7 = new Station("7", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place8 = new Station("8", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place9 = new Station("9", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place10 = new Station("10", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place11 = new Station("11", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place12 = new Station("12", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place13 = new Station("13", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place14 = new Station("14", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place15 = new Station("15", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place16 = new Station("16", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place17 = new Station("17", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place18 = new Station("18", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place19 = new Station("19", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        Place place20 = new Station("20", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE

        Place placeA1 = new Crossroad("A1", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA2 = new Crossroad("A2", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA3 = new Crossroad("A3", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA4 = new Crossroad("A4", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA5 = new Crossroad("A5", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA6 = new Crossroad("A6", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA7 = new Crossroad("A7", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA8 = new Crossroad("A8", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA9 = new Crossroad("A9", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA10 = new Crossroad("A10", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA11 = new Crossroad("A11", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA12 = new Crossroad("A12", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA13 = new Crossroad("A13", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA14 = new Crossroad("A14", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA15 = new Crossroad("A15", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA16 = new Crossroad("A16", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA17 = new Crossroad("A17", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA18 = new Crossroad("A18", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA19 = new Crossroad("A19", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        Place placeA20 = new Crossroad("A20", rn.nextInt(120) + 1, rn.nextInt(120) + 1);



        Path pathAB = new Path(placeA, placeB, 1);
        Path pathAC = new Path(placeA, placeC, 1);

        Path pathBD = new Path(placeB, placeD, 1);
        Path pathBF = new Path(placeB, placeF, 1);

        Path pathCE = new Path(placeC, placeE, 1);

        Path pathDF = new Path(placeD, placeF, 1);
        Path pathDE = new Path(placeD, placeE, 1);

        Path pathFE = new Path(placeF, placeE, 1);


        IAbstractGraph<String, Place, Path> graph = new Graph01<>();



        graph.addNode(placeB.getName(), placeB);
        graph.addNode(placeD.getName(), placeD);
        graph.addNode(placeE.getName(), placeE);
        graph.addNode(placeF.getName(), placeF);


        graph.addNode(placeC.getName(), placeC);
        graph.addNode(placeA.getName(), placeA);
        graph.addNode(placeG.getName(), placeG);
        graph.addNode(placeH.getName(), placeH);
        graph.addNode(placeI.getName(), placeI);
        graph.addNode(placeJ.getName(), placeJ);
        graph.addNode(placeK.getName(), placeK);
        graph.addNode(placeL.getName(), placeL);
        graph.addNode(placeM.getName(), placeM);
        graph.addNode(placeN.getName(), placeN);

        graph.addNode(place1.getName(), place1);
        graph.addNode(place2.getName(), place2);
        graph.addNode(place3.getName(), place3);
        graph.addNode(place4.getName(), place4);
        graph.addNode(place5.getName(), place5);
        graph.addNode(place6.getName(), place6);
        graph.addNode(place7.getName(), place7);
        graph.addNode(place8.getName(), place8);
        graph.addNode(place9.getName(), place9);
        graph.addNode(place10.getName(), place10);
        graph.addNode(place11.getName(), place11);
        graph.addNode(place12.getName(), place12);
        graph.addNode(place13.getName(), place13);
        graph.addNode(place14.getName(), place14);
        graph.addNode(place15.getName(), place15);
        graph.addNode(place16.getName(), place16);
        graph.addNode(place17.getName(), place17);
        graph.addNode(place18.getName(), place18);
        graph.addNode(place19.getName(), place19);
        graph.addNode(place20.getName(), place20);

        graph.addNode(placeA1.getName(), placeA1);
        graph.addNode(placeA2.getName(), placeA2);
        graph.addNode(placeA3.getName(), placeA3);
        graph.addNode(placeA4.getName(), placeA4);
        graph.addNode(placeA5.getName(), placeA5);
        graph.addNode(placeA6.getName(), placeA6);
        graph.addNode(placeA7.getName(), placeA7);
        graph.addNode(placeA8.getName(), placeA8);
        graph.addNode(placeA9.getName(), placeA9);
        graph.addNode(placeA10.getName(), placeA10);
        graph.addNode(placeA11.getName(), placeA11);
        graph.addNode(placeA12.getName(), placeA12);
        graph.addNode(placeA13.getName(), placeA13);
        graph.addNode(placeA14.getName(), placeA14);
        graph.addNode(placeA15.getName(), placeA15);
        graph.addNode(placeA16.getName(), placeA16);
        graph.addNode(placeA17.getName(), placeA17);
        graph.addNode(placeA18.getName(), placeA18);
        graph.addNode(placeA19.getName(), placeA19);
        graph.addNode(placeA20.getName(), placeA20);






        graph.addPath(pathAB.getStartPlace().getName(), pathAB.getFinishPlace().getName(), pathAB);
        graph.addPath(placeA.getName(), placeC.getName(), pathAC);

        graph.addPath(placeB.getName(), placeD.getName(), pathBD);
        graph.addPath(placeB.getName(), placeF.getName(), pathBF);

        graph.addPath(placeC.getName(), placeE.getName(), pathCE);

        graph.addPath(placeD.getName(), placeF.getName(), pathDF);
        graph.addPath(placeD.getName(), placeE.getName(), pathDE);

        graph.addPath(placeF.getName(), placeE.getName(), pathFE);

        // call for store graph
        InOutStream.storeGraph(graph);
    }
}

