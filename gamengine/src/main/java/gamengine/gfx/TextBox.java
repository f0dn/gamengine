package gamengine.gfx;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

import gamengine.inp.KeyHandler;
import gamengine.inp.MouseHandler;
import gamengine.Game;

/**
 * A class that is used to recieve text input from the user
 */
public class TextBox {

    private Rect rect;
    private String text = "";
    private int textScale;
    private int textColor;
    private MouseHandler mouse;
    private KeyHandler keyBoard;
    private boolean active = false;
    private int cursor = 0;
    private int[] keyStates = new int[525];

    /**
     * Used for instantiating a {@link TextBox}
     *
     * @param xPos x-coord for the orign of the text-box
     * @param yPos y-coord for the orign of the text-box
     * @param width the width of the text-box
     * @param height the height of the text-box
     * @param bgColor color to render the background of the text-box (Use {@link Colors#get(int color)})
     * @param textScale scale at which to display the text in the text-box
     * @param textColor color to render the text in the text-box (Use {@link Colors#get(int, int, int, int)})
     * @param mouse the {@link MouseHandler} from the {@link Game}
     * @param keyBoard the {@link KeyHandler} from the {@link Game}
     */
    public TextBox(int xPos, int yPos, int width, int height, int bgColor, int textScale, int textColor, MouseHandler mouse, KeyHandler keyBoard) {
        this.rect = new Rect(xPos, yPos, width, height, bgColor, false);
        this.textScale = textScale;
        this.textColor = textColor;
        this.mouse = mouse;
        this.keyBoard = keyBoard;
    }

