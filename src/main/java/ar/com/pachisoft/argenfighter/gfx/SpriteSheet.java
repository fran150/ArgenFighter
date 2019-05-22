package ar.com.pachisoft.argenfighter.gfx;

import ar.com.pachisoft.argenfighter.gfx.exceptions.SpriteCoordinatesOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
    private final Texture texture;
    private final List<Sprite> sprites;

    public SpriteSheet(Texture texture) {
        this.texture = texture;
        this.sprites = new ArrayList<>();
    }

    public SpriteSheet(Texture texture, int width, int height)
            throws SpriteCoordinatesOutOfBoundsException {
        this(texture);

        for (int y = 0; y < texture.getHeight(); y += height) {
            for (int x = 0; x < texture.getWidth(); x += width) {
                add(x, y, width, height);
            }
        }
    }

    public SpriteSheet(Texture texture, int size)
            throws SpriteCoordinatesOutOfBoundsException {
        this(texture, size, size);
    }

    public void add(int x, int y, int width, int height)
            throws SpriteCoordinatesOutOfBoundsException {
        Sprite sprite = new Sprite(texture, x, y, width, height);
        sprites.add(sprite);
    }

    public Sprite getSprite(int index) {
        return sprites.get(index);
    }

    public int getSpriteCount() {
        return sprites.size();
    }
}
