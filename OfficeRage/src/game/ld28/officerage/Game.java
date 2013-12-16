package game.ld28.officerage;

import game.ld28.officerage.entities.TestEntity;
import game.ld28.officerage.gfx.Renderable;
import game.ld28.officerage.gfx.Renderer;
import game.ld28.officerage.gfx.SpriteSheet;
import game.ld28.officerage.input.Keyboard;
import game.ld28.officerage.input.Mouse;
import game.ld28.officerage.level.Camera;
import game.ld28.officerage.level.TileMap;
import game.ld28.officerage.level.TileType;
import game.ld28.officerage.utils.AssetLoader;
import game.ld28.officerage.utils.Clock;
import game.ld28.officerage.utils.MapIO;
import game.ld28.officerage.utils.PhysicsUtils;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private boolean running;
    private Renderer gfx;
    private Mouse mouse;
    private Keyboard keyboard;
    private Camera camera;
    private TestEntity player;
    private TileMap level;
    private List<Renderable> entities;
    private List<Renderable> background;

    public Game(String name, int width, int height) {
        //init here
        gfx = new Renderer(name, width, height);
        mouse = new Mouse();
        keyboard = new Keyboard();

        camera = new Camera(0, 0, gfx.WIDTH, gfx.HEIGHT);
        gfx.setCamera(camera);

        Clock.setSyncFPS(60);
        
        TileType.load();
        
        //Shortcuts
        keyboard.registerKey("ESCAPE", KeyEvent.VK_ESCAPE);
        keyboard.registerKey("SAVE", KeyEvent.VK_PAGE_DOWN);
        //Movement
        keyboard.registerKey("W", KeyEvent.VK_W);
        keyboard.registerKey("A", KeyEvent.VK_A);
        keyboard.registerKey("S", KeyEvent.VK_S);
        keyboard.registerKey("D", KeyEvent.VK_D);

        gfx.addMouse(mouse, mouse);
        gfx.addKeyboard(keyboard);

        background = new ArrayList<Renderable>();
        level = new TileMap(32, 64, 32, camera);
        background.add(level);

        entities = new ArrayList<Renderable>();
        player = new TestEntity(100, 100, 100, 100, 0xFF0000);
        entities.add(player);
    }

    public void start() {
        camera.setFollowing(player);
        running = true;
        
        while (running) {
            int delta = Clock.getDelta(); //Do timing to get this.
            input(delta);
            update(delta);
            render();
            sleep();
        }
    }

    private void input(int delta) {
        //Process input and alter affected entities.
        if (keyboard.isKeyDown("ESCAPE", true)) {
            running = false;
        }
        if (keyboard.isKeyDown("SAVE", true)) {
            MapIO.save("src/game/ld28/officerage/assets/leveldata/data.txt", level.getData());
        }
        double speed = 200;
        double vx = player.getX()*0, vy = player.getVy();
        if (keyboard.isKeyDown("A", false)) {
            vx += -speed;
        }
        if (keyboard.isKeyDown("D", false)) {
            vx += speed;
        }
        if (vy == 0 && keyboard.isKeyDown("W", false)) {
            vy -= 200;
        }
        vy += 1;
        player.setVel(vx, vy);
    }

    private void update(int delta) {
        if (mouse.isLeftClickPressed(false)) {
            level.setTile(mouse.getMx(), mouse.getMy(), TileType.STRUCTURE);
        }
        if (mouse.isRightClickPressed(false)) {
            level.setTile(mouse.getMx(), mouse.getMy(), TileType.SKY);
        }

        //Update entities based on time passed.
        double px = player.getX();
        double py = player.getY();
        player.update(delta);
        double px2 = player.getX();
        double py2 = player.getY();
        
        //This behaves weirdly and should maybe be moved out of here.
        if (level.testCollision(player)) {
            player.setPos(px, py2);
            if (level.testCollision(player)) {
                player.setVel(0, player.getVy());
            }
            player.setPos(px2, py);
            if (level.testCollision(player)) {
                player.setVel(player.getVx(), 0);
            }
            player.setPos(px, py);
            player.update(delta);
        }
        camera.update(delta);
    }

    private void render() {
//        Push rendering to Renderer via lists of Renderables.
        gfx.render(background, entities);
    }

    private void sleep() {
        //Maybe base off delta. Figure out in timing
        try {
            Thread.sleep(Clock.getSyncTime()-1);
        } catch (Exception e) {
        }
    }
}