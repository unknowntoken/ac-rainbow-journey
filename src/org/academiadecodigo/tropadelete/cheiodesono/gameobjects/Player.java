package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.cheiodesono.Game;
import org.academiadecodigo.tropadelete.cheiodesono.Sound;
import org.academiadecodigo.tropadelete.cheiodesono.gameobjects.GameObject;


public class Player implements GameObject {
    private static final int JUMP_HEIGHT = 200;

    private int health;
    private boolean jumping;
    private boolean down;
    private Sound jumpSound;


    private int jumpCounter;
    private Sprite sprite;

    private Picture healthPicture;

    private Rectangle[] healthPlayer;


    private int lowestY;


    public Player() {
        jumpCounter = 0;
        down = true;
        health = 10;
        sprite = new Sprite(50);
        jumpSound = new Sound("/resources/sounds/jump.wav");

        initHealthBar();
        initPlayerPicture();


        this.jumping = false;
    }

    private void initPlayerPicture() {

        sprite.addFrame(new Picture(40, 40, "resources/images/mary1.png"));
        sprite.addFrame(new Picture(40, 40, "resources/images/marySize.png"));
        lowestY = 600 - (sprite.getY() + sprite.getHeight());

    }


    private void initHealthBar() {
        healthPlayer = new Rectangle[health];
        healthPicture = new Picture(0, 30, "resources/health.png");
        healthPicture.draw();

        healthPlayer[0] = new Rectangle(30, 30, 20, 20);
        healthPlayer[0].setColor(Color.GREEN);
        healthPlayer[0].draw();
        healthPlayer[0].fill();

        for (int i = 1; i < healthPlayer.length; i++) {
            healthPlayer[i] = new Rectangle(healthPlayer[i - 1].getX() + 20, 30, 20, 20);
            healthPlayer[i].setColor(Color.GREEN);
            healthPlayer[i].draw();
            healthPlayer[i].fill();
        }


    }

    private void updateHealthBar() {
        for (int i = 0; i < healthPlayer.length; i++) {
            if (i >= health) {
                healthPlayer[i].delete();
                continue;
            }
            if (health >= 3 && health <= 7) {
                healthPlayer[i].setColor(Color.ORANGE);
            }
            if (health < 3) {
                healthPlayer[i].setColor(Color.RED);
            }
            healthPlayer[i].draw();
            healthPlayer[i].fill();
        }

    }

    public void update() {
        sprite.update();
        if (jumping) {
            jumpAction();
            return;
        }

        if (sprite.getY() < lowestY) {
            sprite.translate(0, 1);
            sprite.update();
            return;
        }
        down = false;
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void reset() {

    }

    private void jumpAction() {
        //System.out.println("Y:" + playerPicture.getY());
        //System.out.println("counter" + jumpCounter);

        jumpCounter++;
        sprite.translate(0, -1);
        sprite.update();
        if (jumpCounter >= JUMP_HEIGHT) {
            jumpCounter = 0;
            jumping = false;
            down = true;
        }
    }

    public void jump() {
        jumpSound.play(true);

        if (!down) {
            jumping = true;
            down = true;
        }
    }

    public void moveLeft() {
        if (Game.isOutOfBoundsLeft(sprite.getX() - 10)) {
            return;
        }
        sprite.translate(-10, 0);
        sprite.update();
    }

    public void moveRight() {
        if (Game.isOutOfBoundsRight(sprite.getX() + 10)) {
            return;
        }
        sprite.translate(10, 0);
        sprite.update();
    }

    public void releaseJump() {
        jumping = false;
        down = true;
        jumpCounter = 0;
        sprite.update();
    }

    public int getHealth() {
        return health;
    }

    public void hit(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        updateHealthBar();
        sprite.update();
    }

    public void addHealth(int health) {
        this.health += health;
        updateHealthBar();
        sprite.update();
    }

    public int getWidth() {
        return sprite.getWidth();
    }

    public int getHeight() {
        return sprite.getHeight();
    }

    public int getX() {
        return sprite.getX();
    }

    public int getY() {
        return sprite.getY();
    }


}
