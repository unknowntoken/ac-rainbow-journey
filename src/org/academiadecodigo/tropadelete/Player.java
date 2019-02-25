package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Movable;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {

    //private int health;
    private boolean jumping;
    private boolean down;
    private final int maxHeight = 40;
    private int countPix;
    private Picture picture;


    public Player() {
        countPix = 0;
        picture = new Picture(50, 50, "resources/hero_chara_mario_pc.png");
        picture.draw();
    }

    public void update() {
        if (jumping) {
            if (down) {
                countPix--;
                picture.translate(1,0);
                if(countPix == 0){
                    jumping = false;
                    down = false;
                }
            } else {
                countPix++;
                picture.translate(-1,0);
                if(countPix >= maxHeight){
                    down = true;

                }
            }
        }
    }

    public void jump() {


        jumping = true;

    }

    public void hit() {


    }

    public boolean isHasJump() {
        return jumping;
    }
}
