package ar.com.pachisoft.argenfighter.gfx;

import ar.com.pachisoft.argenfighter.gfx.exceptions.SpriteCoordinatesOutOfBoundsException;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Represents an sprite which is an image that can be manipulated as a single entity.
 */
public class Sprite {
    private final BufferedImage image;
    private final int width;
    private final int height;

    /**
     * Creates an sprite extracting it from the specified texture and the given coordinates.
     * You can also specify if the image must be flipped horizontally, vertically or both, this is
     * useful to create sprites looking in different directions using the same image.
     *
     * @param texture Texture from where to extract the sprite
     * @param x X axis coordinate in the texture of the upper left corner of the sprite
     * @param y Y axis coordinate in the texture of the upper left corner of the sprite
     * @param width width of the sprite in pixels
     * @param height height of the sprite in pixels
     * @param flip Indicates if the sprite must be flipped in some direction
     * @throws SpriteCoordinatesOutOfBoundsException Exception produced when the specified coordinates
     * are out of the texture coordinates
     */
    public Sprite(Texture texture, int x, int y, int width, int height, SpriteFlippingOptions flip)
        throws SpriteCoordinatesOutOfBoundsException {

        // If the specified coordinates are out of texture bound throw exception
        if (x < 0 || y < 0 || (x + width) > texture.getWidth() || (y + height) > texture.getHeight()) {
            throw new SpriteCoordinatesOutOfBoundsException(x, y, width, height, texture);
        }

        // Get the texture image
        BufferedImage image = texture.getImage();

        // Extract the image from the texture and apply the requested flipping
        this.image = flipImage(image.getSubimage(x, y, width, height), flip);

        // Set the sprite's width and height
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
    }

    /**
     * Creates an sprite extracting it from the specified texture and the given coordinates.
     *
     * @param texture Texture from where to extract the sprite
     * @param x X axis coordinate in the texture of the upper left corner of the sprite
     * @param y Y axis coordinate in the texture of the upper left corner of the sprite
     * @param width width of the sprite in pixels
     * @param height height of the sprite in pixels
     * @throws SpriteCoordinatesOutOfBoundsException Exception produced when the specified coordinates
     * are out of the texture coordinates
     */
    public Sprite(Texture texture, int x, int y, int width, int height)
            throws SpriteCoordinatesOutOfBoundsException {
        this(texture, x, y, width, height, SpriteFlippingOptions.FLIP_NONE);
    }

    /**
     * Returns the sprite associated image
     *
     * @return Sprite image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Returns the sprite width
     *
     * @return Sprite width in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the sprite height
     *
     * @return Sprite height in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Draws the sprite using the graphics object in the specified window position
     *
     * @param g Graphics object to use to draw the sprite
     * @param x X position of the sprite on the main window
     * @param y Y position of the sprite on the main window
     */
    public void render(@NotNull Graphics2D g, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    /**
     * Draws the sprite using the graphics object in the specified window position
     *
     * @param g Graphics object to use to draw the sprite
     * @param x X position of the sprite on the main window
     * @param y Y position of the sprite on the main window
     * @param width New width of the sprite
     * @param height New height of the sprite
     */
    public void render(@NotNull Graphics2D g, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }

    /**
     * Flips the specified image
     *
     * @param image Image to flip
     * @param flip Flip direction
     * @return Flipped image
     */
    private static BufferedImage flipImage(BufferedImage image, SpriteFlippingOptions flip) {
        if (flip == SpriteFlippingOptions.FLIP_NONE) {
            return image;
        }

        int sx = 1;
        int sy = 1;
        double w = 0;
        double h = 0;

        if (flip == SpriteFlippingOptions.FLIP_VERTICALLY || flip == SpriteFlippingOptions.FLIP_BOTH) {
            sy = -1;
            h = -image.getHeight();
        }

        if (flip == SpriteFlippingOptions.FLIP_HORIZONTALLY || flip == SpriteFlippingOptions.FLIP_BOTH) {
            sx = -1;
            w = -image.getWidth();
        }

        AffineTransform tx = AffineTransform.getScaleInstance(sx, sy);
        tx.translate(w, h);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        return op.filter(image, null);
    }
}
