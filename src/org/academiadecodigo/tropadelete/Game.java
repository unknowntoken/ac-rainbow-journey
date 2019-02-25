package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {

    private Rectangle rectangle;
    private Player player;
    private KeyboardListener keyboardHandler;
    private static final int PADDING = 10;




    public void start(){

        rectangle = new Rectangle(PADDING,PADDING, 1700,900);
        rectangle.draw();
        player = new Player();
        player.jump();

        while (true){
            player.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

   // public boolean checkColision(){


    //}




}
