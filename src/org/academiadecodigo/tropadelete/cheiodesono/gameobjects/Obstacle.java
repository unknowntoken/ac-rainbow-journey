package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.cheiodesono.Collision;

public class Obstacle implements GameObject {

    private Player player;
    private Picture picture;

    private boolean show;
    private static final int START_X = 800;
    private static final int START_Y = 500;
    private static final int HIT_POINTS = 1;
    private GameObjectHandler gameObjectHandler;


    public Obstacle(Player player, GameObjectHandler gameObjectHandler) {
        this.player = player;
        this.gameObjectHandler = gameObjectHandler;
        reset();
    }

    public void reset() {
        picture = new Picture(START_X, START_Y, ObstacleResource.getRandomType().getResource());
        picture.draw();
        show = true;

    }

    public void update() {
        if (show) {
            picture.translate(-1, 0);

        }
        if (hitPlayer()){
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
    public void hit(int damage) {

    }

    public void hide() {
        picture.delete();
        gameObjectHandler.remove(this);
        show = false;
    }

    public void show() {
        picture.draw();
        show = true;
    }
}
