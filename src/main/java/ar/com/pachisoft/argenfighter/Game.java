package ar.com.pachisoft.argenfighter;

import ar.com.pachisoft.argenfighter.main.GameLoop;
import ar.com.pachisoft.argenfighter.main.MainWindow;

/**
 * Main game class
 */
public class Game implements Runnable {
    private static MainWindow mainWindow;
    private static Thread thread;

    private static boolean running = false;
    private static boolean physicsRunning = false;

    private static GameLoop gameLoop;

    /**
     * Application main entry point
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Game.init(args);
        Game.start();
    }

    /**
     * Initialize main window and game loop
     */
    protected static void init(String[] args) {
        mainWindow = new MainWindow(1024, 768, "Ship Command");

        gameLoop = new GameLoop();
        initialize();
    }

    /**
     * Initialize the game
     */
    private static void initialize() {
        runPhysics();
    }

    /**
     * Return if game is running
     *
     * @return True game is running
     */
    public static boolean isRunning() {
        return running;
    }

    /**
     * Return if physics engine is running.
     *
     * When physics engine is stopped, only the render loop is processed
     *
     * @return True if the physics engine is running
     */
    public static boolean isPhysicsRunning() {
        return physicsRunning;
    }

    /**
     * Run the physics engine
     */
    public static void runPhysics() {
        physicsRunning = true;
    }

    /**
     * Stop the physics engine
     */
    public static void stopPhysics() {
        physicsRunning = false;
    }

    /**
     * Returns the game's main window object
     *
     * @return Main window of the game
     */
    public static MainWindow getMainWindow() {
        return mainWindow;
    }


    /**
     * Returns the game loop object
     *
     * @return Game loop object
     */
    public static GameLoop getGameLoop() {
        return gameLoop;
    }

    /**
     * Method executed by the main game thread
     */
    public void run() {
        gameLoop.loop();
    }

    /**
     * Starts the main game thread
     */
    private static synchronized void start() {
        thread = new Thread(new Game());
        thread.start();
        running = true;
    }

    /**
     * Stops the main game thread
     */
    public static synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}