package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class Sprite {

    private LinkedList<Picture> pictures;
    private int delay;
    private int index;
    private int delayCounter = 0;

    public Sprite(int delay) {
        pictures = new LinkedList<>();
        this.delay = delay;
        index = 0;
        delayCounter =0;
    }

    public void addFrame(Picture frame) {
        pictures.add(frame);
    }

    public void update() {
        delayCounter++;
        if (!pictures.isEmpty() && delayCounter > delay) {
            delayCounter =0;
            int x = pictures.get(index).getX();
            int y = pictures.get(index).getY();

            int nextx = pictures.get(nextIndex()).getX();
            int nexty = pictures.get(nextIndex()).getY();

            pictures.get(nextIndex()).translate(x - nextx, y - nexty);

            pictures.get(index).delete();
            index = nextIndex();
            pictures.get(index).draw();
        }
    }

    public void translate(int x, int y) {
        if (!pictures.isEmpty()) {
            pictures.get(index).translate(x, y);
        }
    }

    private int nextIndex() {
        return (index + 1) % pictures.size();
    }

    public int getX() {
        return pictures.get(index).getX();
    }

    public int getY() {
        return pictures.get(index).getY();
    }

    public int getWidth() {
        return pictures.get(index).getWidth();
    }

    public int getHeight() {
        return pictures.get(index).getHeight();
    }
}
