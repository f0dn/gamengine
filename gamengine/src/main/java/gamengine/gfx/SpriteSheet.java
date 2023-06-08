package gamengine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A class for reading the pixel data from a spritesheet
 * <p>Used for instantiating a {@link Screen}</p>
 */
public class SpriteSheet {

    /**
     * The pixel width of the {@link SpriteSheet}
     */
    protected int width;

    /**
     * The pixel height of the {@link SpriteSheet}
     */
    protected int height;

    /**
     * The an integer array that holds all the pixels in the {@link SpriteSheet}
     */
    protected int[] pixels;

    /**
     * Used for instantiating a {@link SpriteSheet}
     *
     * @param path the path to the location of the spritesheet
     */
    public SpriteSheet(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image == null) {
            return;
        }

        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pixels = image.getRGB(0, 0, this.width, this.height, null, 0, this.width);

        for (int i = 0; i < this.pixels.length; i++) {
            this.pixels[i] = (this.pixels[i] & 0xff) / 64;
        }
    }
}
