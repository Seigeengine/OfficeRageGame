package game.ld28.officerage.entities;

public abstract class AbstractDynamicEntity extends AbstractStaticEntity implements DynamicEntity{

    protected double vx, vy;
    
    public AbstractDynamicEntity(double x, double y, int w, int h, double vx, double vy) {
        super(x, y, w, h);
        this.vx = vx;
        this.vy = vy;
    }
    
    @Override
    public void setVel(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    @Override
    public double getVx() {
        return vx;
    }

    @Override
    public double getVy() {
        return vy;
    }
    
}