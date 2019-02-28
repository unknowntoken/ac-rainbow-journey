package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Obstacle {

    private Player player;
    private Picture picture;
    private boolean show;
    private static final int START_X = 800;
    private static final int START_Y = 450;


    public Obstacle(Player player, ObstacleImage type) {
        this.player = player;
        reset();
    }

    public void reset() {
        picture = new Picture(START_X, START_Y, ObstacleImage.getRandomType().getResource());
        show = false;

    }

    public void update() {
        if (show) {
            picture.translate(-1, 0);
            //System.out.println(picture.getX() +";" + player.getX());
            //System.out.println(picture.getY() +";" + player.getY());
            boolean collided = Collision.collide(picture.getX(), picture.getY(), picture.getWidth(), picture.getHeight(),
                    player.getX(), player.getY(), player.getWidth(), picture.getHeight());
            if (collided) {
                System.out.println("Collision: ");
            }
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
