package game.ld28.officerage.utils;

import game.ld28.officerage.entities.StaticEntity;

public class PhysicsUtils {
    private PhysicsUtils() {
    }

    public static boolean collides(StaticEntity a, StaticEntity b) {

        boolean withinX = false;
        boolean withinY = false;


        if (a.getX() >= b.getX() && a.getX() <= b.getX() + b.getWidth()) {
            //a.x is within b.x/b.w
            withinX = true;
        }
        if (b.getX() >= a.getX() && b.getX() <= a.getX() + a.getWidth()) {
            //b.x is within a.x/a.w
            withinX = true;
        }
        if (a.getY() >= b.getY() && a.getY() <= b.getY() + b.getHeight()) {
            //a.y is within b.y/b.h
            withinY = true;
        }
        if (b.getY() >= a.getY() && b.getY() <= a.getY() + a.getHeight()) {
            //b.y is within a.y/a.h
            withinY = true;
        }

        return withinX && withinY;
    }
}