package com.mortonstudios.app.utils;

import org.apache.commons.io.output.ByteArrayOutputStream;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cameron on 19/08/2018.
 */
public class ImageBase64Helpers {

    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return imageString;
    }

    public static BufferedImage decodeToImage(String imageString) {
        List<String> dirtyString = Arrays.asList(imageString.split(","));
        String cleanString = dirtyString.get(1);
        String correctString = cleanString.replaceAll(" ","+");

        BufferedImage image = null;
        byte[] imageByte;

        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(correctString);

            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);

            bis.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return image;
    }

}
