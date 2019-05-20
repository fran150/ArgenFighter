package ar.com.pachisoft.argenfighter.world.level.exceptions;

import ar.com.pachisoft.argenfighter.world.level.PathLimitType;

public class PathLimitsEmptyException extends Exception {
    private final PathLimitType type;

    public PathLimitsEmptyException(PathLimitType type) {
        super("The path limit must contain at least one point");

        this.type = type;
    }

    public PathLimitType getType() {
        return type;
    }
}
