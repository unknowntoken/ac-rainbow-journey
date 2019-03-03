package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.cheiodesono.Collision;

public class PowerUp implements GameObject{

    private Player player;
    private Picture picture;

    private boolean dead;
    private boolean show;
    private int deadCounter;
    private static final int START_X = 700;
    private static final int START_Y = 500;
    private static final int HEALTH_POINTS = 1;

    private GameObjectHandler gameObjectHandler;

    public PowerUp(Player player, GameObjectHandler gameObjectHandler) {
        this.player = player;
        this.gameObjectHandler = gameObjectHandler;
        dead = false;
        deadCounter = 0;
        reset();
    }

    public void reset() {
        picture = new Picture(START_X, START_Y, PowerUpResource.getRandomType().getResource());
        show = true;

    }

    public void update() {
        if (dead){
            if(deadCounter >= 100){
                picture.delete();
                gameObjectHandler.remove(this);
            }
            deadCounter++;
            return;
        }
        if (show) {
            picture.translate(-1, 0);

        }
        if (hitPlayer()){
            player.addHealth(HEALTH_POINTS);
            picture.delete();
            picture = new Picture(picture.getX(), picture.getY()-100, "resources/images/powerup.png");
            picture.draw();
            dead = true;
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
        show = false;
    }

    public void show() {
        picture.draw();
        show = true;
    }
}
