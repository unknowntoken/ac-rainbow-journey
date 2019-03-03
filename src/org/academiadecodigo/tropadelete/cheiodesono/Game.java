package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.cheiodesono.gameobjects.*;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class Game implements GameObjectHandler {

    private Rectangle rectangle;
    private Picture backgroundImage;
    private Picture backgroundCity;
    private Picture translate;
    private Picture translate2;

    private Player player;
    private static final int PADDING = 10;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Sound backgroundMusic1;
    private Sound gameOverBackgroundMusic;
    private Sound gameOver;

    private static final long LEVEL_GOAL_0 = 30000L;


    private LinkedList<GameObject> gameObjects;
    LinkedList<GameObject> toRemove = new LinkedList<>();

    private int obstacleIndex;  //Index of the obstacle next to be spawned
    private int newObstacleTrigger; //FrameCounter % newObstacleTrigger == 0 means show another obstacle

    private long frameCounter;
    private long currentLevelGoal;
    private ScrollingImage bk;

    private void init() {
        gameOver = new Sound("/resources/sounds/gameover.wav");
        gameOverBackgroundMusic = new Sound("/resources/sounds/gameOverBackgroundMusic.wav");
        backgroundMusic1 = new Sound("/resources/sounds/background.wav");
        backgroundMusic1.play(true);
        backgroundMusic1.setLoop(10000);
        obstacleIndex = 0;
        frameCounter = 0;
        newObstacleTrigger = 300;
        rectangle = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);


        bk = new ScrollingImage(PADDING,PADDING,"resources/testlongbackground.png");

        Picture start = new Picture(PADDING, PADDING, "resources/images/capa.png");
        start.draw();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        start.delete();


        //rectangle = new Rectangle(PADDING,PADDING, WIDTH, HEIGHT);

        backgroundImage = new Picture(PADDING, PADDING, "resources/background.png");
        backgroundCity = new Picture(PADDING, PADDING, "resources/pavement.png");
        translate = new Picture(PADDING, PADDING, "resources/tracejado1.png");
        translate2 = new Picture(PADDING, PADDING, "resources/tracejado2.png");

        backgroundImage.draw();
        backgroundCity.draw();


        player = new Player();
        new KeyboardListener(player);
        gameObjects = new LinkedList<>();
        gameObjects.add(player);
        //gameObjects.add(new Obstacle(player, this));


    }


    public void start() {
        init();
        currentLevelGoal = LEVEL_GOAL_0;
        while (true) {
            //System.out.println("Frame number:" + frameCounter);
            //bk.drawFrom((int) frameCounter);
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

    public void loseGame() {
        backgroundMusic1.stop();
        gameOverBackgroundMusic.play(true);
        gameOverBackgroundMusic.setLoop(1000);
        Picture endGame = new Picture(PADDING, PADDING, "resources/images/1.png");
        endGame.draw();
    }

    public void winGame() {
        //Picture endGame = new Picture(PADDING,PADDING,"resources/images/2.png");
        //endGame.draw();
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

            GameObject tempObject;
            if (Math.random() <= .5) {
                tempObject = new Obstacle(player, this);

            } else {
                tempObject = new PowerUp(player, this);
            }
            if (validSpawnLocation(tempObject)) {
                gameObjects.add(tempObject);
                tempObject.show();
            }

        }
    }

    public boolean validSpawnLocation(GameObject spawn) {
        //Check if spawn collides with any gameobject then return not valid spawn location.

        if (isOutOfBounds(spawn)) {
            return false;
        }

        for (GameObject gm : gameObjects) {
            if (Collision.collide(gm.getX(), gm.getY(), gm.getWidth(),
                    gm.getHeight(), spawn.getX(), spawn.getY(), spawn.getWidth(), spawn.getHeight())) {
                return false;
            }
        }
        return true;
    }

    private boolean isOutOfBounds(GameObject object) {
        return !Collision.collide(object.getX(), object.getY(), object.getWidth(),
                object.getHeight(), rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
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
}
