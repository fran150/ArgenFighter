package ar.com.pachisoft.argenfighter.tests.gfx;

import ar.com.pachisoft.argenfighter.gfx.Texture;
import ar.com.pachisoft.argenfighter.tests.utils.Procedure;
import ar.com.pachisoft.argenfighter.tests.utils.TestConsts;
import ar.com.pachisoft.argenfighter.tests.utils.TestUtils;
import org.junit.jupiter.api.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

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
            Texture texture = new Texture(TestConsts.TEST_TEXTURE_NAME);

            assertEquals(TestConsts.TEST_TEXTURE_NAME, texture.getFileName());
            assertImage(texture);

            if (!TestUtils.validateSHA256(texture.getImage(), TestConsts.TEST_TEXTURE_SHA256)) {
                fail("SHA256 of the loaded image does not match the one for the image");
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("Must load fighter image from cache")
    void loadFighterImageFromCache() {
        CreateTempFileAndRun(() -> {
            try {
                new Texture(TestConsts.TMP_TEST_TEXTURE_NAME);
            } catch (IOException e) {
                fail(e);
            }
        });

        try {
            Texture texture = new Texture(TestConsts.TMP_TEST_TEXTURE_NAME);

            assertEquals(TestConsts.TMP_TEST_TEXTURE_NAME, texture.getFileName());
            assertImage(texture);
        } catch (IOException e) {
            fail(e);
        }
    }

    private void CreateTempFileAndRun(Procedure procedure) {
        // Get the class loader and get the texture file resource
        ClassLoader loader = getClass().getClassLoader();
        URL resource = loader.getResource("textures/" + TestConsts.TEST_TEXTURE_NAME + ".png");

        // If the the resource exists
        if (resource != null) {
            Path imagePath = null;

            // Get the image path
            try {
                imagePath = Paths.get(resource.toURI());
            } catch (URISyntaxException e) {
                fail("Error in resource path", e);
            }

            // Replace the file name on the path with the tmp file name
            String tmpFileName = imagePath.toString().replace(TestConsts.TEST_TEXTURE_NAME + ".png", TestConsts.TMP_TEST_TEXTURE_NAME + ".png");
            Path tmpImagePath = Paths.get(tmpFileName);

            // Create the new temp file copying the original image and execute the specified procedure,
            // then delete the tmp image
            try {
                Files.copy(imagePath, tmpImagePath);

                procedure.execute();
            } catch (IOException e) {
                fail("Could not create temporary image");
            } finally {
                try {
                    Files.deleteIfExists(tmpImagePath);
                } catch (IOException e) {
                    fail("Could not delete temporary image");
                }
            }
        } else {
            fail("Could not found the fighter.png texture");
        }
    }

    private void assertImage(Texture texture) {
        assertEquals(TestConsts.TEST_TEXTURE_WIDTH, texture.getWidth());
        assertEquals(TestConsts.TEST_TEXTURE_HEIGHT, texture.getHeight());

        BufferedImage image = texture.getImage();
        assertEquals(BufferedImage.TYPE_4BYTE_ABGR, image.getType());
        assertEquals(TestConsts.TEST_TEXTURE_WIDTH, image.getWidth());
        assertEquals(TestConsts.TEST_TEXTURE_HEIGHT, image.getHeight());
    }
}
