package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {
    private static final int JUMP_HEIGHT = 40;

    private int health;

    private boolean jumping;
    private boolean down;
    private int animationCounter;

    private Picture picture;


    public Player() {
        animationCounter = 0;
        picture = new Picture(50, 400, "resources/mario.png");
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

    public void hit() {

    }
}
