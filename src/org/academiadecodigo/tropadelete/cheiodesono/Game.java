package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.cheiodesono.gameobjects.*;

import java.util.LinkedList;

public class Game implements GameObjectHandler, KeyboardHandler {

    private Rectangle rectangle;

    private Player player;
    private static final int PADDING = 0;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MAX_MOVE_RIGHT = 200;

    private Sound backgroundMusic1;
    private Sound gameOverBackgroundMusic;
    private Sound gameOver;

    private Text framesLeft;

    private static final long LEVEL_GOAL_0 = 5000L;
    private Sound winJingle;
    private Sound winMusic;
    private boolean gameStarted;


    private LinkedList<GameObject> gameObjects;
    private LinkedList<GameObject> toRemove = new LinkedList<>();

    private int newObstacleTrigger; //FrameCounter % newObstacleTrigger == 0 means show another obstacle

    private long frameCounter;
    private long currentLevelGoal;
    private Picture movingBackground;

    private void init() {
        gameStarted = false;
        winJingle = new Sound("/resources/sounds/winning.wav");
        winMusic = new Sound("/resources/sounds/success.wav");
        gameOver = new Sound("/resources/sounds/gameover.wav");
        gameOverBackgroundMusic = new Sound("/resources/sounds/gameOverBackgroundMusic.wav");
        backgroundMusic1 = new Sound("/resources/sounds/background.wav");
        backgroundMusic1.play(true);
        backgroundMusic1.setLoop(10000);
        frameCounter = 0;
        newObstacleTrigger = 300;
        rectangle = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);
        Keyboard keyboard = new Keyboard(this);
        KeyboardEvent enter = new KeyboardEvent();
        enter.setKey(KeyboardEvent.KEY_I);
        enter.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(enter);




        Picture start = new Picture(PADDING, PADDING, "resources/images/capa1.png");
        start.draw();
        while (!gameStarted){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        start.delete();


        //rectangle = new Rectangle(PADDING,PADDING, WIDTH, HEIGHT);

        Picture backgroundImage = new Picture(PADDING, PADDING, "resources/background.png");

        Picture backgroundCity = new Picture(PADDING, PADDING, "resources/pavement.png");
        new Picture(PADDING, PADDING, "resources/tracejado1.png");
        new Picture(PADDING, PADDING, "resources/tracejado2.png");

        backgroundImage.draw();
        backgroundCity.draw();

        movingBackground = new Picture(100, 0, "resources/images/clouds.jpg");
        movingBackground.draw();

        player = new Player();
        new KeyboardListener(player);
        gameObjects = new LinkedList<>();
        gameObjects.add(player);
        //gameObjects.add(new Obstacle(player, this));


    }


    void start() {
        init();
        currentLevelGoal = LEVEL_GOAL_0;
        new Text(710,20,"Time left").draw();

        framesLeft = new Text(730,40,"");
        while (!gameOver()) {
        movingBackground.translate(-1,0);
        movingBackground.draw();
            framesLeft.setText(String.valueOf(currentLevelGoal-frameCounter));
            framesLeft.draw();
            //System.out.println("Frame number:" + frameCounter);
            //bk.drawFrom((int) frameCounter);
            frameCounter++;
            player.update();
            manageNewObjects();

            for (GameObject gameObject : gameObjects) {
                gameObject.update();
                if (isOutOfBoundsLeft(gameObject.getX())) {
                    toRemove.add(gameObject);
                }
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

    private void loseGame() {
        backgroundMusic1.stop();
        gameOverBackgroundMusic.play(true);
        gameOverBackgroundMusic.setLoop(1000);
        Picture endGame = new Picture(PADDING, PADDING, "resources/images/1.png");
        endGame.draw();
    }

    private void winGame() {
        backgroundMusic1.stop();
        Picture endGame = new Picture(PADDING, PADDING, "resources/images/2.png");
        endGame.draw();
        winJingle.play(true);
        winMusic.play(true);
        winMusic.setLoop(1000);
    }

    private boolean gameOver() {
        if (player.getHealth() <= 0) {
            gameOver.play(true);
            loseGame();
            return true;
        }

        if (frameCounter >= currentLevelGoal && player.getHealth() > 0) {
            winGame();
            return true;
        }
        return false;

    }

    private void manageNewObjects() {
        if (frameCounter % newObstacleTrigger == 0) {
            //System.out.println("Showing new obstacle");
            newObstacleTrigger = 300 + (int) (Math.random() * 700);

            GameObject tempObject;
            if (Math.random() <= .7d) {
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

    private boolean validSpawnLocation(GameObject spawn) {
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
        return x > (PADDING + WIDTH) - MAX_MOVE_RIGHT;
    }

    public static boolean isOutOfBoundsLeft(int x) {
        return x < 50;
    }

    @Override
    public void remove(GameObject gameObject) {
        toRemove.add(gameObject);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_I){
            gameStarted = true;
        }


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
