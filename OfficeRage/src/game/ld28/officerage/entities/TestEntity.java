package game.ld28.officerage.entities;

import game.ld28.officerage.gfx.Renderable;
import java.awt.Color;
import java.awt.Graphics2D;

public class TestEntity extends AbstractDynamicEntity implements Renderable, Updateable {
    
    private Color col;
    
    public TestEntity(int x, int y, int w, int h, int col) {
        super(x, y, w, h, 0, 0);
        this.col = new Color(col);
    }
    
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(col);
        g2d.fillRect((int) x, (int) y, w, h);
    }

    @Override
    public void update(int delta) {
        x += vx*delta/1000;
        y += vy*delta/1000;
    }
    
    public void setCol(int col) {
        this.col = new Color(col);
    }

    public Color getCol() {
        return col;
    }

}