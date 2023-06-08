package gamengine.inp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamengine.Game;

/**
 * A class for checking if {@link Key}s are pressed or released
 */
public class KeyHandler implements KeyListener {

    /**
     * Used for instantiating a {@link KeyHandler}
     *
     * @param game the game from which to listen to key-strokes
     */
    public KeyHandler(Game game) {
        game.addKeyListener(this);
        for (int i = 0; i < 525; i++) {
            this.keys[i] = new Key();
        }
    }

    /**
     * A class for storing keyboard key data
     */
    public class Key {

        /**
         * Used to instantiate a {@link Key}
         */
        public Key() {

        }

        private int numTimesPressed = 0;
        private boolean pressed = false;

        /**
         * Used to find if the key is currently pressed
         * 
         * @return a boolean for if the key is pressed
         */
        public boolean isPressed() {
            return this.pressed;
        }

        private void toggle(boolean pressed) {
            this.pressed = pressed;
            if (pressed) {
                this.numTimesPressed++;
            }
        }

        /**
         * Used to find how many times the key has been pressed throughout the entire game
         * 
         * @return the number of times the key has been pressed
         */
        public int getNumTimesPressed() {
            return this.numTimesPressed;
        }
    }

    private Key[] keys = new Key[525];

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        this.toggleKey(e.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent e) {
        this.toggleKey(e.getKeyCode(), false);
    }

    private void toggleKey(int keyCode, boolean pressed) {
        this.keys[keyCode].toggle(pressed);
    }

    /**
     * Use {@link KeyEvent#VK_X} as a {@code keyCode}
     * 
     * @param keyCode the code from which to recieve a key
     * @return the {@link Key} values for {@code keyCode}
     */
    public Key getKey(int keyCode) {
        return this.keys[keyCode];
    }
}
