package game.ld28.officerage.gfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;

public class Renderer {
    public final String TITLE;
    public final int WIDTH, HEIGHT;
    private JFrame frame;
    
    public Renderer(String title, int width, int height) {
        TITLE = title.intern();
        WIDTH = width;
        HEIGHT = height;
        
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setIgnoreRepaint(true);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
        
        do {
            try {
                frame.createBufferStrategy(3);
            } catch (IllegalStateException e) {
            }
        } while (frame.getBufferStrategy() == null);
        render();
    }
    
    public void render(List<Renderable>... renderables) {
        BufferStrategy bs = frame.getBufferStrategy();
        Graphics2D g2d = null;
        try {
            g2d = (Graphics2D) bs.getDrawGraphics();
            g2d.clearRect(0, 0, WIDTH, HEIGHT);
//            g2d.clipRect(0, 0, WIDTH, HEIGHT);
            
            for (int i = 0; i < renderables.length; i++) {
                for (Iterator<Renderable> it = renderables[i].iterator(); it.hasNext();) {
                    Renderable renderable = it.next();
                    renderable.render(g2d);
                }
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (g2d != null) {
                g2d.dispose();
            }
        }
        
        bs.show();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void addMouse(MouseListener mouseListener, MouseMotionListener motionListener) {
        frame.addMouseListener(mouseListener);
        frame.addMouseMotionListener(motionListener);
    }
    
    public void addKeyboard(KeyListener listener) {
        frame.addKeyListener(listener);
    }

}