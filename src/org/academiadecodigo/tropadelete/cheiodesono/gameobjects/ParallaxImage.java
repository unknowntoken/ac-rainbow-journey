package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class ParallaxImage {
    private Picture picture;
    private int delay;
    private int counter;
    private double xStep;

    public ParallaxImage(Picture picture, int delay, double xStep) {
        this.picture = picture;
        this.delay = delay;
        counter = 0;
        this.xStep = xStep;
    }

    public void update() {
        if (counter >= delay) {
            picture.translate(xStep,0);
            picture.draw();
            counter=0;
        }
        counter++;
    }

    public void show (){
        picture.draw();
    }
    public void hide (){
        picture.delete();
    }
}
