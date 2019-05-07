package ar.com.pachisoft.argenfighter;

import java.util.ArrayList;
import java.util.Collections;

public class LevelPath {
    private final ArrayList<PathLimit> upperLimits;
    private final ArrayList<PathLimit> lowerLimits;

    public LevelPath(ArrayList<PathLimit> upperLimits, ArrayList<PathLimit> lowerLimits) {
        this.upperLimits = new ArrayList<>(upperLimits);
        this.lowerLimits = new ArrayList<>(lowerLimits);

        Collections.sort(this.upperLimits);
        Collections.sort(this.lowerLimits);
    }

    public int getUpperLimitAt(int x) {

    }

    private static int getIndex(int x, ArrayList<PathLimit> limits) {
        // If the limits array is null or empty return an invalid index
        if (limits == null || limits.size() == 0) {
            return -1;
        }

        // Create an sliding window with left and right pointers
        int l = 0;
        int r = limits.size() - 1;

        // If
        PathLimit v = limits.get(l);
        if (x < v.getX()) {
            return -1;
        }

        v = limits.get(r);
        if (x > v.getX()) {
            return limits.size() - 1;
        }

        // While window width is larger than one
        while ((r - l) > 1) {
            // Sets the current position to the middle of the window
            int p = l + ((r - l) / 2);
            v = limits.get(p);

            // Shrink the sliding window limits to the correct half
            if (x < v.getX()) {
                r = p;
            } if (x > v.getX()) {
                l = p;
            } else {
                return p;
            }
        }

        // The index value is the one exactly to the left of the target
        return l;
    }
}
