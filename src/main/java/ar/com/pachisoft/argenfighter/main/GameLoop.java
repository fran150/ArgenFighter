package ar.com.pachisoft.argenfighter.main;

import ar.com.pachisoft.argenfighter.world.characters.Character;
import ar.com.pachisoft.argenfighter.Game;
import ar.com.pachisoft.argenfighter.input.MouseHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.HashSet;

/**
 * Main game loop implementation
 */
public class GameLoop {
    /**
     * Number of nano seconds in a second
     */
    private static double NANO_SECONDS = 1000000000;
    /**
     * Number of physics steps per second
     */
    private static double PHYSICS_STEP_SIZE = 60;
    /**
     * Time for each physics step
     */
    private static double FRAME_TIME = 1.0 / PHYSICS_STEP_SIZE;

    private HashSet<Character> characters;


    private int fps = 0;
    private int tps = 0;

    /**
     * Creates a new game loop
     */
    public GameLoop() {
        characters = new HashSet<>();
    }

    /**
     * Adds the specified object to the game loop
     *
     * @param character Character to add
     */
    public void add(Character character) {
        characters.add(character);
    }

    /**
     * Removes the specified object to the game loop
     *
     * @param character Character to remove
     */
    public void remove(Character character) {
        characters.remove(character);
    }

    /**
     * Render step of the game loop
     *
     * @param dt Delta time for this step
     */
    private void render(double dt) {
        // Gets the game's main window
        MainWindow win = Game.getMainWindow();

        // Get a buffer strategy
        BufferStrategy bs = win.getBufferStrategy();

        // If a buffer strategy is not found create one
        if (bs == null) {
            win.createBufferStrategy(3);
            return;
        }

        // Get the graphics system
        Graphics2D graphics = (Graphics2D) bs.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, win.getWidth(), win.getHeight());

        // Render all objects
        for (Character object : characters) {
            object.render(graphics, dt);
        }

        // Dispose graphics system and draw buffer
        graphics.dispose();
        bs.show();
    }

    /**
     * Time step for all game objects
     *
     * @param dt Delta time for this step
     */
    private void timestep(double dt) {
        // Step all game objects physics
        for (Character gameObject : characters) {
            gameObject.timestep(dt);
        }
    }

    /**
     * Loop the objects in the game
     */
    public void loop() {
        // Time step size
        double dt = 0;
        // Current physics time step size
        double physicStep = 0;
        // Store the current time to calculate the time step
        long lastTime = System.nanoTime();

        // Store the current time for FPS calculation
        long timer = System.currentTimeMillis();

        int rFrames = 0;
        int tFrames = 0;

        while (Game.isRunning()) {
            // Get current nano time
            long now = System.nanoTime();

            // Calculate elapsed time and reset last time
            dt = (now - lastTime) / NANO_SECONDS;
            lastTime = now;

            if (Game.isPhysicsRunning()) {
                // Calculate time remaining for one physics step
                physicStep += dt;

                // If physic step is complete... step
                if (physicStep >= FRAME_TIME) {
                    // Remove used time from remaining physics time
                    physicStep -= FRAME_TIME;

                    timestep(FRAME_TIME);

                    tFrames++;

                    MouseHandler.update();
                }
            }

            render(dt);
            rFrames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

                fps = rFrames;
                tps = tFrames;

                System.out.printf("FPS: %d | TPS: %d\n", fps, tps);

                rFrames = 0;
                tFrames = 0;
            }
        }
    }
}
