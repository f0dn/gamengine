package gamengine.gfx;

import java.awt.event.MouseEvent;

import gamengine.inp.MouseHandler;
import gamengine.Game;

/**
 * A class that is used to check for clicks on an area
 */
public class Button {

    private Rect rect;
    private String message;
    private int messageScale;
    private int messageColor;
    private MouseHandler mouse;
    private int messageX;
    private int messageY;
    private int prevClicks = 0;

    /**
     * Used for instantiating a {@link Button}
     *
     * @param xPos x-coord for the orign of the button
     * @param yPos y-coord for the orign of the button
     * @param width the width of the button
     * @param height the height of the button
     * @param color color to render the button (Use {@link Colors#get(int color)})
     * @param mouse the {@link MouseHandler} from the {@link Game}
     */
    public Button(int xPos, int yPos, int width, int height, int color, MouseHandler mouse) {
        this(xPos, yPos, width, height, color, "", 1, -1, mouse);
    }

    /**
     * Used for instantiating a {@link Button}
     *
     * @param xPos x-coord for the orign of the button
     * @param yPos y-coord for the orign of the button
     * @param width the width of the button
     * @param height the height of the button
     * @param color color to render the button (Use {@link Colors#get(int color)})
     * @param message message to display on the button
     * @param messageScale scale at which to display the message
     * @param messageColor color to render the message (Use {@link Colors#get(int, int, int, int)})
     * @param mouse the {@link MouseHandler} from the {@link Game}
     */
    public Button(int xPos, int yPos, int width, int height, int color, String message, int messageScale, int messageColor, MouseHandler mouse) {
        this.rect = new Rect(xPos, yPos, width, height, color, false);
        this.message = message;
        this.messageScale = messageScale;
        this.messageColor = messageColor;
        this.mouse = mouse;

        int maxLength = 0;
        for (String line: message.split("\n")) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }
        
        int messageLength = 8 * messageScale * maxLength;
        int messageHeight = 8 * messageScale * (int)message.split("\n").length;
        this.messageX = xPos + width / 2 - messageLength / 2;
        this.messageY = yPos + height / 2 - messageHeight / 2;
    }

    /**
     * Used to check if the {@link Button} is pressed
     *
     * @param includesHeld if holding the button should count as repeated clicks
     * 
     * @return if the button is clicked
     */
    public boolean isClicked(boolean includesHeld) {
        boolean clicked = this.rect.collides(this.mouse.getX(), this.mouse.getY()) && this.mouse.getButton(MouseEvent.BUTTON1).isPressed() && (this.prevClicks != this.mouse.getButton(MouseEvent.BUTTON1).getNumTimesPressed() || includesHeld);
        this.prevClicks = this.mouse.getButton(MouseEvent.BUTTON1).getNumTimesPressed();
        return clicked;
    }

    /**
     * Renders the button
     *
     * @param screen screen on which to render the button
     */
    public void render(Screen screen) {
        this.rect.render(screen);
        if (!this.message.equals("")) {
            Font.render(this.message, screen, this.messageX, this.messageY, this.messageColor, this.messageScale, false);
        }
    }
}
