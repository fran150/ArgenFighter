package ar.com.pachisoft.argenfighter.tests.gfx;

import ar.com.pachisoft.argenfighter.gfx.SpriteFlippingOptions;
import ar.com.pachisoft.argenfighter.gfx.SpriteSheet;
import ar.com.pachisoft.argenfighter.gfx.Texture;
import ar.com.pachisoft.argenfighter.gfx.exceptions.SpriteCoordinatesOutOfBoundsException;
import ar.com.pachisoft.argenfighter.tests.utils.TestConsts;
import ar.com.pachisoft.argenfighter.tests.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class SpriteSheetTest {
    private Texture texture;

    public SpriteSheetTest() {
        try {
            this.texture = new Texture(TestConsts.TEST_SHEET_NAME);
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("It must add manually two sprites from the sheet")
    void MustAddManuallyFromTheSheet() {
        SpriteSheet sheet = new SpriteSheet(texture);

        try {
            sheet.add(0, 0, 100, 100, SpriteFlippingOptions.FLIP_NONE);
            sheet.add(500, 0, 100, 100, SpriteFlippingOptions.FLIP_NONE);

            assertTrue(TestUtils.validateSHA256(sheet.getSprite(0).getImage(), TestConsts.TEST_SHEET_0_TEXT_SHA256));
            assertTrue(TestUtils.validateSHA256(sheet.getSprite(1).getImage(), TestConsts.TEST_SHEET_5_TEXT_SHA256));
        } catch (SpriteCoordinatesOutOfBoundsException | NoSuchAlgorithmException | IOException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("It must add manually 6 sprites from an sprite in the sheet")
    void MustAddManuallyStripeFromTheSheet() {
        SpriteSheet sheet = new SpriteSheet(texture);

        try {
            sheet.add(0, 0, 100, 100, 6, SpriteFlippingOptions.FLIP_NONE);

            assertTrue(TestUtils.validateSHA256(sheet.getSprite(0).getImage(), TestConsts.TEST_SHEET_0_TEXT_SHA256));
            assertTrue(TestUtils.validateSHA256(sheet.getSprite(5).getImage(), TestConsts.TEST_SHEET_5_TEXT_SHA256));
        } catch (SpriteCoordinatesOutOfBoundsException | NoSuchAlgorithmException | IOException e) {
            fail(e);
        }
    }


    @Test
    @DisplayName("It must parse the sprite sheet creating 24 sprites")
    void MustCreate24Sprites() {
        SpriteSheet sheet = new SpriteSheet(texture, 100, SpriteFlippingOptions.FLIP_NONE);

        assertEquals(24, sheet.getCount());

        try {
            assertTrue(TestUtils.validateSHA256(sheet.getSprite(0).getImage(), TestConsts.TEST_SHEET_0_TEXT_SHA256));
            assertTrue(TestUtils.validateSHA256(sheet.getSprite(5).getImage(), TestConsts.TEST_SHEET_5_TEXT_SHA256));
            assertTrue(TestUtils.validateSHA256(sheet.getSprite(23).getImage(), TestConsts.TEST_SHEET_23_TEXT_SHA256));
        } catch (IOException | NoSuchAlgorithmException e) {
            fail(e);
        }
    }
}
