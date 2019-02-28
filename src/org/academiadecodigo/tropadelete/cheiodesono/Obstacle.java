package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Obstacle {

    private Player player;
    private Picture picture;

    private boolean show;
    private static final int START_X = 800;
    private static final int START_Y = 500;


    public Obstacle(Player player) {
        this.player = player;
        reset();
    }

    public void reset() {
        picture = new Picture(START_X, START_Y, ObstacleImage.getRandomType().getResource());

        //picture2 = new Picture(START_X, START_Y, ObstacleImage.getRandomType().getResource());
        //picture = new Rectangle(START_X, START_Y, picture2.getWidth(),picture2.getHeight());
        show = false;

    }

    public void update() {
        if (show) {
            picture.translate(-1, 0);
            //System.out.println(picture.getX() +";" + player.getX());
            //System.out.println(picture.getY() +";" + player.getY());
            boolean collided = Collision.collide(picture.getX(), picture.getY(), picture.getWidth(), picture.getHeight(),
                    player.getX(), player.getY(), player.getWidth(), player.getHeight());

        }
        //checkCollision ();
    }

    private void checkCollision() {

    }

    public int getX() {
        return picture.getX();
    }

    public int getY() {
        return picture.getY();
    }

    public void hide() {
        picture.delete();
        show = false;
    }

    public void show() {
        picture.draw();
        show = true;
    }
}
