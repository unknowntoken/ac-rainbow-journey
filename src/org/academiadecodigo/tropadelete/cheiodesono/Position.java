package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Position {

    private int x;
    private int y;


    public Position(int x , int y){

        this.x = x;
        this.y = y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(Position position){
        this.x = position.getX();
        this.y = position.getY();

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}