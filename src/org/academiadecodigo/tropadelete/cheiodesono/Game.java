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
    private static final int MAX_OBSTACLES = 5;
    private Obstacle[] obstacles;



    private void init() {
        obstacles = new Obstacle [MAX_OBSTACLES];

        //rectangle = new Rectangle(PADDING,PADDING, WIDTH, HEIGHT);

        picture = new Picture(PADDING,PADDING,"resources/Sky-Wallpapers.jpg");
        picture.draw();


        player = new Player();
        KeyboardListener keyboardHandler = new KeyboardListener(player);
        for (int i = 0; i< obstacles.length;i++){
           obstacles[i] = new Obstacle (player,ObstacleImage.getRandomType());
       }
        
    }

    public void start() {
        init();
        int obstacleCounter = 0;
        int indexCounter = 0;

        while (true) {
            obstacleCounter++;
            player.update();

            if(obstacleCounter > 0){
                obstacles[indexCounter].show();
                indexCounter++;
            }

            if(obstacleCounter >700){
                obstacles[indexCounter].show();
                indexCounter++;
                obstacleCounter =0;
            }
            if(indexCounter == obstacles.length){
                indexCounter =0;
            }
            for( Obstacle obstacle : obstacles){

                obstacle.update();
                if(checkOutOfBounds(obstacle.getX(),obstacle.getY())){
                    obstacle.hide();
                }
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public boolean checkOutOfBounds(int x, int y){

        if( x < PADDING){

            return true;
        }

        return false;

    }
    // public boolean checkColision(){


    //}


}
