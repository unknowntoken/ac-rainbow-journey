package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.cheiodesono.gameobjects.*;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class Game implements GameObjectHandler {

    private Rectangle rectangle;
    private Picture backgroundImage;
    private Player player;
    private static final int PADDING = 10;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MAX_OBSTACLES = 4;
    private Sound backgroundMusic1;
    private Sound gameOverBackgroundMusic;
    private Sound gameOver;

    private static final long LEVEL_GOAL_0 = 300000L;


    private LinkedList<GameObject> gameObjects;
    LinkedList<GameObject> toRemove = new LinkedList<>();

    private int obstacleIndex;  //Index of the obstacle next to be spawned
    private int newObstacleTrigger; //FrameCounter % newObstacleTrigger == 0 means show another obstacle

    private long frameCounter;
    private long currentLevelGoal;

    private void init() {
        gameOver = new Sound("/resources/sounds/gameover.wav");
        gameOverBackgroundMusic = new Sound("/resources/sounds/gameOverBackgroundMusic.wav");
        backgroundMusic1 = new Sound("/resources/sounds/background.wav");
        backgroundMusic1.play(true);
        backgroundMusic1.setLoop(10000);
        obstacleIndex = 0;
        frameCounter = 0;
        newObstacleTrigger = 300;


        //rectangle = new Rectangle(PADDING,PADDING, WIDTH, HEIGHT);

        backgroundImage = new Picture(PADDING, PADDING, "resources/Sky-Wallpapers.jpg");
        backgroundImage.draw();


        player = new Player();
        new KeyboardListener(player);
        gameObjects = new LinkedList<>();
        gameObjects.add(new Obstacle(player, this));


    }


    public void start() {
        init();
        currentLevelGoal = LEVEL_GOAL_0;

        while (true) {
            //System.out.println("Frame number:" + frameCounter);
            if (gameOver()) {
                break;
            }
            frameCounter++;
            player.update();
            manageNewObjects();

            try {
                for (GameObject gameObject : gameObjects) {
                    gameObject.update();
                    if (isOutOfBoundsLeft(gameObject.getX())) {
                        toRemove.add(gameObject);
                    }
                }
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
            }

            for (GameObject remove : toRemove) {
                System.out.println("removing");
                remove.hide();
                gameObjects.remove(remove);
            }
            toRemove.clear();

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void loseGame(){

        backgroundMusic1.stop();
        gameOverBackgroundMusic.play(true);
        gameOverBackgroundMusic.setLoop(1000);
    }

    public void winGame (){

    }

    public boolean gameOver() {
        if (player.getHealth() <= 0) {
            gameOver.play(true);
            loseGame();
            return true;
        }
        winGame();

        return frameCounter >= currentLevelGoal;

    }

    public void manageNewObjects() {
        if (frameCounter % newObstacleTrigger == 0) {
            //System.out.println("Showing new obstacle");
            newObstacleTrigger = 300 + (int) (Math.random() * 700);

            if (Math.random() < .5) {
                gameObjects.add(new Obstacle(player, this));
                return;
            }
            gameObjects.add(new PowerUp(player, this));
        }

    }

    public static boolean isOutOfBoundsRight(int x) {
        return x > PADDING + WIDTH;
    }
    public static boolean isOutOfBoundsLeft(int x) {
        return x < PADDING;

    }

    @Override
    public void remove(GameObject gameObject) {
        toRemove.add(gameObject);
    }
    // public boolean checkColision(){


    //}


}
