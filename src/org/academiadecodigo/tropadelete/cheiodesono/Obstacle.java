package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Obstacle {
    private int positionX;
    private int positionY;
    private Player player;
    private Picture picture;


    public Obstacle (Player player, ObstacleImage type){
        this.player = player;

        picture = new Picture(40,40,type.getResource());
        picture.draw();
    }

    public void update (){

        checkCollision ();

    }

    private void checkCollision (){

    }


}
