package ar.com.pachisoft.argenfighter.world.levels;

/**
 * A game levels
 *
 * This class allows to configure each of the game's levels.
 *
 */
public class Level {
    private final int width;
    private final int height;

    private final LevelPath path;

    public Level(int width, int height, LevelPath path) {
        this.width = width;
        this.height = height;
        this.path = path;
    }
}
