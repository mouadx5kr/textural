package com.lazy.textural;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Textural {

    private final String name;
    private Pattern pattern = new Background();
    private int baseColor;

    public Textural(String name) {
        this.name = name;
        this.baseColor = 0x00000000;
    }

    public void print(int width, int height) {
        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        pattern.paint(new Brush(image, baseColor), width, height);
        try {
            FileOutputStream output = new FileOutputStream(name);
            ImageIO.write(image, "PNG", output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage retrieveImage() throws IOException {
        return ImageIO.read(new FileInputStream(name));
    }

    public Textural color(int color) {
        this.baseColor = color;
        return this;
    }

    public Textural rectanglePattern(float distanceFromBorder) {
        this.pattern = new Rectangle(distanceFromBorder);
        return this;
    }
}
