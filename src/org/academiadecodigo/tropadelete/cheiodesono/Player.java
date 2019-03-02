package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;



public class Player {
    private static final int JUMP_HEIGHT = 200;

    private int health;
    private boolean jumping;
    private boolean down;


    private int animationCounter;
    private Picture picture;
    private Picture[] healthPlayer;

    private int lowestY;



    public Player() {
        animationCounter = 0;
        down = true;
        health = 10;
        picture = new Picture(40, 40, "resources/images/mary1.png");
        initHealthBar();

        picture.draw();
        this.jumping = false;

        lowestY = 600 - (picture.getY() + picture.getHeight());

    }

    private void initHealthBar (){
        healthPlayer = new Picture[health];
        healthPlayer[0] = new Picture(0,20,"resources/images/health0.png");
        healthPlayer[1] = new Picture(0,20,"resources/images/health0.png");
        healthPlayer[2] = new Picture(0,20,"resources/images/health0.png");
        healthPlayer[3] = new Picture(0,20,"resources/images/health1.png");
        healthPlayer[4] = new Picture(0,20,"resources/images/health1.png");
        healthPlayer[5] = new Picture(0,20,"resources/images/health1.png");
        healthPlayer[6] = new Picture(0,20,"resources/images/health2.png");
        healthPlayer[7] = new Picture(0,20,"resources/images/health2.png");
        healthPlayer[8] = new Picture(0,20,"resources/images/health2.png");
        healthPlayer[9] = new Picture(0,20,"resources/images/health2.png");

        healthPlayer[0].translate(30,0);
        healthPlayer[0].draw();
        for (int i = 1; i < healthPlayer.length; i++) {
            healthPlayer[i].translate(healthPlayer[i-1].getX()+(healthPlayer[i].getWidth()+10),0);
            healthPlayer[i].draw();
        }
        

    }

    private void updateHealthBar() {
        for (int i = 0; i < healthPlayer.length; i++) {
            if (i >= health){
                healthPlayer[i].delete();

            }
            Canvas.getInstance().repaint();
        }

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
            jumping = true;
            down = true;
        }
    }

    public void moveLeft() {
        if (Game.isOutOfBoundsLeft(picture.getX()-10)){
            return;
        }
        picture.translate(-10, 0);
    }

    public void moveRight() {
        if (Game.isOutOfBoundsRight(picture.getX()+10)){
            return;
        }
        picture.translate(10, 0);
    }

    public void releaseJump() {
        jumping = false;
        down = true;
        animationCounter =0;
    }

    public int getHealth (){
        return health;
    }
    public void hit(int damage) {
        health -= damage;
        if (health < 0){
            health =0;
        }
        updateHealthBar();
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
