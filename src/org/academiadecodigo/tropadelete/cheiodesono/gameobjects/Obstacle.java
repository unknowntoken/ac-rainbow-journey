package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.cheiodesono.Collision;
import org.academiadecodigo.tropadelete.cheiodesono.Sound;

public class Obstacle implements GameObject {

    private Player player;
    private Picture picture;
    private Sound impact;
    private static final int START_X = 800;
    private static final int START_Y = 500;
    private static final int HIT_POINTS = 1;
    private static final int DEAD_FRAMES = 50;
    private int deadCounter;
    private boolean dead;
    private GameObjectHandler gameObjectHandler;


    public Obstacle(Player player, GameObjectHandler gameObjectHandler) {
        this.player = player;
        this.gameObjectHandler = gameObjectHandler;
        deadCounter = 0;
        reset();
        impact = new Sound("/resources/sounds/impact.wav");
    }

    public void reset() {
        picture = new Picture(START_X, START_Y, ObstacleResource.getRandomType().getResource());
    }

    public void update() {
        if (dead) {
            if (deadCounter >= DEAD_FRAMES) {
                hide();
                gameObjectHandler.remove(this);
            }
            deadCounter++;
            return;
        }

        if (hitPlayer()) {
            impact.play(true);
            player.hit(HIT_POINTS);

            picture.delete();
            picture = new Picture(picture.getX(), picture.getY()-30, "resources/images/bang.png");
            dead = true;
            picture.draw();
            return;
        }

        picture.translate(-1, 0);
    }

    private boolean hitPlayer() {
        return Collision.collide(picture.getX(), picture.getY(), picture.getWidth(), picture.getHeight(),
                player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    public int getX() {
        return picture.getX();
    }

    public int getY() {
        return picture.getY();
    }

    @Override
    public int getWidth() {
        return picture.getWidth();
    }

    @Override
    public int getHeight() {
        return picture.getHeight();
    }

    @Override
    public void hit(int damage) {

    }

    public void hide() {
        picture.delete();
    }

    public void show() {
        picture.draw();
    }
}
