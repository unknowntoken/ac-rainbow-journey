package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {
    private static final int JUMP_HEIGHT = 200;


    private int health;
    private boolean jumping;
    private boolean down;
    private PlayerJumpSound jumpSound;

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
        jumpSound = new PlayerJumpSound("resources/sounds/386691__laurenmg95__player-jump.wav");

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
            jumping = false;
            down = true;
        }
    }

    public void jump() {
        if (!down) {
            jumpSound.play(true);
            jumping = true;
            down = true;
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
        down = true;
        jumpSound.close();
        animationCounter =0;
    }
    public void setHealth (int health){
        this.health = health;
    }
    public int getHealth (){
        return health;
    }
    public void hit(int damage) {
        health -= damage;
        System.out.println("Health: " + health);
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
