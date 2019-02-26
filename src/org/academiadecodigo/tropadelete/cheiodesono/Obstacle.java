package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Obstacle {

    private Player player;
    private Picture picture;


    public Obstacle(Player player, ObstacleImage type) {
        this.player = player;
        picture = new Picture(700, 500, ObstacleImage.JS.getResource());
        picture.draw();
    }

    public void update() {

        picture.translate(-1, 0);

        //checkCollision ();
    }

    private void checkCollision() {

    }

    public int getX(){
        return picture.getX();
    }

    public int getY(){
        return picture.getY();
    }

    public void hide(){
        picture.delete();
    }
}
