package game.ld28.officerage.entities;

public interface StaticEntity {
   public void setPos(double x, double y);
   public void setSize(int w, int h);
   public double getX();
   public double getY();
   public int getWidth();
   public int getHeight();
}