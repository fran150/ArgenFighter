package ar.com.pachisoft.argenfighter.main;

import ar.com.pachisoft.argenfighter.Game;
import ar.com.pachisoft.argenfighter.input.MouseHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Main game loop implementation
 */
public final class GameLoop {
    /**
     * Number of nano seconds in a second
     */
    private static final double NANO_SECONDS = 1000000000;

    // List of game objects
    private final Map<String, GameObject> gameObjects;
    private final Set<Renderable> renderables;
    private final Set<TimeSteppable> timeSteppables;


    // Frames per second and steps per second
    private int fps = 0;
    private int tps = 0;

    /**
     * Creates a new game loop
     */
    public GameLoop() {
        gameObjects = new HashMap<>();
        renderables = new HashSet<>();
        timeSteppables = new HashSet<>();
    }

    /**
     * Check if the game object exists in the renderables and steppables sets and removes from them
     *
     * @param prev Object to remove from the sets
     */
    private void removeFromSets(GameObject prev) {
        if (prev != null) {
            if (prev instanceof Renderable) {
                renderables.remove(prev);
            }

            if (prev instanceof TimeSteppable) {
                timeSteppables.remove(prev);
            }
        }
    }

    /**
     * Add a game object to the game loop
     * If the object is a Renderable object it will be processed on the render loop
     * If the object is a TimeSteppable object it will be processed on the physics loop
     * If a object with the same name exists in the game loop it will be replaced by the new one
     *
     * @param gameObject Object to add to the game loop
     */
    public void add(GameObject gameObject) {
        GameObject prev = gameObjects.put(gameObject.getName(), gameObject);
        removeFromSets(prev);

        if (gameObject instanceof Renderable) {
            renderables.add((Renderable) gameObject);
        }

        if (gameObject instanceof TimeSteppable) {
            timeSteppables.add((TimeSteppable) gameObject);
        }
    }

    /**
     * Remove the object with the specified name from the game loop
     *
     * @param name Name of the object to remove
     */
    public void remove(String name) {
        GameObject prev = gameObjects.remove(name);
        removeFromSets(prev);
    }

    /**
     * Remove the object from the game loop
     *
     * @param gameObject Game object to remove from the loop
     */
    public void remove(GameObject gameObject) {
        remove(gameObject.getName());
    }

    /**
     * Render step of the game loop
     */
    private void render() {
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
        for (Renderable renderable : renderables) {
            renderable.render(graphics);
        }

        // Dispose graphics system and draw buffer
        graphics.dispose();
        bs.show();
    }

    /**
     * Time step for all game objects
     */
    private void timestep() {
        // Step all game objects physics
        for (TimeSteppable timeSteppable : timeSteppables) {
            timeSteppable.timestep();
        }
    }

    /**
     * Loop the objects in the game
     */
    public void loop() {
        // Time step size
        double dt;
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
                if (physicStep >= GameConsts.FRAME_TIME) {
                    // Remove used time from remaining physics time
                    physicStep -= GameConsts.FRAME_TIME;

                    timestep();

                    tFrames++;

                    MouseHandler.update();
                }
            }

            render();
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

    /**
     * Get the current frames per second
     *
     * @return Frames per second
     */
    public int getFps() {
        return fps;
    }

    /**
     * Get the current steps per second
     *
     * @return Steps per second
     */
    public int getTps() {
        return tps;
    }
}
