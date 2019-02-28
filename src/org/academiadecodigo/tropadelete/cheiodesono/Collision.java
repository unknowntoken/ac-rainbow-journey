package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Line;

public abstract class Collision {

    static Line leftx = new Line(0,0,0,0);


    public static boolean collide(int xPosA, int yPosA, int widthA, int heightA,int xPosB, int yPosB, int widthB, int heightB){

        /*leftx.delete();
        leftx = new Line(xPosB,0,xPosB,600);
        leftx.draw();
*/
        //bottom
        leftx.delete();
        leftx = new Line(xPosB-100,(yPosB+heightB),(xPosB+widthB+100),(yPosB+heightB));
        leftx.draw();


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
