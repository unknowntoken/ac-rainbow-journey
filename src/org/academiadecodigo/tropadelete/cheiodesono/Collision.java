package org.academiadecodigo.tropadelete.cheiodesono;

public abstract class Collision {

    public static boolean collide(int xPosA, int yPosA, int widthA, int heightA,int xPosB, int yPosB, int widthB, int heightB){
        return
                //Checking for intersection of rectangle A with rectangleB
                //Origin is top left corner of each rectangle
                //If condition is true the rectangle A AND rectangle B intersect and thus collide

                xPosA + widthA > xPosB
                        &&
                yPosA +  heightA > yPosB
                                        &&
                xPosB + widthB > xPosA
                         &&
                yPosB + heightB > yPosA;
    }
}
