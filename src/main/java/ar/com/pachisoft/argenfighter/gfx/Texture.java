package ar.com.pachisoft.argenfighter.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Allows to load an image file to be used as texture
 */
public class Texture {
    // Cache loaded images by filename
    private final static Map<String, BufferedImage> cache = new HashMap<>();

    private final BufferedImage image;
    private final String fileName;
    private final int width;
    private final int height;

    /**
     * Loads the specified image file.
     * The file name is relative to the resources/textures folder and must be in png format.
     *
     * @param fileName Name of the file to load without the png extension
     * @throws IOException Error produced when trying to read the file
     */
    public Texture(String fileName)
        throws IOException {

        // Set the filename
        this.fileName = fileName;

        // Try to get the image from the cache
        BufferedImage cached = cache.get(fileName);

        // If the image is cached use it from there, if not load
        if (cached != null) {
            this.image = cached;
        } else {
            // Get the class loader and get the resource
            ClassLoader loader = getClass().getClassLoader();
            URL resource = loader.getResource("textures/" + fileName + ".png");

            if (resource != null) {
                // Get the file
                File file = new File(resource.getFile());

                // Load the image
                this.image = ImageIO.read(file);

                // Store the image on cache
                cache.put(fileName, image);
            } else {
                throw new IOException("Texture file not found!! (" + fileName + ")");
            }
        }

        // Set the texture width and height reading it from the image
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    /**
     * Returns the texture width
     *
     * @return Width of the texture in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns ths texture height
     *
     * @return Height of the texture in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the image object
     *
     * @return Loaded image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Get the image filename
     *
     * @return Filename of the image relative to the resources/texture folder and without the png extension
     */
    public String getFileName() {
        return fileName;
    }
}
