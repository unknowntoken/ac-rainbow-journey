package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    private Rectangle rectangle;
    private Picture backgroundImage;
    private Player player;
    private static final int PADDING = 10;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MAX_OBSTACLES = 4;
    private Obstacle[] obstacles;

    private int frameCounter;
    private int obstacleIndex;  //Index of the obstacle next to be spawned
    private int newObstacleTrigger; //FrameCounter % newObstacleTrigger == 0 means show another obstacle


    private void init() {
        obstacleIndex = 0;
        frameCounter = 0;
        newObstacleTrigger = 300;

        obstacles = new Obstacle[MAX_OBSTACLES];

        //rectangle = new Rectangle(PADDING,PADDING, WIDTH, HEIGHT);

        backgroundImage = new Picture(PADDING, PADDING, "resources/Sky-Wallpapers.jpg");
        backgroundImage.draw();


        player = new Player();
        new KeyboardListener(player);
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i] = new Obstacle(player);
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
                Thread.sleep(3);
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


        if (frameCounter % newObstacleTrigger == 0) {
            //System.out.println("Showing new obstacle");
            newObstacleTrigger= 300 + (int)(Math.random() * 700);
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
