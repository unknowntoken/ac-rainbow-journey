package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

import java.util.ArrayList;


public class SpriteGroup {

    private ArrayList<Sprite> sprites;
    private int index;

    public SpriteGroup() {
        sprites = new ArrayList<>();
        index = 0;
    }

    public Sprite get(int index) {
        return sprites.get(index);
    }

    public void add(Sprite sprite) {
        sprites.add(sprite);
    }

    public void nextSprite() {
        if (!sprites.isEmpty()) {
            index = (index + 1) % sprites.size();
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getWidth (){
        return currentSprite().getWidth();
    }
    public int getHeight (){
        return currentSprite().getHeight();
    }
    public Sprite currentSprite() {
        return sprites.get(index);
    }

    public void update() {
        currentSprite().update();
    }

    public int getX() {
        return currentSprite().getX();
    }

    public int getY() {
        return currentSprite().getY();
    }

    public int lowerBound (){
        return currentSprite().lowerBound();
    }
    public void translate(int x, int y) {
        currentSprite().translate(x, y);
    }
}
