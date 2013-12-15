package game.ld28.officerage.utils;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class AssetLoader {

    public static BufferedImage getBufferedImage(String fileName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(AssetLoader.class.getResourceAsStream(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }
    
}