package game.ld28.officerage;

import game.ld28.officerage.entities.TestEntity;
import game.ld28.officerage.gfx.Renderable;
import game.ld28.officerage.gfx.Renderer;
import game.ld28.officerage.input.Keyboard;
import game.ld28.officerage.input.Mouse;
import game.ld28.officerage.utils.PhysicsUtils;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private boolean running;
    private Renderer gfx;
    private Mouse mouse;
    private Keyboard keyboard;
    
    private TestEntity player, nonPlayer;
    private List<Renderable> entities;
    
    public Game(String name, int width, int height) {
        //init here
        gfx = new Renderer(name, width, height);
        mouse = new Mouse();
        keyboard = new Keyboard();
        
        //Shortcuts
        keyboard.registerKey("ESCAPE", KeyEvent.VK_ESCAPE);
        //Movement
        keyboard.registerKey("W", KeyEvent.VK_W);
        keyboard.registerKey("A", KeyEvent.VK_A);
        keyboard.registerKey("S", KeyEvent.VK_S);
        keyboard.registerKey("D", KeyEvent.VK_D);
        
        gfx.addMouse(mouse, mouse);
        gfx.addKeyboard(keyboard);
        
        entities = new ArrayList<Renderable>();
        player = new TestEntity(100, 100, 100, 100, 0xFF0000);
        nonPlayer = new TestEntity(400, 400, 100, 100, 0x00FF00);
        entities.add(player);
        entities.add(nonPlayer);
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
        double speed = 30;
        double vx = 0, vy = 0;
        if (keyboard.isKeyDown("W")) {
            vy += -speed;
        }
        if (keyboard.isKeyDown("S")) {
            vy += speed;
        }
        if (keyboard.isKeyDown("A")) {
            vx += -speed;
        }
        if (keyboard.isKeyDown("D")) {
            vx += speed;
        }
        player.setVel(vx, vy);
    }
    
    private void update(int delta) {
        //Update entities based on time passed.
        player.update(delta);
        if (PhysicsUtils.collides(player, nonPlayer)) {
            player.setCol(0x0000FF);
        }else if(player.getCol().getRGB() == 0xFF0000FF){
            player.setCol(0xFF0000);
        }
    }
    
    private void render() {
        //Push rendering to Renderer via lists of Renderables.
        gfx.render(entities);
    }
    
    private void sleep() {
        //Maybe base off delta. Figure out in timing
        try {
            Thread.sleep(10);
        } catch (Exception e) {
        }
    }
    
}