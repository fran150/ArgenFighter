package ar.com.pachisoft.argenfighter;

/**
 * Point marking the floor limits on each level
 *
 * A set of PathLimit defines a polygon where the player is free to walk.
 */
public class PathLimit implements Comparable {
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
    public int compareTo(Object o) {
        if (o.getClass() == this.getClass()) {
            PathLimit path = (PathLimit) o;

            if (x != path.getX()) {
                return x - path.getX();
            } else {
                return y - path.getY();
            }
        } else {
            return this.compareTo(o);
        }
    }
}
