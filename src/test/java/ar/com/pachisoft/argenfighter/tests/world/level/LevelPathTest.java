package ar.com.pachisoft.argenfighter.tests.world.level;

import ar.com.pachisoft.argenfighter.world.levels.*;
import ar.com.pachisoft.argenfighter.world.levels.exceptions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LevelPathTest {

    private LevelPath path;

    LevelPathTest() {
        ArrayList<PathLimit> upperLimits = new ArrayList<>();
        upperLimits.add(new PathLimit(100, 150));
        upperLimits.add(new PathLimit(0, 100));
        upperLimits.add(new PathLimit(200, 200));

        ArrayList<PathLimit> lowerLimits = new ArrayList<>();
        lowerLimits.add(new PathLimit(200, 500));
        lowerLimits.add(new PathLimit(0, 300));
        lowerLimits.add(new PathLimit(100, 350));

        try {
            path = new LevelPath(upperLimits, lowerLimits);
        } catch (PathLimitsEmptyException e) {
            fail("Error creating LevelPath object");
        }
    }

    @Test
    @DisplayName("Path with empty limits should throw error")
    void shouldThrowException() {
        ArrayList<PathLimit> limits = new ArrayList<>();
        limits.add(new PathLimit(0, 150));

        ArrayList<PathLimit> emptyLimits = new ArrayList<>();

        assertThrows(PathLimitsEmptyException.class, () -> path = new LevelPath(null, limits));

        assertThrows(PathLimitsEmptyException.class, () -> path = new LevelPath(limits, null));

        assertThrows(PathLimitsEmptyException.class, () -> path = new LevelPath(emptyLimits, limits));

        assertThrows(PathLimitsEmptyException.class, () -> path = new LevelPath(limits, emptyLimits));
    }

    @Test
    @DisplayName("Trying to get a value outside the path bound should return the leftmost or rightmost bound")
    void outsideBounds() {
        int y = path.getLimitAt(-10, PathLimitType.UPPER);
        assertEquals(100, y);

        y = path.getLimitAt(250, PathLimitType.UPPER);
        assertEquals(200, y);

        y = path.getLimitAt(-10, PathLimitType.LOWER);
        assertEquals(300, y);

        y = path.getLimitAt(250, PathLimitType.LOWER);
        assertEquals(500, y);
    }

    @Test
    @DisplayName("It should interpolate between limit points")
    void shouldIterpolateBetweenPoints() {
        int y = path.getLimitAt(50, PathLimitType.UPPER);
        assertEquals(125, y);

        y = path.getLimitAt(50, PathLimitType.LOWER);
        assertEquals(325, y);

        y = path.getLimitAt(150, PathLimitType.UPPER);
        assertEquals(175, y);

        y = path.getLimitAt(100, PathLimitType.UPPER);
        assertEquals(150, y);

    }

}