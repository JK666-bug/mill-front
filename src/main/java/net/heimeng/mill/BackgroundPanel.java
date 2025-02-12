package net.heimeng.mill;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Background Panel
 *
 * @author JK
 */
public class BackgroundPanel extends JPanel {
    private BufferedImage backgroundImage;

    public BackgroundPanel() {
        try {
            URL imageUrl = getClass().getClassLoader().getResource("image/background.png");
            if (imageUrl != null) {
                backgroundImage = ImageIO.read(imageUrl);
            } else {
                System.err.println("Can not find the picture resource");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (backgroundImage != null) {
            return new Dimension(backgroundImage.getWidth(), backgroundImage.getHeight());
        } else {
            return super.getPreferredSize();
        }
    }
}
