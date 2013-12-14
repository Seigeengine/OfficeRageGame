package game.ld28.officerage;

import game.ld28.officerage.gfx.Renderer;
import game.ld28.officerage.input.Keyboard;
import game.ld28.officerage.input.Mouse;
import java.awt.event.KeyEvent;

public class Game {

    private boolean running;
    private Renderer gfx;
    private Mouse mouse;
    private Keyboard keyboard;
    
    public Game(String name, int width, int height) {
        //init here
        gfx = new Renderer(name, width, height);
        mouse = new Mouse();
        keyboard = new Keyboard();
        
        keyboard.registerKey("ESCAPE", KeyEvent.VK_ESCAPE);
        
        gfx.addMouse(mouse, mouse);
        gfx.addKeyboard(keyboard);
    }
    
    public void start() {
        running = true;
        while (running) {
            int delta = 100; //Do timing to get this.
            input(delta);
            update(delta);
            render();
            sleep();
        }
    }
    
    private void input(int delta) {
        //Process input and alter affected entities.
        if (keyboard.isKeyDown("ESCAPE")) {
            running = false;
        }
    }
    
    private void update(int delta) {
        //Update entities based on time passed.
    }
    
    private void render() {
        //Push rendering to Renderer via lists of Renderables.
    }
    
    private void sleep() {
        //Maybe base off delta. Figure out in timing
        try {
            Thread.sleep(10);
        } catch (Exception e) {
        }
    }
    
}