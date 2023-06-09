package gamengine.gfx;

/**
 * A class for rendering text to a {@link Screen}
 */
public class Font {
    
    private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " +
                                        "0123456789.,\"'!-©               ";

    /**
     * Used to instantiate a {@link Font} class
     * <p>Note that this is unnecessary as all the methods in this class are static</p>
     */
    public Font() {

    }

    /**
     * Renders a string of text to the screen.
     *
     * @param message message to be rendered
     * @param screen screen on which to render message
     * @param x x-coord of message
     * @param y y-coord of message
     * @param color color to render message (Use {@link Colors#get(int, int, int, int)})
     * @param offset if the text should be rendered with an offset
     */
    public static void render(String message, Screen screen, int x, int y, int color, boolean offset) {
        Font.render(message, screen, x, y, color, 1, offset);
    }

    /**
     * Renders a string of text to the screen.
     *
     * @param message message to be rendered
     * @param screen screen on which to render message
     * @param x x-coord of message
     * @param y y-coord of message
     * @param color color to render message (Use {@link Colors#get(int, int, int, int)})
     * @param scale value to scale the message by
     * @param offset if the text should be rendered with an offset
     */
    public static void render(String message, Screen screen, int x, int y, int color, int scale, boolean offset) {
        int currX = x;
        int currY = y;
        message = message.toUpperCase();
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == '\n') {
                currX = x;
                currY += 8 * scale;
                continue;
            }
            int charIndex = Font.chars.indexOf(message.charAt(i));
            if (charIndex >= 0) {
                screen.render(currX, currY, charIndex + 30 * 32, color, false, false, scale, offset);
                currX += 8 * scale;
            }
        }
    }
}
