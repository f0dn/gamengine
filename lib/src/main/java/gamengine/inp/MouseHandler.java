package gamengine.inp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamengine.Game;

/**
 * A class for checking for both mouse motion and mouse click events
 */
public class MouseHandler {

    private int x = 0;
    private int y = 0;
    private int scale;

    private MouseButton[] buttons = new MouseButton[] {new MouseButton(), new MouseButton(), new MouseButton()}; 

    /**
     * A class for storing mouse button data
     */
    public class MouseButton {

        /**
         * Used to instantiate a {@link MouseButton}
         */
        public MouseButton() {

        }

        private int numTimesClicked = 0;
        private boolean pressed = false;

        /**
         * Used to check if the mouse button is currently pressed
         * 
         * @return a boolean that indicates if the mouse button is pressed
         */
        public boolean isPressed() {
            return this.pressed;
        }

        private void toggle(boolean pressed) {
            this.pressed = pressed;
            if (pressed) {
                this.numTimesClicked++;
            }
        }

        /**
         * Used to check how many times the mouse button has been pressed throughout the game
         * 
         * @return the number of times the mouse button has been pressed
         */
        public int getNumTimesPressed() {
            return this.numTimesClicked;
        }
    }

    private void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Used to find the x-position of the mouse on the screen
     * 
     * @return the x coordinate of the mouse
     */
    public int getX() {
        return this.x;
    }

    /**
     * Used to find the y-position of the mouse on the screen
     * 
     * @return the y coordinate of the mouse
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Used to get an integer array of the mouse position on the screen
     * 
     * @return the x and y coordinates of the mouse in an array (x, y)
     */
    public int[] getPos() {
        return new int[] {this.x, this.y};
    }

    /**
     * Use {@link MouseEvent#BUTTON1}, {@link MouseEvent#BUTTON2}, or {@link MouseEvent#BUTTON3} as a {@code mouseButton}
     * 
     * @param mouseButton the button number
     * @return the {@link MouseButton} values for {@code mouseButton}
     */
    public MouseButton getButton(int mouseButton) {
        return this.buttons[mouseButton - 1];
    }

    /**
     * Used for instantiating a {@link MouseHandler}
     *
     * @param game the game from which to listen to mouse events
     */
    public MouseHandler(Game game) {
        new MouseInput(game);
        new MouseMotion(game);
        this.scale = game.SCALE;
    }

    private class MouseInput implements MouseListener {

        private MouseInput(Game game) {
            game.addMouseListener(this);
        }

        public void mouseClicked(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {
            buttons[e.getButton() - 1].toggle(true);
        }

        public void mouseReleased(MouseEvent e) {
            buttons[e.getButton() - 1].toggle(false);
        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }

    private class MouseMotion implements MouseMotionListener {

        private MouseMotion(Game game) {
            game.addMouseMotionListener(this);
        }

        public void mouseDragged(MouseEvent e) {
            move((int)e.getX() / scale, (int)e.getY() / scale);
        }

        public void mouseMoved(MouseEvent e) {
            move((int)e.getX() / scale, (int)e.getY() / scale);
        }
    }
}
