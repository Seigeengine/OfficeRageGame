package game.ld28.officerage.utils;

import game.ld28.officerage.entities.StaticEntity;

public class PhysicsUtils {

    private PhysicsUtils() {}
    
    public static boolean collides(StaticEntity a, StaticEntity b) {
        return ((a.getX() >= b.getX() && a.getX() <= b.getX() + b.getWidth())
                || (b.getX() >= a.getX() && b.getX() <= a.getX() + a.getWidth()))
                && ((a.getY() >= b.getY() && a.getY() <= b.getY() + b.getHeight())
                || (b.getY() >= a.getY() && b.getY() <= a.getY() + a.getHeight()));
    }
    
}