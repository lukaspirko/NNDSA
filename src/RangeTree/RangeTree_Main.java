package RangeTree;


import classes.Coordinates;
import interfaces.ICoordinates;

import java.util.ArrayList;
import java.util.List;

public class RangeTree_Main {


    public static void main(String[] args) {

        RangeTree01<ICoordinates> tree = new RangeTree01<>();

        ICoordinates coor1 = new Coordinates();
        coor1.setX(5);
        coor1.setY(5);
        ICoordinates coor2 = new Coordinates();
        coor2.setX(10);
        coor2.setY(5);

        ICoordinates coor3 = new Coordinates();
        coor3.setX(15);
        coor3.setY(5);

        ICoordinates coor4 = new Coordinates();
        coor4.setX(20);
        coor4.setY(5);


        List<ICoordinates> list = new ArrayList<>();




        list.add(coor2);
        list.add(coor1);
        list.add(coor3);



        tree.build(list);

        System.out.println("Success build");


        ICoordinates node = tree.simplyFind(coor4);

        tree.intervalFind(coor1, coor4);



    }
}