package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public class Player {
    private static final int JUMP_HEIGHT = 100;

    private int health;
    private boolean jumping;
    private boolean down;
    private int animationCounter;
    //private Picture picture;
    private Rectangle picture;


    public Player() {
        animationCounter = 0;
        //picture = new Picture(40, 400, "resources/images/hero_chara_mario_pc.png");
        picture = new Rectangle(40, 400, 136,163);
        picture.draw();
        this.jumping = false;
    }

    public void update() {
        if (jumping) {
            jumpAction();
        }
    }

    private void jumpAction() {
        if (down) {
            animationCounter--;
            picture.translate(0, 1);
            if (animationCounter == 0) {
                jumping = false;
                down = false;
            }
            return;
        }
        animationCounter++;
        picture.translate(0, -1);
        if (animationCounter >= JUMP_HEIGHT) {
            down = true;
        }
    }

    public void jump() {
        jumping = true;
    }

    public void hit(int damage) {

        health -= damage;


    }

    public int getWidth (){
        return picture.getWidth();
    }
    public int getHeight (){
        return picture.getHeight();
    }

    public int getX (){
        return picture.getX();
    }
    public int getY(){
        return picture.getY();
    }
}
