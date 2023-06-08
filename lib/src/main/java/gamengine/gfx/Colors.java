package gamengine.gfx;

/**
 * A class for getting the color code for a sprite
 */
public class Colors {

    /**
     * Used to instantiate a {@link Colors} class
     * <p>Note that this is unnecessary as all the methods in this class are static</p>
     */
    public Colors() {

    }
    
    /**
     * Returns a color code used for {@link Screen#render(int, int, int, int)}
     * <p>Takes 4 3-digit numbers from 0-5 with channels for R, G, and B, respectively (Use -1 if transparent)</p>
     *
     * @param color1 the color used to represent the darkest color
     * @param color2 the color used to represent the second darkest color
     * @param color3 the color used to represent the second lightest color
     * @param color4 the color used to represent the lightest color
     * @return color code
     */
    public static int get(int color1, int color2, int color3, int color4) {
        return (Colors.get(color4) << 24) + (Colors.get(color3) << 16) + (Colors.get(color2) << 8) + Colors.get(color1);
    }

    /**
     * Returns a color code used for single color objects like {@link Rect}
     * <p>Takes a 3-digit number with channels for R, G, and B, respectively (Use -1 if transparent)</p>
     *
     * @param color the color in RGB format
     * @return color code
     */
    public static int get(int color) {
        if (color < 0) return 255;
        int r = color / 100 % 10;
        int g = color / 10 % 10;
        int b = color / 1 % 10;
        return r * 36 + g * 6 + b;
    }
}
