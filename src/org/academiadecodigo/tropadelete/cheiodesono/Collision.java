package org.academiadecodigo.tropadelete.cheiodesono;

public abstract class Collision {

    private boolean isCrash;
    private Player player;
    private Obstacle obstacle;

    public Collision(){
        isCrash = false;

    }

    public void collision(){

    }


    public boolean isCrash() {
        return isCrash;
    }
}
