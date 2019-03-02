package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;



public class Player {
    private static final int JUMP_HEIGHT = 200;

    private int health;
    private boolean jumping;
    private boolean down;


    private int animationCounter;
    private Picture picture;
    private Rectangle healthPlayer;

    private int lowestY;



    public Player() {
        animationCounter = 0;
        down = true;
        health = 10;

        picture = new Picture(40, 40, "resources/images/ze.png");
        createHealthPlayer();

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
        picture.translate(-10, 0);
    }

    public void moveRight() {
        System.out.println("move right");
        picture.translate(10, 0);
    }

    public void releaseJump() {
        jumping = false;
        down = true;
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

    public void addHealth(int health){
        this.health += health;
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

    public void createHealthPlayer() {
        //fullHealthPlayer = new Rectangle(getX()*(health), getY(),30,30);
        for (int i = 1 ; i < health+1 ; i++) {
            healthPlayer = new Rectangle((getX() -10 )* i, getY(), 30, 30);
            if (health < 5){
                healthPlayer.setColor(Color.RED);
                healthPlayer.fill();
            }else {
                healthPlayer.setColor(Color.GREEN);
                //healthPlayer.draw();
                healthPlayer.fill();
            }
            System.out.println(i);
            //fullHealthPlayer.fill();
        }
    }



}
