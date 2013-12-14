package game.ld28.officerage.entities;

public interface DynamicEntity extends StaticEntity{
    public void setVel(double vx, double vy);
    public double getVx();
    public double getVy();
}