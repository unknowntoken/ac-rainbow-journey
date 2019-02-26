package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Obstacle {

    private Player player;
    private Picture picture;
    private boolean show;


    public Obstacle(Player player, ObstacleImage type) {
        this.player = player;
        picture = new Picture(700, 500, ObstacleImage.JS.getResource());
        show = false;
    }

    public void update() {

        if  (show) {
            picture.translate(-1, 0);
        }
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
        show = false;
    }

    public void show(){
        picture.draw();
        show = true;
    }
}
