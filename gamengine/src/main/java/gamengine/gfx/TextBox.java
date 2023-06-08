package gamengine.gfx;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

import gamengine.inp.KeyHandler;
import gamengine.inp.MouseHandler;
import gamengine.Game;

/**
 * A class that is used to check for clicks on an area
 */
public class TextBox {

    private Rect rect;
    private String text = "";
    private int textScale;
    private int textColor;
    private MouseHandler mouse;
    private KeyHandler keyBoard;
    private boolean active = false;

    /**
     * Used for instantiating a {@link Button}
     *
     * @param xPos x-coord for the orign of the button
     * @param yPos y-coord for the orign of the button
     * @param width the width of the button
     * @param height the height of the button
     * @param bgColor color to render the button (Use {@link Colors#get(int color)})
     * @param messageScale scale at which to display the message
     * @param messageColor color to render the message (Use {@link Colors#get(int, int, int, int)})
     * @param mouse the {@link MouseHandler} from the {@link Game}
     */
    public TextBox(int xPos, int yPos, int width, int height, int bgColor, int textScale, int textColor, MouseHandler mouse, KeyHandler keyBoard) {
        this.rect = new Rect(xPos, yPos, width, height, bgColor, false);
        this.textScale = textScale;
        this.textColor = textColor;
        this.mouse = mouse;
        this.keyBoard = keyBoard;
    }

    public void update() {
        if (this.mouse.getButton(MouseEvent.BUTTON1).isPressed()) {
            if (this.rect.collides(this.mouse.getX(), this.mouse.getY())) {
                this.active = true;
            } else {
                this.active = false;
            }
        }

        for (int i = 0; i < 525; i++) {
            if (this.keyBoard.getKey(i).isPressed()) {
                this.text += KeyEvent.getModifiersExText(i);
            }
        }
    }

    /**
     * Used to check if the {@link Button} is pressed
     *
     * @param includesHeld if holding the button should count as repeated clicks
     * 
     * @return if the button is clicked
     */
    public boolean isActive(boolean includesHeld) {
        return this.active;
    }

    /**
     * Renders the button
     *
     * @param screen screen on which to render the button
     */
    public void render(Screen screen) {
        int maxLength = 0;
        for (String line: this.text.split("\n")) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }
        
        int textLength = 8 * this.textScale * maxLength;
        int textHeight = 8 * this.textScale * (int)this.text.split("\n").length;
        int textX = this.rect.getX() + this.rect.getWidth() / 2 - textLength / 2;
        int textY = this.rect.getY() + this.rect.getHeight() / 2 - textHeight / 2;

        this.rect.render(screen);
        if (!this.text.equals("")) {
            Font.render(this.text, screen, textX, textY, this.textColor, this.textScale, false);
        }
    }
}
