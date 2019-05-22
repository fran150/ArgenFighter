package ar.com.pachisoft.argenfighter.gfx;

import ar.com.pachisoft.argenfighter.gfx.exceptions.SpriteCoordinatesOutOfBoundsException;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    private final BufferedImage image;
    private final int width;
    private final int height;

    public Sprite(Texture texture, int x, int y, int width, int height)
        throws SpriteCoordinatesOutOfBoundsException {

        if (x < 0 || y < 0 || (x + width) > texture.getWidth() || (y + height) > texture.getHeight()) {
            throw new SpriteCoordinatesOutOfBoundsException(x, y, width, height, texture);
        }

        BufferedImage image = texture.getImage();

        this.image = image.getSubimage(x, y, width, height);
        this.width = width;
        this.height = height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void render(@NotNull Graphics2D g, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    public void render(@NotNull Graphics2D g, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }
}
