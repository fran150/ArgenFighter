package ar.com.pachisoft.argenfighter.tests;

import ar.com.pachisoft.argenfighter.world.levels.PathLimit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PathLimitTest {

    @Test
    @DisplayName("Must correctly compare path limits first by X axis and then by Y axis")
    void compareTest() {
        PathLimit limit1 = new PathLimit(0, 10);
        PathLimit limit11 = new PathLimit(0, 10);
        PathLimit limit2 = new PathLimit(10, 0);
        PathLimit limit3 = new PathLimit(10, 10);

        Assertions.assertTrue(limit1.compareTo(limit2) < 0);
        Assertions.assertTrue(limit1.compareTo(limit11) == 0);
        Assertions.assertTrue(limit1.compareTo(limit3) < 0);

        Assertions.assertTrue(limit2.compareTo(limit1) > 0);
        Assertions.assertTrue(limit2.compareTo(limit3) < 0);

        Assertions.assertTrue(limit3.compareTo(limit1) > 0);
        Assertions.assertTrue(limit3.compareTo(limit2) > 0);
    }
}
