package org.academiadecodigo.tropadelete.cheiodesono;

public abstract class Collision {

    private boolean isCrash;
    private Player player;
    private Obstacle obstacle;
    private int rowPlayer;
    private int colPlayer;
    private int rowObstacle;
    private int colObstacle;


    public Collision() {
        isCrash = false;

    }

    public void collision(Position player, Position obstacle) {
        if (!isCrash) {
            getRowPlayer(player);
            getColPlayer(player);
            getRowObstacle(obstacle);
            getColObstacle(obstacle);
            if (compareCol() && compareRow()) {
                isCrash = true;
            }
        }


    }

    private void getRowPlayer(Position player) {
        this.rowPlayer = player.getX();
    }

    private void getColPlayer(Position player) {
        this.colPlayer = player.getY();
    }

    private void getRowObstacle(Position obstacle) {
        this.rowObstacle = obstacle.getX();
    }

    private void getColObstacle(Position obstacle) {
        this.colObstacle = obstacle.getY();
    }

    private boolean compareRow() {
        return rowPlayer == rowObstacle;
    }

    private boolean compareCol() {
        return colPlayer == colObstacle;
    }

    public boolean isCrash() {
        return isCrash;
    }
}
