package game.ld28.officerage.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sheet;
    private int spriteSize;

    public SpriteSheet(BufferedImage src, int spriteSize) {
        sheet = src;
        this.spriteSize = spriteSize;
    }

    public BufferedImage getSprite(int spriteXPos, int spriteYPos) {
        int x1 = spriteXPos*spriteSize;
        int y1 = spriteYPos*spriteSize;
        int x2 = x1 + spriteSize;
        int y2 = y1 + spriteSize;
        return sheet.getSubimage(x1, y1, x2, y2);
    }
}