package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Obstacle {
    private int countMov;
    private Player player;
    private Picture picture;


    public Obstacle(Player player, ObstacleImage type) {
        this.player = player;
        countMov = 0;
        picture = new Picture(700, 500, ObstacleImage.JS.getResource());
        picture.draw();
    }

    public void update() {


        picture.translate(-1, 0);


        //checkCollision ();

    }

    private void checkCollision() {

    }


}
