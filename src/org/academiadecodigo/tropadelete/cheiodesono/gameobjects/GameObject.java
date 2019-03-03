package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

public interface GameObject {

    void update ();
    void show();
    void hide ();
    void reset ();
    int getX ();
    int getY ();
    int getWidth ();
    int getHeight();
    void hit (int damage);
}
