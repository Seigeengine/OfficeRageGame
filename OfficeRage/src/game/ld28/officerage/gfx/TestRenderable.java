package game.ld28.officerage.gfx;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestRenderable implements Renderable{
    private int x, y;
    private int w, h;
    private Color col;
    
    public TestRenderable(int x, int y, int w, int h, int col) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.col = new Color(col);
    }
    
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(col);
        g2d.fillRect(x, y, w, h);
    }

}