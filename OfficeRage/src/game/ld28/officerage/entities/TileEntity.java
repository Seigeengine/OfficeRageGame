package game.ld28.officerage.entities;

import game.ld28.officerage.gfx.Renderable;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TileEntity extends AbstractDynamicEntity implements Renderable{

    private BufferedImage img;
    
    public TileEntity(double x, double y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, 0, 0);
        img = sprite;
    }
    
    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(img, (int) getX(), (int) getY(), null);
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
    

}