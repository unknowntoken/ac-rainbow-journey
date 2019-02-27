package org.academiadecodigo.tropadelete.cheiodesono;

public abstract class Collision {

    private boolean isCrash;
    private Player player;
    private Obstacle obstacle;
    private int rowPlayer;
    private int colPlayer;
    private int widthPlayer;
    private int heightPlayer;
    private int rowObstacle;
    private int colObstacle;
    private int widthObstacle;
    private int heightObstacle;


    public Collision() {
        isCrash = false;

    }

    public boolean isCollision(Position player, Position obstacle) {
        if (!isCrash) {
            getRowPlayer(player);
            getColPlayer(player);
            getRowObstacle(obstacle);
            getColObstacle(obstacle);
            if (compareCol() && compareRow()) {
                isCrash = true;
            }
        }

        return isCrash;
    }

    //*********************
    //*******PLAYER********
    //*********************
    private void getRowPlayer(Position player) {
        this.rowPlayer = player.getX();
    }

    private void getColPlayer(Position player) {
        this.colPlayer = player.getY();
    }

    private void getWidthPlayer(Player player) {
        //this.widthPlayer = player.
    }

    private void getHeightPlayer(Player player) {
        //this.heightPlayer = player.
    }
    //*********************
    //******OBSTACLE*******
    //*********************
    private void getRowObstacle(Position obstacle) {
        this.rowObstacle = obstacle.getX();
    }

    private void getColObstacle(Position obstacle) {
        this.colObstacle = obstacle.getY();
    }

    private void getWidthObstacle(Obstacle obstacle) {
        //this.widthObstacle =obstacle.
    }

    private void getHeightObstacle(Obstacle obstacle){
        //this.heightObstacle = obstacle.
    }


    private boolean compareRow() {
        return rowPlayer == rowObstacle;
    }

    private boolean compareCol() {
        return colPlayer == colObstacle;
    }

}
