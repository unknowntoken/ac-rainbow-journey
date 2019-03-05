package org.academiadecodigo.tropadelete.cheiodesono;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.tropadelete.cheiodesono.gameobjects.Player;

import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent.*;

public class KeyboardListener implements KeyboardHandler {
    private int[] keyEventsPressed = {KEY_W, KEY_A, KEY_S, KEY_D, KEY_K,KEY_H,KEY_L};

    private Player player;

    public KeyboardListener(Player player) {
        this.player = player;
        init();

    }

    private void init() {
        Keyboard keyboard = new Keyboard(this);
        for (int i = 0; i < keyEventsPressed.length; i++) {
            KeyboardEvent tempKey = new KeyboardEvent();
            tempKey.setKey(keyEventsPressed[i]);
            tempKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(tempKey);

            tempKey = new KeyboardEvent();
            tempKey.setKey(keyEventsPressed[i]);
            tempKey.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
            keyboard.addEventListener(tempKey);
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_K:
                player.jump();
                break;
            case KEY_H:
                player.moveLeft();
                break;
            case KEY_L:
                player.moveRight();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KEY_K:
                player.releaseJump();
                break;
            case KEY_A:
                //player.releaseMove();
                break;
            case KEY_D:
                //player.releaseMove();
                break;
        }

    }
}
