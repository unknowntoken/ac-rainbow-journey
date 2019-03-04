package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.cheiodesono.Game;
import org.academiadecodigo.tropadelete.cheiodesono.Sound;

import java.util.LinkedList;


public class Player implements GameObject {
    private static final int JUMP_HEIGHT = 150;
    private static final int MAX_HEALTH = 10;

    private int health;
    private boolean jumping;
    private boolean down;
    private Sound jumpSound;
    private Sound footSteps;
    private Sound ouch;

    private int jumpCounter;
    private SpriteGroup sprites;
    private int spriteCounter;

    private Rectangle[] healthPlayer;


    public Player() {
        jumpCounter = 0;
        down = true;
        health = MAX_HEALTH;
        jumping = false;
        spriteCounter = 0;
        jumpSound = new Sound("/resources/sounds/jump.wav");
        footSteps = new Sound("/resources/sounds/footstep.wav");
        ouch = new Sound("/resources/sounds/gettinghit.wav");
        initHealthBar();
        initPlayerPicture();
    }

    private void initPlayerPicture() {

        sprites = new SpriteGroup();
        sprites.add(new Sprite(100));
        sprites.add(new Sprite(100));
        sprites.add(new Sprite(100));
        sprites.add(new Sprite(100));


        //MARY
        sprites.get(0).addFrame(new Picture(40, 40, "resources/images/mary1.png"));
        sprites.get(0).addFrame(new Picture(40, 40, "resources/images/marySize.png"));

        //KEVIN
        sprites.get(1).addFrame(new Picture(40, 40, "resources/images/kevin.png"));
        sprites.get(1).addFrame(new Picture(40, 40, "resources/images/kevin2.png"));

        //RUI
        sprites.get(2).addFrame(new Picture(40, 40, "resources/images/rui.png"));
        sprites.get(2).addFrame(new Picture(40, 40, "resources/images/rui2.png"));
        //ZE
        sprites.get(3).addFrame(new Picture(40, 40, "resources/images/ze.png"));
        sprites.get(3).addFrame(new Picture(40, 40, "resources/images/ze1.png"));

    }


    private void initHealthBar() {
        healthPlayer = new Rectangle[health];
        Picture healthPicture = new Picture(30, 50, "resources/cerebro.png");
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
            if (health > 7) {
                healthPlayer[i].setColor((Color.GREEN));
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
        if (spriteCounter++ > 1000) {
            sprites.nextSprite();
            spriteCounter = 0;
        }
        sprites.update();
        if (jumping) {
            jumpAction();
            return;
        }

        if (sprites.getY() < sprites.lowerBound()) {
            sprites.translate(0, 1);
            sprites.update();
            return;
        }
        down = false;
    }


    private void jumpAction() {
        jumpCounter++;
        sprites.translate(0, -1);
        sprites.update();
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

    public void moveLeftInBounds (){
        footSteps.play(true);
        if (Game.isOutOfBoundsLeft(sprites.getX() - 1)) {
            return;
        }
        sprites.translate(-25, 0);
        sprites.update();
    }
    public void moveRightInBounds (){
        int x = sprites.getX();
        int total = x + sprites.getWidth();
        footSteps.play(true);
        if (Game.isOutOfBoundsRight(total)){
            return;
        }
        sprites.translate(25, 0);
        sprites.update();
    }

    public void moveLeft() {
        moveLeftInBounds();
        //movingRight = false;
        //movingLeft = true;
    }

    public void moveRight() {
        moveRightInBounds();
        //movingRight = true;
        //movingLeft = false;
    }

    public void releaseJump() {
        jumping = false;
        down = true;
        jumpCounter = 0;
        //sprites.update();
    }

    public int getHealth() {
        return health;
    }

    public void hit(int damage) {
        ouch.play(true);
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        updateHealthBar();
        sprites.update();
    }

    public void addHealth(int health) {
        this.health += health;
        if (this.health > 10) {
            this.health = 10;
        }
        updateHealthBar();
        sprites.update();
    }

    public int getWidth() {
        return sprites.getWidth();
    }

    public int getHeight() {
        return sprites.getHeight();
    }

    public int getX() {
        return sprites.getX();
    }

    public int getY() {
        return sprites.getY();
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


}
