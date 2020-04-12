package classes;

import graph.Graph;
import IOOperations.InOutStream;
import interfaces.IAbstractGraph;
import interfaces.IPlace;

import java.util.Random;

public class MyMain {
    public static void main(String[] args) throws Exception {

        Random rn = new Random();
        //rn.nextInt(130) + 1;


        IPlace IPlaceB = new Crossroad("B", rn.nextInt(120) + 1, rn.nextInt(120) + 1);

        IPlace IPlaceD = new Crossroad("D", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceE = new Station("E", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlaceF = new Crossroad("F", rn.nextInt(120) + 1, rn.nextInt(120) + 1);

        IPlace IPlaceA = new Landing("A", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        IPlace IPlaceC = new Landing("C", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        IPlace IPlaceG = new Landing("G", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        IPlace IPlaceH = new Landing("H", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        IPlace IPlaceI = new Landing("I", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        IPlace IPlaceJ = new Landing("J", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        IPlace IPlaceK = new Landing("K", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        IPlace IPlaceL = new Landing("L", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        IPlace IPlaceM = new Landing("M", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN
        IPlace IPlaceN = new Landing("N", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.GREEN

        IPlace IPlace1 = new Station("1", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace2 = new Station("2", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace3 = new Station("3", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace4 = new Station("4", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace5 = new Station("5", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace6 = new Station("6", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace7 = new Station("7", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace8 = new Station("8", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace9 = new Station("9", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace10 = new Station("10", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace11 = new Station("11", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace12 = new Station("12", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace13 = new Station("13", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace14 = new Station("14", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace15 = new Station("15", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace16 = new Station("16", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace17 = new Station("17", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace18 = new Station("18", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace19 = new Station("19", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE
        IPlace IPlace20 = new Station("20", rn.nextInt(120) + 1, rn.nextInt(120) + 1); // Color.BLUE

        IPlace IPlaceA1 = new Crossroad("A1", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA2 = new Crossroad("A2", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA3 = new Crossroad("A3", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA4 = new Crossroad("A4", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA5 = new Crossroad("A5", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA6 = new Crossroad("A6", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA7 = new Crossroad("A7", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA8 = new Crossroad("A8", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA9 = new Crossroad("A9", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA10 = new Crossroad("A10", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA11 = new Crossroad("A11", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA12 = new Crossroad("A12", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA13 = new Crossroad("A13", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA14 = new Crossroad("A14", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA15 = new Crossroad("A15", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA16 = new Crossroad("A16", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA17 = new Crossroad("A17", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA18 = new Crossroad("A18", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA19 = new Crossroad("A19", rn.nextInt(120) + 1, rn.nextInt(120) + 1);
        IPlace IPlaceA20 = new Crossroad("A20", rn.nextInt(120) + 1, rn.nextInt(120) + 1);



        Path pathAB = new Path(IPlaceA, IPlaceB, 1);
        Path pathAC = new Path(IPlaceA, IPlaceC, 1);

        Path pathBD = new Path(IPlaceB, IPlaceD, 1);
        Path pathBF = new Path(IPlaceB, IPlaceF, 1);

        Path pathCE = new Path(IPlaceC, IPlaceE, 1);

        Path pathDF = new Path(IPlaceD, IPlaceF, 1);
        Path pathDE = new Path(IPlaceD, IPlaceE, 1);

        Path pathFE = new Path(IPlaceF, IPlaceE, 1);


        IAbstractGraph<String, IPlace, Path> graph = new Graph<>();



        graph.addNode(IPlaceB.getName(), IPlaceB);
        graph.addNode(IPlaceD.getName(), IPlaceD);
        graph.addNode(IPlaceE.getName(), IPlaceE);
        graph.addNode(IPlaceF.getName(), IPlaceF);


        graph.addNode(IPlaceC.getName(), IPlaceC);
        graph.addNode(IPlaceA.getName(), IPlaceA);
        graph.addNode(IPlaceG.getName(), IPlaceG);
        graph.addNode(IPlaceH.getName(), IPlaceH);
        graph.addNode(IPlaceI.getName(), IPlaceI);
        graph.addNode(IPlaceJ.getName(), IPlaceJ);
        graph.addNode(IPlaceK.getName(), IPlaceK);
        graph.addNode(IPlaceL.getName(), IPlaceL);
        graph.addNode(IPlaceM.getName(), IPlaceM);
        graph.addNode(IPlaceN.getName(), IPlaceN);

        graph.addNode(IPlace1.getName(), IPlace1);
        graph.addNode(IPlace2.getName(), IPlace2);
        graph.addNode(IPlace3.getName(), IPlace3);
        graph.addNode(IPlace4.getName(), IPlace4);
        graph.addNode(IPlace5.getName(), IPlace5);
        graph.addNode(IPlace6.getName(), IPlace6);
        graph.addNode(IPlace7.getName(), IPlace7);
        graph.addNode(IPlace8.getName(), IPlace8);
        graph.addNode(IPlace9.getName(), IPlace9);
        graph.addNode(IPlace10.getName(), IPlace10);
        graph.addNode(IPlace11.getName(), IPlace11);
        graph.addNode(IPlace12.getName(), IPlace12);
        graph.addNode(IPlace13.getName(), IPlace13);
        graph.addNode(IPlace14.getName(), IPlace14);
        graph.addNode(IPlace15.getName(), IPlace15);
        graph.addNode(IPlace16.getName(), IPlace16);
        graph.addNode(IPlace17.getName(), IPlace17);
        graph.addNode(IPlace18.getName(), IPlace18);
        graph.addNode(IPlace19.getName(), IPlace19);
        graph.addNode(IPlace20.getName(), IPlace20);

        graph.addNode(IPlaceA1.getName(), IPlaceA1);
        graph.addNode(IPlaceA2.getName(), IPlaceA2);
        graph.addNode(IPlaceA3.getName(), IPlaceA3);
        graph.addNode(IPlaceA4.getName(), IPlaceA4);
        graph.addNode(IPlaceA5.getName(), IPlaceA5);
        graph.addNode(IPlaceA6.getName(), IPlaceA6);
        graph.addNode(IPlaceA7.getName(), IPlaceA7);
        graph.addNode(IPlaceA8.getName(), IPlaceA8);
        graph.addNode(IPlaceA9.getName(), IPlaceA9);
        graph.addNode(IPlaceA10.getName(), IPlaceA10);
        graph.addNode(IPlaceA11.getName(), IPlaceA11);
        graph.addNode(IPlaceA12.getName(), IPlaceA12);
        graph.addNode(IPlaceA13.getName(), IPlaceA13);
        graph.addNode(IPlaceA14.getName(), IPlaceA14);
        graph.addNode(IPlaceA15.getName(), IPlaceA15);
        graph.addNode(IPlaceA16.getName(), IPlaceA16);
        graph.addNode(IPlaceA17.getName(), IPlaceA17);
        graph.addNode(IPlaceA18.getName(), IPlaceA18);
        graph.addNode(IPlaceA19.getName(), IPlaceA19);
        graph.addNode(IPlaceA20.getName(), IPlaceA20);






        graph.addPath(pathAB.getStartIPlace().getName(), pathAB.getFinishIPlace().getName(), pathAB);
        graph.addPath(IPlaceA.getName(), IPlaceC.getName(), pathAC);

        graph.addPath(IPlaceB.getName(), IPlaceD.getName(), pathBD);
        graph.addPath(IPlaceB.getName(), IPlaceF.getName(), pathBF);

        graph.addPath(IPlaceC.getName(), IPlaceE.getName(), pathCE);

        graph.addPath(IPlaceD.getName(), IPlaceF.getName(), pathDF);
        graph.addPath(IPlaceD.getName(), IPlaceE.getName(), pathDE);

        graph.addPath(IPlaceF.getName(), IPlaceE.getName(), pathFE);

        // call for store graph
        InOutStream.storeGraph(graph);
    }
}

