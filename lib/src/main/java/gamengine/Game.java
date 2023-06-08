package gamengine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import gamengine.gfx.Screen;
import gamengine.gfx.SpriteSheet;
import gamengine.inp.KeyHandler;
import gamengine.inp.MouseHandler;

/**
 * A class that is the foundation for a Java 2D game
 */
public abstract class Game extends Canvas implements Runnable {

    /**
     * The width of the window
     */
    public int WIDTH;
    /**
     * The height of the window
     */
    public int HEIGHT;
    /**
     * The scale at which to draw pixels
     */
    public int SCALE;
    /**
     * The desired frames per second
     */
    public int FPS;
    /**
     * The title of the window
     */
    public String NAME;

    /**
     * A boolean that represents the state of the game
     */
    private boolean running = false;
    /**
     * The number of ticks
     */
    private int ticks = 0;
    /**
     * The number of frames
     */
    private int frames = 0;

    /**
     * The window
     */
    private JFrame frame;
    /**
     * The buffer system that draws to the screen
     */
    private BufferedImage image;
    /**
     * An array of all the pixels on the screen
     */
    private int[] pixels;
    /**
     * An array of all the possible pixel colors
     */
    private int[] colors = new int[6 * 6 * 6];

    /**
     * The {@link Screen} that renders pixel data
     */
    public Screen screen;

    /**
     * The {@link KeyHandler} that keeps track of key-strokes
     */
    public KeyHandler keyboard;

    /**
     * The {@link MouseHandler} that keeps track of mouse-clicks
     */
    public MouseHandler mouse;

    /**
     * Used for instantiating a {@link Game}
     *
     * @param width the width of the game window
     * @param height the height of the game window
     * @param scale the ratio of screen pixels to in-game pixels
     * @param fps the desired frames per second for the game
     * @param name the title of the window/game
     * @param spriteSheetPath the path to the sprite sheet
     */
    public Game(int width, int height, int scale, int fps, String name, String spriteSheetPath) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.SCALE = scale;
        this.FPS = fps;
        this.NAME = name;

        this.image = new BufferedImage(this.WIDTH, this.HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.pixels = ((DataBufferInt)this.image.getRaster().getDataBuffer()).getData();

        this.setMinimumSize(new Dimension(this.WIDTH * this.SCALE, this.HEIGHT * this.SCALE));
        this.setMaximumSize(new Dimension(this.WIDTH * this.SCALE, this.HEIGHT * this.SCALE));
        this.setPreferredSize(new Dimension(this.WIDTH * this.SCALE, this.HEIGHT * this.SCALE));

        this.frame = new JFrame(this.NAME);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());
        this.frame.add(this, BorderLayout.CENTER);
        this.frame.pack();
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        int index = 0;
        for (int r = 0; r < 6; r++) {
            for (int g = 0; g < 6; g++) {
                for (int b = 0; b < 6; b++) {
                    int rr = r * 255 / 5;
                    int gg = g * 255 / 5;
                    int bb = b * 255 / 5;

                    this.colors[index++] = rr << 16 | gg << 8 | bb;
                }
            }
        }

        this.screen = new Screen(this.WIDTH, this.HEIGHT, new SpriteSheet(spriteSheetPath));
        this.keyboard = new KeyHandler(this);
        this.mouse = new MouseHandler(this);
    }

    /**
     * Starts the game
     */
    public synchronized void start() {
        this.running = true;
        new Thread(this).start();
    }

    /**
     * Stops the game
     */
    public synchronized void stop() {
        this.running = false;
    }

    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D/this.FPS;

        this.ticks = 0;
        this.frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while (this.running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            boolean shouldRender = false;

            while (delta >= 1) {
                this.ticks++;
                this.gameTick();
                delta--;
                shouldRender = true;
            }

            if (shouldRender) {
                this.frames++;
                this.gameRender();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                this.frames = 0;
                this.ticks = 0;
            }
        }
    }

    /**
     * This function is called every frame
     * <p>Put all game logic here</p>
     */
    public abstract void tick();

    /**
     * This function is called whenever the {@code screen} is to be rendered
     * <p>Put all sprites and objects to draw here</p>
     */
    public abstract void render();

    private void gameTick() {
        this.tick();
    }

    private void gameRender() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        this.render();

        for (int y = 0; y < this.screen.getHeight(); y++) {
            for (int x = 0; x < this.screen.getWidth(); x++) {
                int colorCode = this.screen.getPixels()[x + y * this.screen.getWidth()];
                if (colorCode < 255) this.pixels[x + y * this.WIDTH] = this.colors[colorCode];
            }
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
        g.dispose();
        
        bs.show();
    }

    /**
     * Used to find how fast the game is running
     * 
     * @return the current number of ticks per second
     */
    public int getTicks() {
        return this.ticks;
    }

    /**
     * Used to find how fast each buffer is being drawn to the screen
     * 
     * @return the current number of frames per second
     */
    public int getFrames() {
        return this.frames;
    }
}