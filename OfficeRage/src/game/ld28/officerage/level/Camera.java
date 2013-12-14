package game.ld28.officerage.level;

import game.ld28.officerage.entities.AbstractDynamicEntity;
import game.ld28.officerage.entities.StaticEntity;
import game.ld28.officerage.entities.Updateable;
import game.ld28.officerage.gfx.Renderable;
import java.awt.Graphics2D;

public class Camera extends AbstractDynamicEntity implements Updateable, Renderable{
    private StaticEntity following;

    public Camera(double viewX, double viewY, int screenWidth, int screenHeight) {
        super(viewX, viewY, screenWidth, screenHeight, 0, 0);
    }

    public void setFollowing(StaticEntity entity) {
        following = entity;
    }

    @Override
    public void update(int delta) {
        int fx = (int) (following.getX() + following.getWidth() / 2);
        int fy = (int) (following.getY() + following.getHeight() / 2);
        
        setPos(fx - getWidth()/2, fy - getHeight()/2);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.translate(-getX(), -getY());
    }
}