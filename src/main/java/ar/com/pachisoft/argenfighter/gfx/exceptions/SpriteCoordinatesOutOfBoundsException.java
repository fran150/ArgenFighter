package ar.com.pachisoft.argenfighter.gfx.exceptions;

import ar.com.pachisoft.argenfighter.gfx.Texture;

/**
 * Thrown if the specified coordinates for creating a sprite from a texture are out of the texture's bounds
 */
public class SpriteCoordinatesOutOfBoundsException extends Exception {
    private final int requestedX;
    private final int requestedY;
    private final int requestedWidth;
    private final int requestedHeight;

    private final int textureWidth;
    private final int textureHeight;
    private final String textureFileName;

    /**
     * Creates an exception
     *
     * @param requestedX X coordinate of the sprite upper left corner in the texture
     * @param requestedY Y coordinate of the sprite upper left corner in the texture
     * @param requestedWidth Width of the sprite
     * @param requestedHeight Height of the sprite
     * @param texture Texture from where to get the sprite
     */
    public SpriteCoordinatesOutOfBoundsException(int requestedX, int requestedY, int requestedWidth, int requestedHeight, Texture texture) {
        super("The requested sprite coordinates are outside of the provided texture");

        this.requestedX = requestedX;
        this.requestedY = requestedY;
        this.requestedWidth = requestedWidth;
        this.requestedHeight = requestedHeight;
        this.textureWidth = texture.getWidth();
        this.textureHeight = texture.getHeight();
        this.textureFileName = texture.getFileName();
    }

    /**
     * Returns the requested x coordinate for sprite's the upper left corner
     *
     * @return X coordinate for sprite's the upper left corner
     */
    public int getRequestedX() {
        return requestedX;
    }

    /**
     * Returns the requested y coordinate for sprite's the upper left corner
     *
     * @return Y coordinate for sprite's the upper left corner
     */
    public int getRequestedY() {
        return requestedY;
    }

    /**
     * Returns the requested width of the sprite
     *
     * @return Requested width of the sprite
     */
    public int getRequestedWidth() {
        return requestedWidth;
    }

    /**
     * Returns the requested height of the sprite
     *
     * @return Requested height of the sprite
     */
    public int getRequestedHeight() {
        return requestedHeight;
    }

    /**
     * Returns the actual texture width
     *
     * @return Texture width
     */
    public int getTextureWidth() {
        return textureWidth;
    }

    /**
     * Returns the actual texture height
     *
     * @return Texture height
     */
    public int getTextureHeight() {
        return textureHeight;
    }

    /**
     * Returns the file name of the used texture
     *
     * @return File name of the used texture
     */
    public String getTextureFileName() {
        return textureFileName;
    }
}
