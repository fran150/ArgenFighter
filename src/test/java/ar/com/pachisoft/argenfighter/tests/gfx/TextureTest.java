package ar.com.pachisoft.argenfighter.tests.gfx;

import ar.com.pachisoft.argenfighter.gfx.Texture;
import org.junit.jupiter.api.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class TextureTest {

    @Test
    @DisplayName("Trying to load a non existent texture must throw a IOException")
    void loadNotExistentFile() {
        assertThrows(IOException.class, () -> new Texture("NotFound"));
    }

    @Test
    @DisplayName("Must load fighter image correctly")
    void loadFighterImage() {
        try {
            Texture texture = new Texture("fighter");

            assertEquals("fighter", texture.getFileName());
            assertEquals(200, texture.getWidth());
            assertEquals(350, texture.getHeight());

            BufferedImage image = texture.getImage();
            assertEquals(BufferedImage.TYPE_4BYTE_ABGR, image.getType());
            assertEquals(200, image.getWidth());
            assertEquals(350, image.getHeight());
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("Must load fighter image from cache")
    void loadFighterFromCache() {
        try {
            Texture texture = new Texture("fighter");

        } catch (IOException e) {
            fail(e);
        }

    }

    private void renameFile(String oldFileName, String newFileName) {
        // Get the class loader and get the resource
        ClassLoader loader = getClass().getClassLoader();
        URL oldResource = loader.getResource("textures/" + oldFileName + ".png");

        if (oldResource != null) {
            // Get the file
            File file = new File(resource.getFile());

            File f1 = new File("oldname.txt");
        File f2 = new File("newname.txt");
        boolean b = f1.renameTo(f2);
    }

}
