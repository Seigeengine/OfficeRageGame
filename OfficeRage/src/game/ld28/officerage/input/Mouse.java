package game.ld28.officerage.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{

    private int mx, my;
    private boolean leftClickPressed = false;
    private boolean middleClickPressed = false;
    private boolean rightClickPressed = false;
    private boolean mouseInScreen = false;
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //Do nothing.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case 1: leftClickPressed = true; break;
            case 2: middleClickPressed = true; break;
            case 3: rightClickPressed = true; break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case 1: leftClickPressed = false; break;
            case 2: middleClickPressed = false; break;
            case 3: rightClickPressed = false; break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseInScreen = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseInScreen = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
    }
    
    //Getters
    public int getMx() {
        return mx;
    }

    public int getMy() {
        return my;
    }

    public boolean isLeftClickPressed(boolean unPress) {
        boolean b = leftClickPressed;
        if (unPress) {
            leftClickPressed = false;
        }
        return b;
    }
    
    public boolean isMiddleClickPressed(boolean unPress) {
        boolean b = middleClickPressed;
        if (unPress) {
            middleClickPressed = false;
        }
        return b;
    }

    public boolean isRightClickPressed(boolean unPress) {
        boolean b = rightClickPressed;
        if (unPress) {
            rightClickPressed = false;
        }
        return b;
    }

}