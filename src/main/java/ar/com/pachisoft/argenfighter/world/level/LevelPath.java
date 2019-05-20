package ar.com.pachisoft.argenfighter.world.level;

import ar.com.pachisoft.argenfighter.world.level.exceptions.PathLimitsEmptyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a path for the player to freely walk on the level
 *
 * A set of upper and lower points forms a polygon where the player can freely walk.
 * Walk outside this polygon will not be allowed
 */
public class LevelPath {
    private final List<PathLimit> upperLimits;
    private final List<PathLimit> lowerLimits;

    /**
     * Creates a new level path.
     *
     * It allows to define a set of points for the upper and lower bounds of the path that
     * the player is allowed to walk inside each level.
     * Player movement outside the path will not be allowed
     * If only one point is specified the path bound will be and horizontal line passing through
     * the specified point
     *
     * @param upperLimits List of points that conforms the upper bound
     * @param lowerLimits List of points that conforms the lower bound
     *
     * @throws PathLimitsEmptyException There must be at least one point on each upper and lower bounds
     */
    public LevelPath(ArrayList<PathLimit> upperLimits, ArrayList<PathLimit> lowerLimits)
            throws PathLimitsEmptyException {

        // Check that limits have at least one point
        if (upperLimits == null || upperLimits.size() == 0) throw new PathLimitsEmptyException(PathLimitType.UPPER);
        if (lowerLimits == null || lowerLimits.size() == 0) throw new PathLimitsEmptyException(PathLimitType.LOWER);

        // Set the upper and lower limits properties
        this.upperLimits = new ArrayList<>(upperLimits);
        this.lowerLimits = new ArrayList<>(lowerLimits);

        // Sort the points by the x axis position
        Collections.sort(this.upperLimits);
        Collections.sort(this.lowerLimits);
    }

    /**
     * Gets the upper or lower bound at the specified x position
     *
     * @param x X axis position
     * @param type Get the upper or lower bound
     * @return Y axis limit at the specified position
     */
    public int getLimitAt(int x, PathLimitType type) {
        List<PathLimit> limits;

        // Use the upper or lower limit list according to the type
        if (type == PathLimitType.UPPER) {
            limits = upperLimits;
        } else {
            limits = lowerLimits;
        }

        // Get the leftmost and rightmost points
        PathLimit leftMost = limits.get(0);
        PathLimit rightMost = limits.get(limits.size() - 1);

        // If the specified x is less than the leftmost limit return that limit
        if (x <= leftMost.getX()) {
            return leftMost.getY();
        }

        // If the specified x is greater than the rightmost limit return that limit
        if (x >= rightMost.getY()) {
            return rightMost.getY();
        }

        // Get the index of the limit to the left of the specified point
        int index = getIndex(x, limits);

        // Get the left and right limits of the specified point
        PathLimit left = limits.get(index);
        PathLimit right = limits.get(index + 1);

        // Calculate the upper limit interpolating between the two point heights
        return interpolatePoint(left, right, x);
    }

    /**
     * Calculates the limit on the Y axis in the given X position interpolating between the
     * two limits
     *
     * @param left Limit to the left of the given position
     * @param right Limit to the right of the given position
     * @param x Position in where to calculate the upper limit
     * @return Y axis limit position at the specified X position
     */
    private static int interpolatePoint(PathLimit left, PathLimit right, int x) {
        double x1 = left.getX();
        double y1 = left.getY();
        double x2 = right.getX();
        double y2 = right.getY();

        double slope = (y2 - y1) / (x2 - x1);
        double result = slope * ((x - x1) + y1);

        return (int) result;
    }

    /**
     * Returns the index in the specified list to the left of the specified position.
     * It perform a binary search over the list
     *
     * @param x X position to search
     * @param limits Limits list
     * @return Index in the limits list of the point closer to the left of the specified position
     */
    private static int getIndex(int x, List<PathLimit> limits) {
        // If the limits array is null or empty return an invalid index
        if (limits == null || limits.size() == 0) {
            return -1;
        }

        // Create an sliding window with left and right pointers
        int l = 0;
        int r = limits.size() - 1;

        // If the limit is out of bound return -1
        PathLimit v = limits.get(l);
        if (x < v.getX()) {
            return -1;
        }

        v = limits.get(r);
        if (x > v.getX()) {
            return -1;
        }

        // While window width is larger than one
        while ((r - l) > 1) {
            // Sets the current position to the middle of the window
            int p = l + ((r - l) / 2);
            v = limits.get(p);

            // Shrink the sliding window limits to the correct half
            if (x < v.getX()) {
                r = p;
            } else if (x > v.getX()) {
                l = p;
            } else {
                return p;
            }
        }

        // The index value is the one exactly to the left of the target
        return l;
    }
}
