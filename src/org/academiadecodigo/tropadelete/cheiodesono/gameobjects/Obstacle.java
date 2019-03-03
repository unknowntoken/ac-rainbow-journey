package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.cheiodesono.Collision;
import org.academiadecodigo.tropadelete.cheiodesono.Sound;

public class Obstacle implements GameObject {

    private Player player;
    private Picture picture;
    private Sound impact;
    private boolean show;
    private static final int START_X = 800;
    private static final int START_Y = 500;
    private static final int HIT_POINTS = 1;
    private GameObjectHandler gameObjectHandler;


    public Obstacle(Player player, GameObjectHandler gameObjectHandler) {
        this.player = player;
        this.gameObjectHandler = gameObjectHandler;
        reset();
        impact = new Sound("/resources/sounds/impact.wav");
    }

    public void reset() {
        picture = new Picture(START_X, START_Y, ObstacleResource.getRandomType().getResource());
        show = true;
    }

    public void update() {
        if (show) {
            picture.translate(-1, 0);


        }
        if (hitPlayer()){
            impact.play(true);
            player.hit(HIT_POINTS);
            hide();
            gameObjectHandler.remove(this);
        }
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
        //gameObjectHandler.remove(this);
        show = false;
    }

    public void show() {
        picture.draw();
        show = true;
    }
}
