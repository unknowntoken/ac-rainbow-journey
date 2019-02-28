package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Obstacle {

    private Player player;
    private Picture picture;
    private boolean show;


    public Obstacle(Player player, ObstacleImage type) {
        this.player = player;
        picture = new Picture(700, 500, ObstacleImage.getRandomType().getResource());
        show = false;
    }

    public void update() {
        if  (show) {
            picture.translate(-1, 0);
            Collision.collide(picture.getX(),picture.getY(),picture.getWidth(),picture.getHeight())
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
