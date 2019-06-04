package ar.com.pachisoft.argenfighter.gfx;

import ar.com.pachisoft.argenfighter.gfx.exceptions.SpriteCoordinatesOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows to extract sprites from a texture as a sprite sheet.
 */
public class SpriteSheet {
    private final Texture texture;
    private final List<Sprite> sprites;

    /**
     * Loads a texture as sprite sheet but don't automatically extract any sprite from it
     * This constructor is useful to load the sprite sheet and then use the add methods to
     * extract the sprites from the sheet
     *
     * @param texture Texture to use as sprite sheet
     */
    public SpriteSheet(Texture texture) {
        this.texture = texture;
        this.sprites = new ArrayList<>();
    }

    /**
     * Loads a texture as a sprite sheet and extract sprites of the given size
     * processing the texture from left to right and up to down.
     * This constructor is useful when all the sprites in the texture are of the same size
     * and ordered
     *
     * @param texture Texture to use as sprite sheet
     * @param width Width of the sprites to extract
     * @param height Height of the sprites to extract
     * @param flip Indicate if the sprite must be flipped in any direction
     */
    public SpriteSheet(Texture texture, int width, int height, SpriteFlippingOptions flip) {
        this(texture);

        try {
            for (int y = 0; y < texture.getHeight(); y += height) {
                for (int x = 0; x < texture.getWidth(); x += width) {
                    add(x, y, width, height, flip);
                }
            }
        } catch (SpriteCoordinatesOutOfBoundsException e) {
            throw new RuntimeException("Error processing sprite sheet", e);
        }
    }

    /**
     * Loads a texture as a sprite sheet and extract square sprites of the specified size
     * processing the texture from left to right and up to down.
     * This constructor is useful when all the sprites in the texture are square and ordered
     *
     * @param texture Texture to use as sprite sheet
     * @param size Size of the square sprite
     * @param flip Indicate if the sprite must be flipped in any direction
     */
    public SpriteSheet(Texture texture, int size, SpriteFlippingOptions flip) {
        this(texture, size, size, flip);
    }

    /**
     * Extract an sprite from the sprite sheet and add it to the sprite list
     *
     * @param x X position of the upper left corner of the sprite
     * @param y Y position of the upper left corner of the sprite
     * @param width Width of the sprite
     * @param height Height of the sprite
     * @param flip Indicate if the sprite must be flipped in any direction
     * @throws SpriteCoordinatesOutOfBoundsException Produced if the specified coordinates are out of the texture
     */
    public void add(int x, int y, int width, int height, SpriteFlippingOptions flip)
            throws SpriteCoordinatesOutOfBoundsException {
        Sprite sprite = new Sprite(texture, x, y, width, height, flip);
        sprites.add(sprite);
    }

    /**
     * Extract the specified number of sprites scanning from left to right starting from the specified
     * position
     *
     * @param x X coordinate of the upper left corner of the first sprite
     * @param y Y coordinate of the upper left corner of the first sprite
     * @param width Width of the sprites
     * @param height Height of the sprites
     * @param count Number of sprites to extract
     * @param flip Indicate if the sprites must be flipped in any direction
     * @throws SpriteCoordinatesOutOfBoundsException Produced if the specified coordinates are out of the texture
     */
    public void add(int x, int y, int width, int height, int count, SpriteFlippingOptions flip)
            throws SpriteCoordinatesOutOfBoundsException {
        for (int i = 0; i < count; i++) {
            add(x, y, width, height, flip);
            x += width;
        }
    }

    /**
     * Get the sprite with the specified index
     *
     * @param index Index of the requested sprite
     * @return Sprite with the specified index
     */
    public Sprite getSprite(int index) {
        return sprites.get(index);
    }

    /**
     * Get the number of sprites currently extracted from the sprite sheet
     *
     * @return Number of the sprites extracted from the sprite sheet
     */
    public int getCount() {
        return sprites.size();
    }
}
