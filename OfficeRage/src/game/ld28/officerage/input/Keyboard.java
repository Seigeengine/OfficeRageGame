package game.ld28.officerage.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class Keyboard implements KeyListener {
    private Map<Integer, String> keyMap;
    private Map<String, Key> accessMap;
    
    public Keyboard () {
        keyMap = new HashMap<Integer, String>();
        accessMap = new HashMap<String, Key>();
    }
    
    public void registerKey(String name, int keyCode) {
        Key key = new Key(name);
        accessMap.put(name, key);
        keyMap.put(keyCode, name);
    }
    
    public boolean isKeyDown(String name) {
        if (accessMap.containsKey(name)) {
            return accessMap.get(name).isKeyDown();
        }
        throw new IllegalArgumentException("Attempted to access unregistered key: " + name);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (keyMap.containsKey(e.getKeyCode())) {
            accessMap.get(keyMap.get(e.getKeyCode())).setDown(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keyMap.containsKey(e.getKeyCode())) {
            accessMap.get(keyMap.get(e.getKeyCode())).setDown(false);
        }
    }
    
    public static class Key {
        public final String name;
        private boolean keyDown = false;
        
        public Key(String name) {
            this.name = name.intern();
        }
        
        public void setDown(boolean isDown) {
            keyDown = isDown;
        }

        public String getName() {
            return name;
        }

        public boolean isKeyDown() {
            return keyDown;
        }
        
    }
}