package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {

    private Rectangle rectangle;
    private Player player;
    private static final int PADDING = 10;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private void init() {
        rectangle = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);
        rectangle.draw();
        player = new Player();
        KeyboardListener keyboardHandler = new KeyboardListener(player);
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
