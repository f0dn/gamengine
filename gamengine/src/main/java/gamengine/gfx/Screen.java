package gamengine.gfx;

/**
 * A class for rendering sprites to a {@link Screen}
 */
public class Screen {

    /**
     * An integer array of all the pixels colors of the screen
     */
    protected int[] pixels;

    /**
     * The X-offset of all pixels on the screen
     * <p>This can be used to simulate movement in a game</p>
     */
    public int xOffset = 0;
    /**
     * The Y-offset of all pixels on the screen
     * <p>This can be used to simulate movement in a game</p>
     */
    public int yOffset = 0;
    
    /**
     * An integer that stores the width of the screen
     */
    protected int width;

    /**
     * An integer that stores the width of the screen
     */
    protected int height;

    /**
     * The {@link SpriteSheet} that thw screen will draw sprites from
     */
    protected SpriteSheet sheet;

    /**
     * Used for instantiating a {@link Screen}
     *
     * @param width the width of the screen
     * @param height the height of the screen
     * @param sheet the {@link SpriteSheet} from which to draw
     */
    public Screen(int width, int height, SpriteSheet sheet) {
        this.width = width;
        this.height = height;
        this.sheet = sheet;
        this.pixels = new int[this.width * this.height];
    }

    /**
     * Fills the screen with a single color
     *
     * @param color color to fill the screen (Use {@link Colors#get(int color)})
     */
    public void fill(int color) {
        if (color == 255) return;
        for (int i = 0; i < this.pixels.length; i++) {
            this.pixels[i] = color;
        }
    }

    /**
     * Renders a rectangle on the screen
     * 
     * @param xPos x-coord to render the rectangle
     * @param yPos y-coord to render the rectangle
     * @param width the width to render the rectangle
     * @param height the height to render the rectangle
     * @param color color to fill the screen (Use {@link Colors#get(int color)})
     * @param offset a boolean representing if an offset should be applied to the rectangle
     */
    protected void renderBox(int xPos, int yPos, int width, int height, int color, boolean offset) {
        if (color == 255) return;
        if (offset) {
            xPos -= this.xOffset;
            yPos -= this.yOffset;
        }

        for (int x = xPos; x < xPos + width; x++) {
            if (x < 0 || x >= this.width) continue;
            for (int y = yPos; y < yPos + height; y++) {
                if (y < 0 || y >= this.height) continue;
                this.pixels[x + y * this.width] = color;
            }
        }
    }

    /**
     * Renders a sprite from {@link SpriteSheet}
     *
     * @param xPos x-coord to render the sprite
     * @param yPos y-coord to render the sprite
     * @param sprite location of a sprite from a {@link SpriteSheet}
     * @param color color to render tile (Use {@link Colors#get(int color1, int color2, int color3, int color4)})
     */
    public void render(int xPos, int yPos, int sprite, int color) {
        this.render(xPos, yPos, sprite, color, false, false, 1, true);
    }

    /**
     * Renders a sprite from {@link SpriteSheet}
     *
     * @param xPos x-coord to render the sprite
     * @param yPos y-coord to render the sprite
     * @param sprite location of a sprite from a {@link SpriteSheet}
     * @param color color to render sprite (Use {@link Colors#get(int color1, int color2, int color3, int color4)})
     * @param scale scale at which to render the sprite
     */
    public void render(int xPos, int yPos, int sprite, int color, int scale) {
        this.render(xPos, yPos, sprite, color, false, false, scale, true);
    }

    /**
     * Renders a sprite from {@link SpriteSheet}
     *
     * @param xPos x-coord to render the sprite
     * @param yPos y-coord to render the sprite
     * @param sprite location of a sprite from a {@link SpriteSheet}
     * @param color color to render sprite (Use {@link Colors#get(int color1, int color2, int color3, int color4)})
     * @param mirrorX if the sprite should be mirrored horizontially
     * @param mirrorY if the sprite should be mirrored vertically
     */
    public void render(int xPos, int yPos, int sprite, int color, boolean mirrorX, boolean mirrorY) {
        this.render(xPos, yPos, sprite, color, mirrorX, mirrorY, 1, true);
    }

    /**
     * Renders a sprite from {@link SpriteSheet}
     *
     * @param xPos x-coord to render the sprite
     * @param yPos y-coord to render the sprite
     * @param sprite location of a sprite from a {@link SpriteSheet}
     * @param color color to render sprite (Use {@link Colors#get(int color1, int color2, int color3, int color4)})
     * @param mirrorX if the sprite should be mirrored horizontially
     * @param mirrorY if the sprite should be mirrored vertically
     * @param scale scale at which to render the sprite
     * @param offset if the sprite should be rendered with an offset
     */
    public void render(int xPos, int yPos, int sprite, int color, boolean mirrorX, boolean mirrorY, int scale, boolean offset) {
        if (offset) {
            xPos -= this.xOffset;
            yPos -= this.yOffset;
        }
        
        int xSprite = sprite % 32;
        int ySprite = sprite / 32;
        int spriteOffset = (xSprite << 3) + (ySprite << 3) * this.sheet.width;

        for (int y = 0; y < 8; y++) {
            int ySheet = y;
            if (mirrorY) ySheet = 7 - y;
            int yPixel = yPos + (y * scale);
            for (int x = 0; x < 8; x++) {
                int xSheet = x;
                if (mirrorX) xSheet = 7 - x;
                int xPixel = xPos + (x * scale);
                int col = (color >> (this.sheet.pixels[xSheet + ySheet * this.sheet.width + spriteOffset] * 8)) & 255;
                if (col < 255) {
                    for (int yScale = 0; yScale < scale; yScale++) {
                        if (yPixel + yScale < 0 || yPixel + yScale >= this.height) continue;
                        for (int xScale = 0; xScale < scale; xScale++) {
                            if (xPixel + xScale < 0 || xPixel + xScale >= this.width) continue;
                            this.pixels[(xPixel + xScale) + (yPixel + yScale) * this.width] = col;
                        }
                    }
                }
            }
        }
    }

    /**
     * Used to get the given screen height
     * 
     * @return the height of the screen
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Used to get the given width of the screen
     * 
     * @return the width of the screen
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Used to get an integer array of all the screen's pixels
     * 
     * @return the pixels on the screen
     */
    public int[] getPixels() {
        return this.pixels;
    }
}
