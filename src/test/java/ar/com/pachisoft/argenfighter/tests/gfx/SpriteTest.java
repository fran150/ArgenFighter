package ar.com.pachisoft.argenfighter.tests.gfx;

import ar.com.pachisoft.argenfighter.gfx.Sprite;
import ar.com.pachisoft.argenfighter.gfx.SpriteFlippingOptions;
import ar.com.pachisoft.argenfighter.gfx.Texture;
import ar.com.pachisoft.argenfighter.gfx.exceptions.SpriteCoordinatesOutOfBoundsException;
import ar.com.pachisoft.argenfighter.tests.utils.TestConsts;
import ar.com.pachisoft.argenfighter.tests.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.fail;

public class SpriteTest {

    private Texture texture;

    public SpriteTest() {
        try {
            texture = new Texture(TestConsts.TEST_TEXTURE_NAME);
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("Must throw exception when the specified position are out of texture bounds")
    void ItShouldThrowOutOfBounds() {
        // Test upper left corner x axis
        assertThrows(SpriteCoordinatesOutOfBoundsException.class, () -> {
            new Sprite(texture, -1, 0, TestConsts.TEST_TEXTURE_WIDTH, TestConsts.TEST_TEXTURE_HEIGHT);
        });

        // Test upper left corner y axis
        assertThrows(SpriteCoordinatesOutOfBoundsException.class, () -> {
            new Sprite(texture, 0, -1, TestConsts.TEST_TEXTURE_WIDTH, TestConsts.TEST_TEXTURE_HEIGHT);
        });

        // Test width
        assertThrows(SpriteCoordinatesOutOfBoundsException.class, () -> {
            new Sprite(texture, 0, 0, TestConsts.TEST_TEXTURE_WIDTH + 1, TestConsts.TEST_TEXTURE_HEIGHT);
        });

        // Test height
        assertThrows(SpriteCoordinatesOutOfBoundsException.class, () -> {
            new Sprite(texture, 0, 0, TestConsts.TEST_TEXTURE_WIDTH, TestConsts.TEST_TEXTURE_HEIGHT + 1);
        });

        // Test the exception properties
        try {
            new Sprite(texture, -1, -1, 200, 350);
        } catch(SpriteCoordinatesOutOfBoundsException ex) {
            assertEquals(ex.getRequestedX(), -1);
            assertEquals(ex.getRequestedY(), -1);
            assertEquals(ex.getRequestedWidth(), 200);
            assertEquals(ex.getRequestedHeight(), 350);
            assertEquals(ex.getTextureFileName(), TestConsts.TEST_TEXTURE_NAME);
            assertEquals(ex.getTextureHeight(), TestConsts.TEST_TEXTURE_HEIGHT);
            assertEquals(ex.getTextureWidth(), TestConsts.TEST_TEXTURE_WIDTH);
        }
    }

    @Test
    @DisplayName("It must create an sprite using half texture")
    void ItShouldCreateAnSprite() {
        int y = TestConsts.TEST_TEXTURE_HEIGHT / 2;

        Sprite sprite = null;

        try {
            sprite = new Sprite(texture, 0, y, TestConsts.TEST_TEXTURE_WIDTH, y);
        } catch (SpriteCoordinatesOutOfBoundsException e) {
            fail(e);
        }

        assertEquals(sprite.getHeight(), y);
        assertEquals(sprite.getWidth(), TestConsts.TEST_TEXTURE_WIDTH);

        try {
            TestUtils.validateSHA256(sprite.getImage(), TestConsts.HALF_TEST_TEXTURE_SHA256);
        } catch (IOException | NoSuchAlgorithmException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("It must create rotated sprites")
    void ItShouldCreateRotatedSprites() {
        try {
            Sprite flippedHorizontally = new Sprite(texture, 0, 0, TestConsts.TEST_TEXTURE_WIDTH, TestConsts.TEST_TEXTURE_HEIGHT, SpriteFlippingOptions.FLIP_HORIZONTALLY);
            Sprite flippedVertically = new Sprite(texture, 0, 0, TestConsts.TEST_TEXTURE_WIDTH, TestConsts.TEST_TEXTURE_HEIGHT, SpriteFlippingOptions.FLIP_VERTICALLY);
            Sprite flippedBoth = new Sprite(texture, 0, 0, TestConsts.TEST_TEXTURE_WIDTH, TestConsts.TEST_TEXTURE_HEIGHT, SpriteFlippingOptions.FLIP_BOTH);

            TestUtils.validateSHA256(flippedHorizontally.getImage(), TestConsts.HORIZ_ROT_TEST_TEXTURE_SHA256);
            TestUtils.validateSHA256(flippedVertically.getImage(), TestConsts.VERT_ROT_TEST_TEXTURE_SHA256);
            TestUtils.validateSHA256(flippedBoth.getImage(), TestConsts.BOTH_ROT_TEST_TEXTURE_SHA256);
        } catch (SpriteCoordinatesOutOfBoundsException | NoSuchAlgorithmException | IOException e) {
            fail(e);
        }
    }

}
