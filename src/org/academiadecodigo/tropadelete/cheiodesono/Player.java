package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public class Player {
    private static final int JUMP_HEIGHT = 100;
    private static final int HANG_TIME = 100;

    private int health;
    private boolean jumping;
    private boolean down;


    private int animationCounter;
    private Picture picture;
    private int lowestY;


    public Player() {
        animationCounter = 0;
        health = 100;
        down = true;

        picture = new Picture(40, 40, "resources/images/hero_chara_mario_pc.png");
        picture.draw();
        this.jumping = false;

        lowestY = 600 - (picture.getY() + picture.getHeight());

    }

    public void update() {

        if (jumping) {
            jumpAction();
            return;
        }

        if (picture.getY() < lowestY) {
            picture.translate(0, 1);
            return;
        }
        down = false;
    }

    private void jumpAction() {
        //System.out.println("Y:" + picture.getY());
        //System.out.println("counter" + animationCounter);



        animationCounter++;
        picture.translate(0, -1);
        if (animationCounter >= JUMP_HEIGHT) {
            animationCounter =0;
            down = true;
            jumping= false;
        }
    }

    public void jump() {
        if (!down) {
            jumping = true;
        }
    }

    public void moveLeft() {
        picture.translate(-10, 0);
    }

    public void moveRight() {
        System.out.println("move right");
        picture.translate(10, 0);
    }

    public void releaseJump() {
        jumping = false;
        animationCounter =0;
    }

    public void hit(int damage) {
        health -= damage;
    }

    public int getWidth() {
        return picture.getWidth();
    }

    public int getHeight() {
        return picture.getHeight();
    }

    public int getX() {
        return picture.getX();
    }

    public int getY() {
        return picture.getY();
    }
}
