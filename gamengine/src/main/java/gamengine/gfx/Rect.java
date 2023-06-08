package gamengine.gfx;

/**
 * A class that is used for hitboxes and rendering sprites over them
 */
public class Rect {

    private int x;
    private int y;
    private int width;
    private int height;
    private int color;
    private int sprite = -1;
    private int scale;
    private boolean offset;
    
    /**
     * Used for instantiating a {@link Rect}
     *
     * @param xPos x-coord for the orign of the rectangle
     * @param yPos y-coord for the orign of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color color to render the rectangle (Use {@link Colors#get(int color)})
     * @param offset if the rectangle should be rendered with an offset
     */
    public Rect(int xPos, int yPos, int width, int height, int color, boolean offset) {
        this.x = xPos;
        this.y = yPos;
        this.width = width;
        this.height = height;
        this.color = color;
        this.offset = offset;
    }

    /**
     * Used for instantiating a {@link Rect}
     *
     * @param xPos x-coord for the orign of the rectangle
     * @param yPos y-coord for the orign of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color color to render the sprite (Use {@link Colors#get(int color1, int color2, int color3, int color4)})
     * @param sprite location of a sprite from a {@link SpriteSheet}
     * @param scale scale at which to render the sprite
     * @param offset if the rectangle should be rendered with an offset
     */
    public Rect(int xPos, int yPos, int width, int height, int color, int sprite, int scale, boolean offset) {
        this.x = xPos;
        this.y = yPos;
        this.width = width;
        this.height = height;
        this.color = color;
        this.sprite = sprite;
        this.scale = scale;
        this.offset = offset;
    }

    /**
     * Renders the rectangle
     *
     * @param screen screen on which to render the rectangle
     */
    public void render(Screen screen) {
        this.render(screen, false, false);
    }

    /**
     * Renders the rectangle
     *
     * @param screen screen on which to render the rectangle
     * @param mirrorX if the sprite should be mirrored horizontially
     * @param mirrorY if the sprite should be mirrored vertically
     */
    public void render(Screen screen, boolean mirrorX, boolean mirrorY) {
        if (this.sprite != -1) {
            screen.render(this.x, this.y, this.sprite, this.color, mirrorX, mirrorY, this.scale, this.offset);
        } else {
            screen.renderBox(this.x, this.y, this.width, this.height, this.color, this.offset);
        }
    }

    /**
     * Checks if a {@link Rect} overlaps with this rectangle
     *
     * @param rect rectangle to which check for overlap
     * @return if the rectangles overlap
     */
    public boolean collides(Rect rect) {
        if (this.x > rect.x + rect.width || rect.x > this.x + this.width) {
            return false;
        }

        if (this.y + this.height > rect.y || rect.y + rect.height > this.y) {
            return false;
        }

        return true;
    }

    /**
     * Checks if a point is inside this rectangle
     *
     * @param x x-coord of the point
     * @param y y-coord of the point
     * @return if the point lies inside this rectangle
     */
    public boolean collides(int x, int y) {
        return x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height;
    }

    /**
     * Used to get the x-position at which the rectangle will be drawn on the screen
     * 
     * @return the x position of the rectangle
     */
    public int getX() {
        return this.x;
    }

    /**
     * Used to get the y-position at which the rectangle will be drawn on the screen
     * 
     * @return the y position of the rectangle
     */
    public int getY() {
        return this.y;
    }

    /**
     * Used to get the width of the rectangle
     * 
     * @return the width of the rectangle
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Used to get the height of the rectangle
     * 
     * @return the height of the rectangle
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the x positon of the rectangle to {@code x}
     * 
     * @param x the new x position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y positon of the rectangle to {@code y}
     * 
     * @param y the new y position
     */
    public void setY(int y) {
        this.y = y;
    }
}
