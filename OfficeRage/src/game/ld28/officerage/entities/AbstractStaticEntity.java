package game.ld28.officerage.entities;

public abstract class AbstractStaticEntity implements StaticEntity {
    protected double x, y;
    protected int w, h;

    public AbstractStaticEntity(double x, double y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setSize(int w, int h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }
}