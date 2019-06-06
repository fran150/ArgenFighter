package ar.com.pachisoft.argenfighter.world.levels;

import org.jetbrains.annotations.NotNull;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class LevelCoordinates implements Comparable<LevelCoordinates> {
    private int x;
    private int y;
    private int height;

    public LevelCoordinates(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) throws OperationNotSupportedException {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) throws OperationNotSupportedException {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws OperationNotSupportedException {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LevelCoordinates)) return false;
        LevelCoordinates that = (LevelCoordinates) o;
        return x == that.x &&
                y == that.y &&
                height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, height);
    }

    @Override
    public int compareTo(@NotNull LevelCoordinates o) {
        Integer x = this.getX();
        int xDif = x.compareTo(o.getX());

        if (xDif == 0) {
            Integer y = this.getY();
            int yDif = y.compareTo(o.getY());

            if (yDif == 0) {
                Integer h = this.getHeight();
                return h.compareTo(o.getHeight());
            } else {
                return yDif;
            }
        } else {
            return xDif;
        }
    }
}
