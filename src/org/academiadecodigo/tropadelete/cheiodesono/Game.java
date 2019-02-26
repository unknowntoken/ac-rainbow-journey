package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {

    private Rectangle rectangle;
    private Player player;
    private static final int PADDING = 10;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MAX_OBSTACLES = 2;
    private Obstacle[] obstacles;


    private void init() {
        obstacles = new Obstacle [MAX_OBSTACLES];

        rectangle = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);
        rectangle.draw();
        player = new Player();
        KeyboardListener keyboardHandler = new KeyboardListener(player);
        for (int i = 0; i< obstacles.length;i++){
            obstacles[i] = new Obstacle (player,ObstacleImage.getRandomType());
        }
        
    }

    public void start() {
        init();
        while (true) {
            player.update();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



    // public boolean checkColision(){


    //}


}
