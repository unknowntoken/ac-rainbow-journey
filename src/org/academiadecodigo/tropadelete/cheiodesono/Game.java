package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    private Rectangle rectangle;
    private Picture picture;
    private Player player;
    private static final int PADDING = 10;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MAX_OBSTACLES = 4;
    private Obstacle[] obstacles;

    private int frameCounter;
    private int obstacleIndex;  //Index of the obstacle next to be spawned


    private void init() {
        obstacleIndex = 0;
        frameCounter = 0;

        obstacles = new Obstacle[MAX_OBSTACLES];

        //rectangle = new Rectangle(PADDING,PADDING, WIDTH, HEIGHT);

        picture = new Picture(PADDING, PADDING, "resources/Sky-Wallpapers.jpg");
        picture.draw();


        player = new Player();
        KeyboardListener keyboardHandler = new KeyboardListener(player);
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i] = new Obstacle(player, ObstacleImage.getRandomType());
        }

    }

    public void start() {
        init();


        while (true) {
            //System.out.println("Frame number:" + frameCounter);
            frameCounter++;
            player.update();
            manageObstacles();


            for (Obstacle obstacle : obstacles) {

                obstacle.update();
                if (checkOutOfBounds(obstacle.getX(), obstacle.getY())) {
                    obstacle.hide();
                    obstacle.reset();
                }
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void manageObstacles() {
        if (frameCounter == 0) {
            obstacles[obstacleIndex].show();
            obstacleIndex++;
        }


        if (frameCounter % 300 == 0) {
            //System.out.println("Showing new obstacle");
            obstacles[obstacleIndex%obstacles.length].show();
            obstacleIndex++;
        }

    }

    public boolean checkOutOfBounds(int x, int y) {

        if (x < PADDING) {
            return true;
        }

        return false;

    }
    // public boolean checkColision(){


    //}


}
