package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

public class TimedSprite extends Sprite {
    private int xDelay;
    private int xStep;
    private int counter;

    public TimedSprite(int animationDelay, int xDelay, int xStep) {
        super(animationDelay);
        this.xDelay = xDelay;
        this.xStep = xStep;
        counter = 0;
    }

    @Override
    public void update() {
        super.update();
        counter++;
        if (counter >= xDelay){
            counter =0;
            super.translate(xStep,0);
        }
    }
}
