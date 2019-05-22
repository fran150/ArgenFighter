package ar.com.pachisoft.argenfighter.gfx.exceptions;

import ar.com.pachisoft.argenfighter.gfx.Texture;

public class SpriteCoordinatesOutOfBoundsException extends Exception {
    private final int requestedX;
    private final int requestedY;
    private final int requestedWidth;
    private final int requestedHeight;

    private final int textureWidth;
    private final int textureHeight;

    public SpriteCoordinatesOutOfBoundsException(int requestedX, int requestedY, int requestedWidth, int requestedHeight, Texture texture) {
        super("The requested sprite coordinates are outside of the provided texture");

        this.requestedX = requestedX;
        this.requestedY = requestedY;
        this.requestedWidth = requestedWidth;
        this.requestedHeight = requestedHeight;
        this.textureWidth = texture.getWidth();
        this.textureHeight = texture.getHeight();
    }

    public int getRequestedX() {
        return requestedX;
    }

    public int getRequestedY() {
        return requestedY;
    }

    public int getRequestedWidth() {
        return requestedWidth;
    }

    public int getRequestedHeight() {
        return requestedHeight;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }
}
