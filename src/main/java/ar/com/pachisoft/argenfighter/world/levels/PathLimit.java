package ar.com.pachisoft.argenfighter.world.levels;

/**
 * Point marking the floor limits on each levels
 *
 * A set of PathLimits defines a polygon where the player is free to walk.
 */
public final class PathLimit implements Comparable<PathLimit> {
    private final int x;
    private final int y;

    /**
     * Creates a new point for the path limit
     *
     * @param x X position of the point
     * @param y Y position of the point
     */
    public PathLimit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the horizontal position of the limit
     *
     * @return Horizontal position of the limit
     */
    public int getX() {
        return x;
    }

    /**
     * Get the vertical position of the limit
     *
     * @return Vertical position of the limit
     */
    public int getY() {
        return y;
    }

    /**
     * Orders PathLimits horizontally first then vertically
     *
     * @param o Object to compare
     * @return Comparison result
     */
    @Override
    public int compareTo(PathLimit o) {
        if (x != o.getX()) {
            return x - o.getX();
        } else {
            return y - o.getY();
        }
    }
}
