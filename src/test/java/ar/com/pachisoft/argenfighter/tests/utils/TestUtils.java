package ar.com.pachisoft.argenfighter.tests.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilities for testing purposes
 */
public class TestUtils {

    /**
     * Writes the image to the specified file
     *
     * @param image Image to write
     * @param file Path to the file in where to save image
     * @throws IOException Produced if the specified file cannot be created
     */
    public static void SaveImageTo(BufferedImage image, String file)
            throws IOException {
        FileOutputStream output = new FileOutputStream(file);
        ImageIO.write(image, "png", output);
        output.flush();
        output.close();
    }

    /**
     * Generate the SHA256 of the specified image
     *
     * @param image Image to process
     * @return SHA256 string of the specified image
     * @throws IOException Error if the buffered image cannot be transformed to bytes
     * @throws NoSuchAlgorithmException Error if the sha256 algorithm is not found
     */
    public static String getSHA256(BufferedImage image)
            throws IOException, NoSuchAlgorithmException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(image, "png", output);
        output.flush();
        byte[] imageBytes = output.toByteArray();
        output.close();

        return getSHA256String(imageBytes);
    }

    /**
     * Returns whether the image matches the specified SHA256 checksum
     *
     * @param image Image to process
     * @param expectedSha Expected SHA256 value of the image
     * @return True if the image matches the specified checksum
     * @throws IOException Error if the buffered image cannot be transformed to bytes
     * @throws NoSuchAlgorithmException Error if the sha256 algorithm is not found
     */
    public static boolean validateSHA256(BufferedImage image, String expectedSha)
            throws IOException, NoSuchAlgorithmException {
        String hash = getSHA256(image);
        return hash.equals(expectedSha);
    }

    /**
     * Get the SHA256 hex string of the specified byte array
     *
     * @param source bytes to calculate sha256
     * @return SHA256 String calculated from the specified array
     * @throws NoSuchAlgorithmException Error if the sha256 algorithm is not found
     */
    private static String getSHA256String(byte[] source) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(source);

        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);

            if (hex.length() == 1) {
                hexString.append('0');
            }

            hexString.append(hex);
        }

        return hexString.toString();
    }
}