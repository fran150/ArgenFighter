package ar.com.pachisoft.argenfighter.main;

import ar.com.pachisoft.argenfighter.input.KeyHandler;
import ar.com.pachisoft.argenfighter.input.MouseHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main window in the game
 */
public final class MainWindow extends Canvas {
    /**
     * Creates the main game window
     *
     * @param width Width of the window
     * @param height Height of the window
     * @param title Title of the window
     */
    public MainWindow(int width, int height, String title) {
        initMainWindow(width, height, title);
        initKeyboardHandler();
        initMouseHandler();
    }

    /**
     * Initialize main game window
     *
     * @param width Main game window width
     * @param height Main game window height
     * @param title Window title
     */
    private void initMainWindow(int width, int height, String title) {
        JFrame frame = new JFrame(title);

        Dimension dimension = new Dimension(width, height);

        frame.setPreferredSize(dimension);
        frame.setMaximumSize(dimension);
        frame.setMinimumSize(dimension);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(this);

        frame.setVisible(true);
    }

    /**
     * Attach a keyboard handler to the window
     */
    private void initKeyboardHandler() {
        addKeyListener(new KeyHandler());
    }

    /**
     * Attach a mouse handler to the window
     */
    private void initMouseHandler() {
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        addMouseWheelListener(mouseHandler);
    }
}
