package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class PowerUp {

    private Player player;
    private Picture picture;

    private boolean show;
    private static final int START_X = 800;
    private static final int START_Y = 500;
    private static final int HEALTH_POINTS = 1;


    public PowerUp(Player player) {
        this.player = player;
        reset();
    }

    public void reset() {
        picture = new Picture(START_X, START_Y, PowerUpResource.getRandomType().getResource());
        show = false;

    }

    public void update() {
        if (show) {
            picture.translate(-1, 0);

        }
        if (hitPlayer()){
            player.addHealth(HEALTH_POINTS);
            hide();
            reset();
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

    public void hide() {
        picture.delete();
        show = false;
    }

    public void show() {
        picture.draw();
        show = true;
    }
}
