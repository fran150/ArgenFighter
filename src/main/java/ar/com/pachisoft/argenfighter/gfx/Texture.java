package ar.com.pachisoft.argenfighter.gfx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Texture {
    private final static Map<String, BufferedImage> cache = new HashMap<>();

    private final BufferedImage image;
    private final String fileName;
    private final int width;
    private final int height;

    public Texture(String fileName)
        throws IOException {

        this.fileName = fileName;

        BufferedImage cached = cache.get(fileName);

        if (cached != null) {
            this.image = cached;
        } else {
            this.image = ImageIO.read(new File("./resources/textures/" + fileName + ".png"));
            cache.put(fileName, image);
        }

        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getFileName() {
        return fileName;
    }
}
