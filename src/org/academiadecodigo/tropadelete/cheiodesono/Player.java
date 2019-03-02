package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Player {
    private static final int JUMP_HEIGHT = 200;

    private int health;
    private boolean jumping;
    private boolean down;


    private int jumpCounter;
    private Picture[] playerPicture;
    private int playerPictureCounter;

    private Rectangle[] healthPlayer;

    private int playerX;
    private int playerY;
    private int lowestY;


    public Player() {
        jumpCounter = 0;
        playerPictureCounter = 0;
        playerX = 0;
        playerY = 0;
        down = true;
        health = 10;
        playerPicture = new Picture[3];

        initHealthBar();
        initPlayerPicture();


        this.jumping = false;


    }

    private void initPlayerPicture() {
        playerPicture[0] = new Picture(40, 40, "resources/images/mary1.png");
        playerPicture[1] = new Picture(40, 40, "resources/images/mary1s.png");
        playerPicture[2] = new Picture(40, 40, "resources/images/mary2s 60.245.png");
        lowestY = 600 - (playerPicture[0].getY() + playerPicture[0].getHeight());
        playerPicture[0].draw();
        playerX = playerPicture[0].getX();
        playerY = playerPicture[0].getY();
    }

    private void updatePlayerPicture() {
        playerPictureCounter++;
        if (playerPictureCounter % 8 == 0) {

            int index = (playerPictureCounter - 1) % playerPicture.length;
            System.out.println(index);
            playerX = playerPicture[index].getX();
            playerY = playerPicture[index].getY();
            playerPicture[index].delete();
            index = playerPictureCounter % playerPicture.length;

            int currentx = playerPicture[index].getX();
            int currenty = playerPicture[index].getY();
            playerPicture[index].translate(playerX - currentx, playerY - currenty);
            playerPicture[index].draw();
            System.out.println(index);
        }
    }

    private void initHealthBar() {
        healthPlayer = new Rectangle[health];

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
            if (health <= 5) {
                healthPlayer[i].setColor(Color.RED);
            }
            healthPlayer[i].draw();
            healthPlayer[i].fill();
        }

    }

    public void update() {
        updatePlayerPicture();
        if (jumping) {
            jumpAction();
            return;
        }

        if (playerPicture().getY() < lowestY) {
            playerPicture().translate(0, 1);
            updatePlayerPicture();
            return;
        }
        down = false;
    }

    private Picture playerPicture() {
        int index = playerPictureCounter % playerPicture.length;
        return playerPicture[index];
    }

    private void jumpAction() {
        //System.out.println("Y:" + playerPicture.getY());
        //System.out.println("counter" + jumpCounter);

        jumpCounter++;
        playerPicture().translate(0, -1);
        updatePlayerPicture();
        if (jumpCounter >= JUMP_HEIGHT) {
            jumpCounter = 0;
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
        if (Game.isOutOfBoundsLeft(playerPicture().getX() - 10)) {
            return;
        }
        playerPicture().translate(-10, 0);
        updatePlayerPicture();
    }

    public void moveRight() {
        if (Game.isOutOfBoundsRight(playerPicture().getX() + 10)) {
            return;
        }
        playerPicture().translate(10, 0);
        updatePlayerPicture();
    }

    public void releaseJump() {
        jumping = false;
        down = true;
        jumpCounter = 0;
        updatePlayerPicture();
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
        updatePlayerPicture();
        System.out.println("Health: " + health);
    }

    public int getWidth() {
        return playerPicture().getWidth();
    }

    public int getHeight() {
        return playerPicture().getHeight();
    }

    public int getX() {
        return playerPicture().getX();
    }

    public int getY() {
        return playerPicture().getY();
    }


}
