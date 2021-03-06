package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class Sprite implements GameObject{

    private LinkedList<Picture> pictures;
    private int delay;
    private int index;
    private int delayCounter;

    public Sprite(int delay) {
        pictures = new LinkedList<>();
        this.delay = delay;
        index = 0;
        delayCounter =0;
    }

    public void addFrame(Picture frame) {
        pictures.add(frame);
    }

    @Override
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

    public void translate(double x, double y) {
        if (!pictures.isEmpty()) {
            pictures.get(index).translate(x, y);
        }
    }
    public void grow (double x, double y){
        for (Picture picture : pictures) {
            picture.grow(x,y);
        }
    }
    public void show (){
        getCurrentPicture().draw();
    }

    public void hide (){
        getCurrentPicture().delete();
    }

    @Override
    public void reset() {

    }

    public int lowerBound (){
        return 600 - (pictures.get(index).getY() - pictures.get(index).getHeight());
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

    @Override
    public void hit(int damage) {

    }

    public Picture getCurrentPicture (){
        return pictures.get(index);
    }
}