    /**
     * Updates the text in the text-box
     * <p>Should be called once every frame</p>
     */
    public void update() {
        if (this.mouse.getButton(MouseEvent.BUTTON1).isPressed()) {
            if (this.rect.collides(this.mouse.getX(), this.mouse.getY())) {
                this.active = true;
            } else {
                this.active = false;
            }
        }

        for (int i = 0; i < this.keyStates.length; i++) {
            if (this.keyBoard.getKey(i).isPressed()) {
                if (this.keyStates[i] == this.keyBoard.getKey(i).getNumTimesPressed()) continue;
                this.keyStates[i] = this.keyBoard.getKey(i).getNumTimesPressed();
                char c = '\t';
                if (this.keyBoard.getKey(KeyEvent.VK_SHIFT).isPressed()) {
                    switch (i) {
                        case KeyEvent.VK_ENTER:
                            c = '\n';
                            break;
                        case KeyEvent.VK_BACK_SPACE:
                            this.text = this.text.substring(0, this.cursor - 1) + this.text.substring(this.cursor, this.text.length());
                            break;
                        case KeyEvent.VK_SPACE:
                            c = ' ';
                            break;
                        case KeyEvent.VK_COMMA:
                            c = ',';
                            break;
                        case KeyEvent.VK_MINUS:
                            c = '-';
                            break;
                        case KeyEvent.VK_PERIOD:
                            c = '.';
                            break;
                        case KeyEvent.VK_SLASH:
                            c = '/';
                            break;
                        case KeyEvent.VK_0:
                            c = '0';
                            break;
                        case KeyEvent.VK_1:
                            c = '1';
                            break;
                        case KeyEvent.VK_2:
                            c = '2';
                            break;
                        case KeyEvent.VK_3:
                            c = '3';
                            break;
                        case KeyEvent.VK_4:
                            c = '4';
                            break;
                        case KeyEvent.VK_5:
                            c = '5';
                            break;
                        case KeyEvent.VK_6:
                            c = '6';
                            break;
                        case KeyEvent.VK_7:
                            c = '7';
                            break;
                        case KeyEvent.VK_8:
                            c = '8';
                            break;
                        case KeyEvent.VK_9:
                            c = '9';
                            break;
                        case KeyEvent.VK_SEMICOLON:
                            c = ';';
                            break;
                        case KeyEvent.VK_EQUALS:
                            c = '=';
                            break;
                        case KeyEvent.VK_A:
                            c = 'a';
                            break;
                        case KeyEvent.VK_B:
                            c = 'b';
                            break;
                        case KeyEvent.VK_C:
                            c = 'c';
                            break;
                        case KeyEvent.VK_D:
                            c = 'd';
                            break;
                        case KeyEvent.VK_E:
                            c = 'e';
                            break;
                        case KeyEvent.VK_F:
                            c = 'f';
                            break;
                        case KeyEvent.VK_G:
                            c = 'g';
                            break;
                        case KeyEvent.VK_H:
                            c = 'h';
                            break;
                        case KeyEvent.VK_I:
                            c = 'i';
                            break;
                        case KeyEvent.VK_J:
                            c = 'j';
                            break;
                        case KeyEvent.VK_K:
                            c = 'k';
                            break;
                        case KeyEvent.VK_L:
                            c = 'l';
                            break;
                        case KeyEvent.VK_M:
                            c = 'm';
                            break;
                        case KeyEvent.VK_N:
                            c = 'n';
                            break;
                        case KeyEvent.VK_O:
                            c = 'o';
                            break;
                        case KeyEvent.VK_P:
                            c = 'p';
                            break;
                        case KeyEvent.VK_Q:
                            c = 'q';
                            break;
                        case KeyEvent.VK_R:
                            c = 'r';
                            break;
                        case KeyEvent.VK_S:
                            c = 's';
                            break;
                        case KeyEvent.VK_T:
                            c = 't';
                            break;
                        case KeyEvent.VK_U:
                            c = 'u';
                            break;
                        case KeyEvent.VK_V:
                            c = 'v';
                            break;
                        case KeyEvent.VK_W:
                            c = 'w';
                            break;
                        case KeyEvent.VK_X:
                            c = 'x';
                            break;
                        case KeyEvent.VK_Y:
                            c = 'y';
                            break;
                        case KeyEvent.VK_Z:
                            c = 'z';
                            break;
                        case KeyEvent.VK_OPEN_BRACKET:
                            c = '[';
                            break;
                        case KeyEvent.VK_BACK_SLASH:
                            c = '\\';
                            break;
                        case KeyEvent.VK_CLOSE_BRACKET:
                            c = ']';
                            break;
                        case KeyEvent.VK_BACK_QUOTE:
                            c = '`';
                            break;
                        case KeyEvent.VK_QUOTE:
                            c = '\'';
                            break;
                        case KeyEvent.VK_LEFT:
                            if (this.cursor > 1) this.cursor--;
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (this.cursor < this.text.length()) this.cursor++;
                            break;
                    }
                } else {
                    switch (i) {
                        case KeyEvent.VK_ENTER:
                            c = '\n';
                            break;
                        case KeyEvent.VK_BACK_SPACE:
                            this.text = this.text.substring(0, this.cursor - 1) + this.text.substring(this.cursor, this.text.length());
                            break;
                        case KeyEvent.VK_SPACE:
                            c = ' ';
                            break;
                        case KeyEvent.VK_COMMA:
                            c = '<';
                            break;
                        case KeyEvent.VK_MINUS:
                            c = '_';
                            break;
                        case KeyEvent.VK_PERIOD:
                            c = '>';
                            break;
                        case KeyEvent.VK_SLASH:
                            c = '?';
                            break;
                        case KeyEvent.VK_0:
                            c = ')';
                            break;
                        case KeyEvent.VK_1:
                            c = '!';
                            break;
                        case KeyEvent.VK_2:
                            c = '@';
                            break;
                        case KeyEvent.VK_3:
                            c = '#';
                            break;
                        case KeyEvent.VK_4:
                            c = '$';
                            break;
                        case KeyEvent.VK_5:
                            c = '%';
                            break;
                        case KeyEvent.VK_6:
                            c = '^';
                            break;
                        case KeyEvent.VK_7:
                            c = '&';
                            break;
                        case KeyEvent.VK_8:
                            c = '*';
                            break;
                        case KeyEvent.VK_9:
                            c = '(';
                            break;
                        case KeyEvent.VK_SEMICOLON:
                            c = ':';
                            break;
                        case KeyEvent.VK_EQUALS:
                            c = '+';
                            break;
                        case KeyEvent.VK_A:
                            c = 'A';
                            break;
                        case KeyEvent.VK_B:
                            c = 'B';
                            break;
                        case KeyEvent.VK_C:
                            c = 'C';
                            break;
                        case KeyEvent.VK_D:
                            c = 'D';
                            break;
                        case KeyEvent.VK_E:
                            c = 'E';
                            break;
                        case KeyEvent.VK_F:
                            c = 'F';
                            break;
                        case KeyEvent.VK_G:
                            c = 'G';
                            break;
                        case KeyEvent.VK_H:
                            c = 'H';
                            break;
                        case KeyEvent.VK_I:
                            c = 'I';
                            break;
                        case KeyEvent.VK_J:
                            c = 'J';
                            break;
                        case KeyEvent.VK_K:
                            c = 'K';
                            break;
                        case KeyEvent.VK_L:
                            c = 'L';
                            break;
                        case KeyEvent.VK_M:
                            c = 'M';
                            break;
                        case KeyEvent.VK_N:
                            c = 'N';
                            break;
                        case KeyEvent.VK_O:
                            c = 'O';
                            break;
                        case KeyEvent.VK_P:
                            c = 'P';
                            break;
                        case KeyEvent.VK_Q:
                            c = 'Q';
                            break;
                        case KeyEvent.VK_R:
                            c = 'R';
                            break;
                        case KeyEvent.VK_S:
                            c = 'S';
                            break;
                        case KeyEvent.VK_T:
                            c = 'T';
                            break;
                        case KeyEvent.VK_U:
                            c = 'U';
                            break;
                        case KeyEvent.VK_V:
                            c = 'V';
                            break;
                        case KeyEvent.VK_W:
                            c = 'W';
                            break;
                        case KeyEvent.VK_X:
                            c = 'X';
                            break;
                        case KeyEvent.VK_Y:
                            c = 'Y';
                            break;
                        case KeyEvent.VK_Z:
                            c = 'Z';
                            break;
                        case KeyEvent.VK_OPEN_BRACKET:
                            c = '{';
                            break;
                        case KeyEvent.VK_BACK_SLASH:
                            c = '|';
                            break;
                        case KeyEvent.VK_CLOSE_BRACKET:
                            c = '}';
                            break;
                        case KeyEvent.VK_BACK_QUOTE:
                            c = '~';
                            break;
                        case KeyEvent.VK_QUOTE:
                            c = '"';
                            break;
                        case KeyEvent.VK_LEFT:
                            if (this.cursor > 1) this.cursor--;
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (this.cursor < this.text.length()) this.cursor++;
                            break;
                    }
                }

                if (c != '\t') {
                    this.text = this.text.substring(0, this.cursor) + c + this.text.substring(this.cursor, this.text.length());
                    break;
                }
            }
        }
    }

    /**
     * Used to check if the {@link TextBox} is currently active
     * 
     * @return if the text-box is active
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Renders the text-box and the text within
     *
     * @param screen screen on which to render the text-box
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

    /**
     * Used to get all the text that the user has inputed
     *
     * @return the text inside the text-box
     */
    public String getText() {
        return this.text;
    }
}
