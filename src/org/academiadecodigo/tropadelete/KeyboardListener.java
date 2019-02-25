package org.academiadecodigo.tropadelete;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardListener implements KeyboardHandler {


    private Player player;

    public KeyboardListener(Player player){
        this.player = player;

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(space);

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        System.out.println("a");

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_SPACE:
                System.out.println("here");
                player.jump();
                break;

        }


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
