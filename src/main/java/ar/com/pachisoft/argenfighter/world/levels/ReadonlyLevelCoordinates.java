package ar.com.pachisoft.argenfighter.world.levels;

import javax.naming.OperationNotSupportedException;

public final class ReadonlyLevelCoordinates extends LevelCoordinates {

    public ReadonlyLevelCoordinates(int x, int y, int height) {
        super(x, y, height);
    }

    @Override
    public void setX(int x) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("You can't change the coordinates of this object");
    }

    @Override
    public void setY(int y) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("You can't change the coordinates of this object");
    }

    @Override
    public void setHeight(int height) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("You can't change the coordinates of this object");
    }
}
